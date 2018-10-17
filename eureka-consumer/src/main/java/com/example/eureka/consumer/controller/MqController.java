package com.example.eureka.consumer.controller;

import com.example.eureka.consumer.test.TestService;
import com.example.rabbit.client.MqSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dingyabin
 * @date 2018/4/19
 * Time: 14:53
 * function:
 */
@Slf4j
@RestController()
@RequestMapping("/mq")
public class MqController {

    @Resource
    private TestService testService;



    @RequestMapping("/send")
    public String testMq() {
        testService.getName();

        for (int i = 0; i < 100; i++) {
            MqSender.send("ding.topic.key", "this is a messge_"+i);
        }

        return "ok!";
    }


}
