package com.example.demo.configration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:21:21
 */
@Component
public class MyBeanContainer implements ApplicationContextAware {


    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyBeanContainer.applicationContext = applicationContext;
    }


    public static <T> T getBean(Class<T> clz) {
        if (applicationContext != null) {
            return applicationContext.getBean(clz);
        }
        return null;
    }


}
