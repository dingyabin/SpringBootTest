package com.example.shiro.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 *
 */
@Slf4j
public class ConfigUtil {

    private static Properties configs = new Properties();


    public static void reLoad(Properties properties) {
        configs.putAll(properties);
    }


    public static String get(String key) {
        String result = configs.getProperty(key);
        return result;
    }


    public static String get(String key, String defaultValue) {
        String prop = configs.getProperty(key);
        if(prop==null){
            return defaultValue;
        }
        return prop;
    }


    public static int getInt(String key) {
        String value = get(key);
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            log.error("get int error, return null.", ex);
            return 0;
        }
    }


    public static int getInt(String key, int defaultVlaue) {
        String value = get(key);
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return defaultVlaue;
        }
    }


    public static boolean getBoolean(String key) {
        String value = get(key);
        return Boolean.valueOf(value);
    }



    public static double getDouble(String key) {
        String value = get(key);
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            log.error("get int error, return null.", e);
            return 0.0;
        }
    }


    public static long getLong(String key) {
        String value = get(key);
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            log.error("get int error, return null.", e);
            return 0;
        }
    }


    public static Properties cat(){
        Properties properties=new Properties();
        //复制一份
        properties.putAll(configs);
        return properties;
    }


}