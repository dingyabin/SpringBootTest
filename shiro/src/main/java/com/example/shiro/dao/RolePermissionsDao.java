package com.example.shiro.dao;


import com.example.shiro.bean.RolePermissions;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;


public interface RolePermissionsDao {


    List<RolePermissions> selectByRoleName(@Param("roleName") String roleName);

    List<RolePermissions> selectByRoleNameSet(@Param("roleNameSet") Set<String> roleNameSet);


}