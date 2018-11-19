package com.example.demo.transaction;

import com.example.demo.datasource.DynamicDataSourceHolder;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MrDing
 * Date: 2018/11/19.
 * Time:20:40
 */
public class TransactionHolder {

    private static ThreadLocal<Integer> txTimes = ThreadLocal.withInitial(() -> 0);


    private static ThreadLocal<Map<String, Connection>> conMap = ThreadLocal.withInitial(HashMap::new);


    public static Integer getTimes() {
        return txTimes.get();
    }


    public static void addTime() {
        Integer times = getTimes();
        times++;
        txTimes.set(times);
    }


    public static void minusTime() {
        Integer times = getTimes();
        times--;
        if (times < 0) {
            times = 0;
        }
        txTimes.set(times);
    }

    public static void resetTxTimes() {
        txTimes.set(0);
    }


    public static void clear() {
        conMap.remove();
        txTimes.remove();
    }


    public static Connection getConnection() {
        Map<String, Connection> connectionMap = conMap.get();
        return connectionMap.get(DynamicDataSourceHolder.getDataSource());
    }


    public static void setConnection(Connection connection) {
        Map<String, Connection> connectionMap = conMap.get();
        connectionMap.put(DynamicDataSourceHolder.getDataSource(), connection);
        conMap.set(connectionMap);
    }


    public static Collection<Connection> getConnections() {
        Map<String, Connection> map = conMap.get();
        return map.values();
    }




}
