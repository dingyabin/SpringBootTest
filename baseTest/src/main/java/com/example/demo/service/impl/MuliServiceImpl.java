package com.example.demo.service.impl;

import com.example.demo.aop.CustomerTransction;
import com.example.demo.bean.Weight;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.TbUserDao;
import com.example.demo.dao.WeightDao;
import com.example.demo.service.MuliService;
import com.example.demo.service.StudentService;
import com.example.demo.service.WeightService;
import org.springframework.stereotype.Service;

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

    @Resource
    private WeightService weightService;

    @Resource
    private StudentService studentService;


    @Override
    @CustomerTransction
    public void save() {
        weightDao.insertWeight(new Weight(112,22 ,new Date()));
        studentDao.insert(new com.example.demo.bean.Student("222", "3", 3, new Date()));
        weightDao.insertWeight(new Weight(113,23 ,new Date()));
        studentDao.insert(new com.example.demo.bean.Student("223", "4", 4, new Date()));
        //throw new NullPointerException("xxxx");
    }

    @Override
    @CustomerTransction
    public void save2() {
        weightService.insertWeight(new Weight(10,20 ,new Date()));
        studentService.save(new com.example.demo.bean.Student("222", "3", 3, new Date()));
        weightService.insertWeight(new Weight(11,21 ,new Date()));
        studentService.save(new com.example.demo.bean.Student("225", "5", 3, new Date()));
        //throw new NullPointerException("xxxx");
    }


}
