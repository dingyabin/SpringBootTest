package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by MrDing
 * Date: 2018/10/20.
 * Time:11:13
 */
@SpringBootApplication
@EnableZuulProxy
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class,args);
    }
}
