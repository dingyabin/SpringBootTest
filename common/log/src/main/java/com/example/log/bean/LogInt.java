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
public class LogInt {

    private String key;

    private int value;

    public LogInt() {
    }

    public LogInt(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return value+"";
    }
}
