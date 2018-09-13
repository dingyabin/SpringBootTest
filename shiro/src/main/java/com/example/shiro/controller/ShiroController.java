package com.example.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MrDing
 * Date: 2018/9/8.
 * Time:0:50
 */
@RestController
@RequestMapping("/shiro")
public class ShiroController {


    @RequestMapping("/unlogin")
    public String unlogin(){
        return "未登录!";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "权限不足!";
    }


    @RequestMapping("/auth")
    public String auth(){
        return "auth!";
    }


    //@RequiresRoles({"admin"})
    @RequestMapping("/role")
    public String role(){
        return "role ok!";
    }


    @RequestMapping("/login")
    public String login(String name, String pwd) {
        UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return "login success";
    }


    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout success";
    }


}
