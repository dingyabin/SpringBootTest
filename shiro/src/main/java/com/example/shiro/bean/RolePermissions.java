package com.example.shiro.bean;

import java.io.Serializable;

public class RolePermissions implements Serializable {

    private static final long serialVersionUID = -5913208556156456001L;

    private Long id;

    private String roleName;

    private String permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}