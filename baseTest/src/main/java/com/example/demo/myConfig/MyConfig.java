package com.example.demo.myConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by MrDing
 * Date: 2017/8/12.
 * Time:23:52
 */
@ConfigurationProperties(prefix = "demo")//此注解并没有向容器中注入bean
public class MyConfig {

    private String name;

    private int age;

    private String id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "myConfig{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }
}
