package com.example.demo.datasource;

import com.example.demo.transaction.TransactionHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Discard on 2016/11/7.
 */

public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }


    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = TransactionHolder.getConnection();
        if (connection != null) {
            return connection;
        }
        Connection con = super.getConnection();
        if (TransactionHolder.getTimes() > 0) {
            con = getProxyConnection(con);
            con.setAutoCommit(false);
            TransactionHolder.setConnection(con);
        }
        return con;
    }


    private Connection getProxyConnection(final Connection con) {
        Object proxy = Proxy.newProxyInstance(DynamicDataSource.class.getClassLoader(), new Class[]{Connection.class}, (proxy1, method, args) -> {
            int times = TransactionHolder.getTimes();
            if (method.getName().equals("close")) {
                times = TransactionHolder.getTimes();
                if (times > 0) {
                    return null;
                }
            }
            if (method.getName().equals("commit")) {
                if (times > 0) {
                    return null;
                }
            }
            if (method.getName().equals("rollback")) {
                if (times > 0) {
                    return null;
                }
            }
            return method.invoke(con, args);
        });
        return (Connection) proxy;
    }


}
