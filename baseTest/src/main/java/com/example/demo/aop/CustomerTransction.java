package com.example.demo.aop;

import java.lang.annotation.*;

/**
 * Created by MrDing
 * Date: 2018/11/19.
 * Time:23:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CustomerTransction {
}
