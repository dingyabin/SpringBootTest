package com.example.demo.configration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 *
 */
public class WeConfig {

    private static final Logger logger = LoggerFactory.getLogger(WeConfig.class);

    private static Properties configs = new Properties();


    protected static void reLoad(Properties properties) {
        configs.putAll(properties);
    }


    /**
     * 返回String类型的配置结果
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        String result = configs.getProperty(key);
        return result;
    }

    /**
     * 返回String类型的配置结果
     * @param key
     * @param defaultValue 如果值为空, 返回defaultValue
     * @return
     */
    public static String get(String key, String defaultValue) {
        String prop = configs.getProperty(key);
        if(prop==null){
            return defaultValue;
        }
        return prop;
    }

    /**
     * 返回Integer型的配置结果。如果没有找到配置，返回null
     *
     * @param key
     * @return
     */
    public static Integer getInt(String key) {
        String value = get(key);
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            logger.error("get int error, return null.", ex);
            return null;
        }
    }

    public static Integer getInt(String key, Integer defaultVlaue) {
        String value = get(key);
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return defaultVlaue;
        }
    }

    /**
     * 返回Boolean型的配置结果。如果没有找到配置，返回false
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        String value = get(key);
        return Boolean.valueOf(value);
    }


    /**
     * 返回Double型的配置结果。如果没有找到配置，返回null
     *
     * @param key
     * @return
     */
    public static Double getDouble(String key) {
        String value = get(key);
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            logger.error("get int error, return null.", e);
            return null;
        }
    }

    /**
     * 返回Long型的配置结果。如果没有找到配置，返回null
     *
     * @param key
     * @return
     */
    public static Long getLong(String key) {
        String value = get(key);
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            logger.error("get int error, return null.", e);
            return null;
        }
    }


    public static Properties cat(){
        Properties properties=new Properties();
        //复制一份
        properties.putAll(configs);
        return properties;
    }


}