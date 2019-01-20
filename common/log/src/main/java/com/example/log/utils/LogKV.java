package com.example.log.utils;

import com.example.log.bean.LogBoolean;
import com.example.log.bean.LogInt;
import com.example.log.bean.LogString;

/**
 * Created by dingyabin001
 * Date: 2018/10/22.
 * Time:17:54
 */
public class LogKV {

    public static LogString logKV(String key, String value) {
        return new LogString(key, value);
    }

    public static LogInt logKV(String key, int value) {
        return new LogInt(key, value);
    }

    public static LogBoolean logKV(String key, boolean value) {
        return new LogBoolean(key, value);
    }

}
