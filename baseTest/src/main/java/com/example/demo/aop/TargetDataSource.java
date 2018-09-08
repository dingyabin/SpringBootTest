package com.example.demo.aop;

import java.lang.annotation.*;

/**
 * Created by MrDing
 * Date: 2017/12/29.
 * Time:22:41
 */

@Documented
@Inherited
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {

    DataSourceType value() default DataSourceType.FIRSTDATASOURCE;
}
