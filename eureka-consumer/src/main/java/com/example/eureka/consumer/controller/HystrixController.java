package com.example.eureka.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MrDing
 * Date: 2018/10/20.
 * Time:14:17
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    private static final String TIME_OUT = "execution.isolation.thread.timeoutInMilliseconds";


    @RequestMapping("/api/test")
    @HystrixCommand(fallbackMethod = "fallback",
                    commandProperties ={@HystrixProperty(name = HystrixController.TIME_OUT, value = "2000")}
    )
    public String gateway(int flag) {
        if (flag > 5) {
            throw new RuntimeException("异常了.....");
        }
        return "ok";
    }


    public String fallback(int flag) {
        return "排队中，请稍等......";
    }


}
