package com.example.dingyabin.data.dao;


import com.example.dingyabin.data.bean.Common;

public interface CommonDao{

    int deleteByPrimaryKey(Long id);

    int insert(Common record);

    int insertSelective(Common record);

    Common selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Common record);

    int updateByPrimaryKey(Common record);
}