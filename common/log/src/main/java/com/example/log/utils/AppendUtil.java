package com.example.log.utils;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.CoreConstants;
import com.example.log.bean.LogBoolean;
import com.example.log.bean.LogInt;
import com.example.log.bean.LogString;


import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dingyabin001
 * Date: 2018/10/23.
 * Time:12:02
 */
public class AppendUtil {

    private final static String[] excludeException = {"at org.apache.catalina",
                                                      "at org.apache.tomcat",
                                                      "at org.apache.coyote",
                                                      "at org.springframework.web",
                                                      "at sun.reflect"
                                                     };

    public static void logThrowableInfo(Map<String, Object> myMap, IThrowableProxy proxy) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s: %s\n", proxy.getClassName(), proxy.getMessage()));
        for (StackTraceElementProxy element : proxy.getStackTraceElementProxyArray()) {
            String steAsString = element.getSTEAsString();
            if (steAsString != null && startWith(excludeException,steAsString)) {
                continue;
            }
            sb.append(CoreConstants.TAB).append(steAsString).append("\n");
        }
        if (proxy.getCause() != null) {
            sb.append("Cause:").append(proxy.getCause());
        }
        myMap.put("throwable", sb.toString());
    }



    public static void logKeyValueInfo(Map<String, Object> myMap,Object[] argumentArray) {
        Map<String, String> strMap = new HashMap<>();
        Map<String, Integer> intMap = new HashMap<>();
        Map<String, Boolean> bolMap = new HashMap<>();
        for (Object o : argumentArray) {
            if (o instanceof LogString) {
                LogString logString = (LogString) o;
                strMap.put(logString.getKey(), logString.getValue());
            }
            if (o instanceof LogInt) {
                LogInt logInt = (LogInt) o;
                intMap.put(logInt.getKey(), logInt.getValue());
            }
            if (o instanceof LogBoolean) {
                LogBoolean logBoolean = (LogBoolean) o;
                bolMap.put(logBoolean.getKey(), logBoolean.getValue());
            }
        }
        if (!strMap.isEmpty()) {
            myMap.put("strValue", strMap);
        }
        if (!intMap.isEmpty()) {
            myMap.put("intValue", intMap);
        }
        if (!bolMap.isEmpty()) {
            myMap.put("boolValue", bolMap);
        }
    }



    public static void logCallerInfo(Map<String, Object> myMap, StackTraceElement callerData) {
        Map<String, Object> data = new HashMap<>();
        data.put("className",  callerData.getClassName());
        data.put("methodName", callerData.getMethodName());
        data.put("fileName",   callerData.getFileName());
        data.put("lineNumber", callerData.getLineNumber());
        myMap.put("callData", data);
    }




    private static  boolean startWith(String[] sources,String tobeMatched){
        return Arrays.stream(sources).anyMatch(tobeMatched::startsWith);
    }



    public static  String getAddress() {
        String ip = "unknown";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }


    public static String getHost() {
        String host = "unknown";
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return host;
    }
}
