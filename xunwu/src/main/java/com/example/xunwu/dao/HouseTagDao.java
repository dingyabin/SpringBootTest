package com.example.xunwu.dao;

import com.example.xunwu.model.HouseTag;

public interface HouseTagDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseTag record);

    int insertSelective(HouseTag record);

    HouseTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseTag record);

    int updateByPrimaryKey(HouseTag record);
}