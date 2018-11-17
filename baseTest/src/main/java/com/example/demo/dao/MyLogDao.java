package com.example.demo.dao;

import com.example.demo.aop.DataSourceType;
import com.example.demo.aop.TargetDataSource;
import com.example.demo.bean.MyLog;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:20:30
 */
@TargetDataSource(DataSourceType.FIRSTDATASOURCE)
public interface MyLogDao {

    @TargetDataSource(DataSourceType.FIRSTDATASOURCE)
    int insertOne(MyLog myLog);
}
