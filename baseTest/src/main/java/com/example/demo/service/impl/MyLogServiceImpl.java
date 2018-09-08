package com.example.demo.service.impl;

import com.example.demo.bean.MyLog;
import com.example.demo.dao.MyLogDao;
import com.example.demo.service.MyLogService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:20:44
 */
@Service("myLogService")
@Scope("singleton")
public class MyLogServiceImpl implements MyLogService {


    @Resource
    private MyLogDao myLogDao;


    @Override
    public int saveMyLog(MyLog myLog) {
        return myLogDao.insertOne(myLog);
    }
}
