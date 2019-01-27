package com.example.xunwu.dao;

import com.example.xunwu.model.HousePicture;

public interface HousePictureDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HousePicture record);

    int insertSelective(HousePicture record);

    HousePicture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousePicture record);

    int updateByPrimaryKey(HousePicture record);
}