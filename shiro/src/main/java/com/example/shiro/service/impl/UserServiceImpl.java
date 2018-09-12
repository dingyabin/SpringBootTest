package com.example.shiro.service.impl;

import com.example.shiro.bean.RolePermissions;
import com.example.shiro.bean.User;
import com.example.shiro.bean.UserRoles;
import com.example.shiro.dao.RolePermissionsDao;
import com.example.shiro.dao.UserRolesDao;
import com.example.shiro.dao.UsersDao;
import com.example.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by MrDing
 * Date: 2018/9/12.
 * Time:22:49
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UsersDao usersDao;

    @Resource
    private UserRolesDao userRolesDao;

    @Resource
    private RolePermissionsDao rolePermissionsDao;


    @Override
    public User selectByUserName(String userName) {
        return usersDao.selectByUserName(userName);
    }

    @Override
    public User selectByUserNameWithRolesAndPermissions(String userName) {
        User user = usersDao.selectByUserName(userName);
        if (user == null) {
            return null;
        }
        List<UserRoles> userRoles = userRolesDao.selectByUserName(userName);
        userRoles.forEach(role -> user.getRoles().add(role.getRoleName()));
        if (user.getRoles().size() > 0) {
            List<RolePermissions> permissions = rolePermissionsDao.selectByRoleNameSet(user.getRoles());
            permissions.forEach(permit->user.getPermissions().add(permit.getPermission()));
        }
        return user;
    }
}
