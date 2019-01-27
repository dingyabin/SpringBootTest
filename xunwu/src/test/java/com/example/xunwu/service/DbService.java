package com.example.xunwu.service;

import com.example.xunwu.BaseApplicationTests;
import com.example.xunwu.dao.UserDao;
import com.example.xunwu.model.User;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by MrDing
 * Date: 2019/1/28.
 * Time:0:46
 */
public class DbService extends BaseApplicationTests {

    @Resource
    private UserDao userDao;

    @Test
    public void dbTest(){
        User user = userDao.selectByPrimaryKey(1);
        Assert.assertNotNull(user);
        Assert.assertEquals("waliwali", user.getName());
    }


}
