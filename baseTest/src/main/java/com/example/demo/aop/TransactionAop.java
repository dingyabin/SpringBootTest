package com.example.demo.aop;

import com.example.demo.transaction.TransactionHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Collection;

/**
 * Created by MrDing
 * Date: 2018/11/19.
 * Time:22:20
 */

@Order(0)
@Aspect
@Component
public class TransactionAop {


    private void before(ProceedingJoinPoint pjp) {
        TransactionHolder.addTime();
    }


    @Around("@annotation(com.example.demo.aop.CustomerTransction)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        try {
            before(pjp);
            Object proceed = pjp.proceed();
            after(pjp);
            return proceed;
        } catch (Throwable t) {
            t.printStackTrace();
            TransactionHolder.resetTxTimes();
            try {
                Collection<Connection> connections = TransactionHolder.getConnections();
                for (Connection connection : connections) {
                    rollback(connection);
                    close(connection);
                }
            } finally {
                TransactionHolder.clear();
            }
            throw t;
        }
    }


    private void after(ProceedingJoinPoint pjp) {
        TransactionHolder.minusTime();
        Integer times = TransactionHolder.getTimes();
        if (times == 0) {
            try {
                Collection<Connection> connections = TransactionHolder.getConnections();
                for (Connection connection : connections) {
                    commit(connection);
                    close(connection);
                }
            } finally {
                TransactionHolder.clear();
            }
        }
    }


    private void commit(Connection able) {
        try {
            able.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void rollback(Connection able) {
        try {
            able.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void close(Connection able) {
        try {
            able.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
