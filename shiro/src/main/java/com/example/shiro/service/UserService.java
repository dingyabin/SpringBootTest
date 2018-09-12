package com.example.shiro.service;

import com.example.shiro.bean.User;

/**
 * Created by MrDing
 * Date: 2018/9/12.
 * Time:22:44
 */
public interface UserService {

    User selectByUserName(String userName);

    User selectByUserNameWithRolesAndPermissions(String userName);

}
