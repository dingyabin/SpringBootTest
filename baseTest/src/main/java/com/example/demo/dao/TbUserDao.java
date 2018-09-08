package com.example.demo.dao;

import com.example.demo.aop.DataSourceType;
import com.example.demo.aop.TargetDataSource;
import com.example.demo.bean.TbUser;

import java.util.List;

@TargetDataSource(DataSourceType.SECDATASOURCE)
public interface TbUserDao {

    int insert(TbUser record);

    List<TbUser> selectAll();

}