package com.example.log.bean;

/**
 * Created by dingyabin001
 * Date: 2018/10/22.
 * Time:17:50
 */
public class LogBoolean {

    private String key;

    private boolean value;

    public LogBoolean() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public LogBoolean(String key, boolean value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
