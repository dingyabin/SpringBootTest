package com.example.demo.dao;

import com.example.demo.aop.DataSourceType;
import com.example.demo.aop.TargetDataSource;
import com.example.demo.bean.QueryConditon;
import com.example.demo.bean.Weight;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by MrDing
 * Date: 2017/3/18.
 * Time:19:23
 */
@TargetDataSource(DataSourceType.FIRSTDATASOURCE)
public interface WeightDao {

    int insertWeight(Weight weight);

    List<Weight> selectByDateRange(QueryConditon queryConditon);

    List<Weight> selectByDate(@Param("date") Date date);

    List<Weight> selectAll();

}
