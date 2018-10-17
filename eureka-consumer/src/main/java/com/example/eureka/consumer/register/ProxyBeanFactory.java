package com.example.eureka.consumer.register;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * Created by MrDing
 * Date: 2018/10/17.
 * Time:0:59
 */

public class ProxyBeanFactory<T> implements FactoryBean<T> {

    private Class clz;


    public ProxyBeanFactory(String clzName) throws ClassNotFoundException {
        this.clz = Class.forName(clzName);
    }


    @SuppressWarnings("unchecked")
    public T getObject() {
        Class[] interfaces = clz.getInterfaces();
        if (clz.isInterface()) {
            interfaces = ArrayUtils.add(interfaces, clz);
        }
        Object o = Proxy.newProxyInstance(clz.getClassLoader(), interfaces, (proxy, method, args) -> {
            Class<?> returnType = method.getReturnType();
            if (method.getName().equals("toString")) {
                return clz.getName();
            }
            if (returnType == Void.TYPE) {
                return null;
            }
            if (returnType == String.class) {
                return method.getName() + "被调用了!";
            }
            return returnType.newInstance();
        });
        return (T) o;
    }


    @Override
    public Class<?> getObjectType() {
        return clz;
    }


    @Override
    public boolean isSingleton() {
        return true;
    }


}
