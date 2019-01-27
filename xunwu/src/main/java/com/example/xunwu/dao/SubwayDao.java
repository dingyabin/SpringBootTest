package com.example.xunwu.dao;

import com.example.xunwu.model.Subway;

public interface SubwayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Subway record);

    int insertSelective(Subway record);

    Subway selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subway record);

    int updateByPrimaryKey(Subway record);
}