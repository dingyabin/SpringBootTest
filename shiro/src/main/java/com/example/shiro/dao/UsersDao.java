package com.example.shiro.dao;


import com.example.shiro.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UsersDao {

    User selectByUserName(@Param("userName") String userName);

}