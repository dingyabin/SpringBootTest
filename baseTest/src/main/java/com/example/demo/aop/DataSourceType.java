package com.example.demo.aop;

/**
 * Created by MrDing
 * Date: 2017/12/29.
 * Time:22:29
 */
public enum DataSourceType {

    /**
     *ds_b
     */
    DS_A("ds_a"),


    /**
     * ds_b
     */
    DS_B("ds_b") ;

    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    DataSourceType(String type) {
        this.type = type;
    }
}
