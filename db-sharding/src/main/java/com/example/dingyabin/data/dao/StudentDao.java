package com.example.dingyabin.data.dao;


import com.example.dingyabin.data.bean.Student;

public interface StudentDao {

    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}