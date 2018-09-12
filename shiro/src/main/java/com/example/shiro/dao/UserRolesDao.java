package com.example.shiro.dao;


import com.example.shiro.bean.UserRoles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRolesDao {

    List<UserRoles> selectByUserName(@Param("userName") String userName);

}