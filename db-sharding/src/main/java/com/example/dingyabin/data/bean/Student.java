package com.example.dingyabin.data.bean;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long stuno;

    private String name;

    private Integer age;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStuno() {
        return stuno;
    }

    public void setStuno(Long stuno) {
        this.stuno = stuno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public static Student create(long id) {
        Student student = new Student();
        student.setId(id);
        student.setStuno((long) new Random().nextInt(100000000));
        student.setName(UUID.randomUUID().toString().toUpperCase().replaceAll("-", "").substring(0, 4));
        student.setAge(new Random().nextInt(30));
        return student;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuno=" + stuno +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}