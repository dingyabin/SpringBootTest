package com.example.xunwu.dao;

import com.example.xunwu.model.HouseSubscribe;

public interface HouseSubscribeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseSubscribe record);

    int insertSelective(HouseSubscribe record);

    HouseSubscribe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseSubscribe record);

    int updateByPrimaryKey(HouseSubscribe record);
}