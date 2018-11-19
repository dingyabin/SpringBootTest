package com.example.demo.service.impl;

import com.example.demo.aop.CustomerTransction;
import com.example.demo.bean.QueryConditon;
import com.example.demo.bean.Weight;
import com.example.demo.dao.WeightDao;
import com.example.demo.service.WeightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:18:52
 */
@Service("weightService")
public class WeightServiceImpl implements WeightService {

    @Resource
    private WeightDao weightDao;


    @Override
    @CustomerTransction
    public int insertWeight(Weight weight) {
        return weightDao.insertWeight(new Weight(33, 22, new Date()));
    }

    @Override
    public List<Weight> selectByDateRange(QueryConditon queryConditon) {
        return weightDao.selectByDateRange(queryConditon);
    }

    @Override
    public List<Weight> selectByDate(Date date) {
        return weightDao.selectByDate(date);
    }

    @Override
    public List<Weight> selectAll() {
        return weightDao.selectAll();
    }
}
