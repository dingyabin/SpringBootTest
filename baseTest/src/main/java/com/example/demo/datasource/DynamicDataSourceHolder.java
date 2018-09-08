package com.example.demo.datasource;


import static com.example.demo.aop.DataSourceType.FIRSTDATASOURCE;

/**
 * Created by Discard on 2016/11/7.
 */
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSource(String type) {
        CONTEXT_HOLDER.set(type);
    }

    public static String getDataSource() {
        if(null == CONTEXT_HOLDER.get()){
            DynamicDataSourceHolder.setDataSource(FIRSTDATASOURCE.getType());
        }
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }

}