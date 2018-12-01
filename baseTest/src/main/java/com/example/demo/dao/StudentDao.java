package com.example.demo.dao;


import com.example.demo.aop.DataSourceType;
import com.example.demo.aop.TargetDataSource;
import com.example.demo.bean.Student;

import java.util.List;
import java.util.Map;

@TargetDataSource(DataSourceType.DS_B)
public interface StudentDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Map<String,String>> selectDistinct();
}