package com.example.shiro.controller;

import com.example.shiro.config.RedisConfig;
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


    @RequestMapping("test")
    public String tets(){
        for (int i = 0; i < 200; i++) {
        RedisConfig.getAutoJedis().setex("aaaa",111,"dingaybin" );

        }
        return "111";
    }



}
