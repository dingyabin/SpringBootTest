package com.example.demo.service.impl;

import com.example.demo.bean.QueryConditon;
import com.example.demo.bean.Weight;
import com.example.demo.dao.WeightDao;
import com.example.demo.service.WeightService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class )
    public int insertWeight(Weight weight) {
        int count = weightDao.insertWeight(new Weight(33, 22, new Date()));
        if (weight.getId() == 111) {
            throw new IllegalArgumentException("参数不合法!");
        }
        return count;
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
