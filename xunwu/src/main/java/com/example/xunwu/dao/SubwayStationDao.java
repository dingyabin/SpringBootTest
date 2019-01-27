package com.example.xunwu.dao;

import com.example.xunwu.model.SubwayStation;

public interface SubwayStationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SubwayStation record);

    int insertSelective(SubwayStation record);

    SubwayStation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SubwayStation record);

    int updateByPrimaryKey(SubwayStation record);
}