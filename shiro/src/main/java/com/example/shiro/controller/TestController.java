package com.example.shiro.controller;

import com.example.redis.RedisClusterUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MrDing
 * Date: 2019/1/25.
 * Time:19:19
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/1")
    public String test(){
        String hello = RedisClusterUtil.getJedisCluster().get("hello");
        return "ok!";
    }
}
