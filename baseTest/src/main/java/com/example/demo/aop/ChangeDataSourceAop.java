package com.example.demo.aop;

import com.example.demo.datasource.DynamicDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by MrDing
 * Date: 2017/12/29.
 * Time:22:55
 *
 * @author MrDing
 */

@Order(1)
@Aspect
@Component
public class ChangeDataSourceAop {


    @Pointcut(value = "execution(* com.example.demo.dao..*.*(..))")
    public void pointcutMethod() {

    }


    @Before("pointcutMethod()")
    public void changeDataSource(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();

        //处理类上的注解
        if (declaringClass.isAnnotationPresent(TargetDataSource.class)) {
            TargetDataSource annotation = declaringClass.getAnnotation(TargetDataSource.class);
            DynamicDataSourceHolder.setDataSource(annotation.value().getType());
        }

        //处理方法上的注解(会覆盖类上的注解)
        if (method.isAnnotationPresent(TargetDataSource.class)) {
            TargetDataSource annotation = method.getAnnotation(TargetDataSource.class);
            DynamicDataSourceHolder.setDataSource(annotation.value().getType());
        }
    }

    @After("pointcutMethod()")
    public void clear(){
        DynamicDataSourceHolder.clearDataSource();
    }


}
