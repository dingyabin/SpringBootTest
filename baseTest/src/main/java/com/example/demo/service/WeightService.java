package com.example.demo.service;


import com.example.demo.bean.QueryConditon;
import com.example.demo.bean.Weight;

import java.util.Date;
import java.util.List;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:18:51
 */
public interface WeightService {

    int insertWeight(Weight weight);

    List<Weight> selectByDateRange(QueryConditon queryConditon);

    List<Weight> selectByDate(Date date);

    List<Weight> selectAll();

}
