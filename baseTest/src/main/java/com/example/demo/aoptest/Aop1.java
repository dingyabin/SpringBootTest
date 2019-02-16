package com.example.demo.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by MrDing
 * Date: 2019/1/15.
 * Time:20:25
 */
@Order(0)
@Aspect
@Component
public class Aop1 {



    @Before("execution(* com.example.demo.aoptest.AopTest.*(..))")
    public void before(){
        System.out.println("Aop1------before");

    }


    @Around("execution(* com.example.demo.aoptest.AopTest.*(..))")
    public Object aroud(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("Aop1------around-before");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("Aop1------around-after");
        return proceed;

    }

    @After("execution(* com.example.demo.aoptest.AopTest.*(..))")
    public void after(){
        System.out.println("Aop1------after");

    }


}
