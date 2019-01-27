package com.example.xunwu.dao;

import com.example.xunwu.model.SupportAddress;

public interface SupportAddressDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SupportAddress record);

    int insertSelective(SupportAddress record);

    SupportAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SupportAddress record);

    int updateByPrimaryKey(SupportAddress record);
}