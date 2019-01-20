package com.example.log.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by dingyabin001
 * Date: 2018/10/22.
 * Time:17:50
 */
@Getter
@Setter
public class LogString {

    private String key;

    private String value;

    public LogString() {
    }

    public LogString(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
