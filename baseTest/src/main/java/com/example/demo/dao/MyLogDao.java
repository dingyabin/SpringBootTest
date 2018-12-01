package com.example.demo.dao;

import com.example.demo.aop.DataSourceType;
import com.example.demo.aop.TargetDataSource;
import com.example.demo.bean.MyLog;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:20:30
 */
@TargetDataSource(DataSourceType.DS_A)
public interface MyLogDao {

    @TargetDataSource(DataSourceType.DS_A)
    int insertOne(MyLog myLog);
}
