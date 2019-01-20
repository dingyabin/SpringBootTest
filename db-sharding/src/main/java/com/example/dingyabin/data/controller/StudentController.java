package com.example.dingyabin.data.controller;

import com.example.dingyabin.data.bean.Common;
import com.example.dingyabin.data.bean.Student;
import com.example.dingyabin.data.dao.CommonDao;
import com.example.dingyabin.data.dao.StudentDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by dingyabin001
 * Date: 2019/1/3.
 * Time:15:16
 */
@RestController
@RequestMapping("/student")
public class StudentController {


    @Resource
    private StudentDao studentDao;

    @Resource
    private CommonDao commonDao;

    private AtomicLong atomicLong=new AtomicLong(System.currentTimeMillis());


    @RequestMapping("/save")
    public Student save(){
        Student student1=  Student.create(atomicLong.incrementAndGet());
        studentDao.insertSelective(student1);

        Student student2=  Student.create(atomicLong.incrementAndGet());
        studentDao.insertSelective(student2);
        return student2;
    }


    @RequestMapping("/get")
    public String get(){
        Student student = studentDao.selectByPrimaryKey(atomicLong.get());
        Common common = commonDao.selectByPrimaryKey(1L);
        return student.toString()+"\n"+common.toString();
    }






}
