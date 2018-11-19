package com.example.demo.service.impl;

import com.example.demo.aop.CustomerTransction;
import com.example.demo.bean.Student;
import com.example.demo.dao.StudentDao;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by MrDing
 * Date: 2018/11/17.
 * Time:1:39
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;



    @Override
    @CustomerTransction
    public int save(Student record) {
        return studentDao.insert(record);
    }
}
