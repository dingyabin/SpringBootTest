package com.example.demo.service.impl;

import com.example.demo.bean.Weight;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.WeightDao;
import com.example.demo.service.MuliService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by MrDing
 * Date: 2018/11/17.
 * Time:3:33
 */
@Service
public class MuliServiceImpl implements MuliService {

    @Resource
    private WeightDao weightDao;

    @Resource
    private StudentDao studentDao;


    @Override
    //@Transactional
    public void save() {
        weightDao.insertWeight(new Weight(10,2 ,new Date()));
        studentDao.insert(new com.example.demo.bean.Student("222", "3", 3, new Date()));
        //throw new NullPointerException("xxxx");
    }


}
