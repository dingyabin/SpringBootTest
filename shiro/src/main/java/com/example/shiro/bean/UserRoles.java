package com.example.shiro.bean;

import java.io.Serializable;

public class UserRoles implements Serializable {

    private static final long serialVersionUID = -137743253496209517L;

    private Long id;

    private String username;

    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}