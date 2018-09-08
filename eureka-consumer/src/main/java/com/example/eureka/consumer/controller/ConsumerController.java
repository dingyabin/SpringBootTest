package com.example.eureka.consumer.controller;

import com.example.eureka.consumer.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author dingyabin
 * @date 2018/4/19
 * Time: 14:53
 * function:
 */
@Slf4j
@RestController()
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource
    private RestService restService;


    @RequestMapping("/test")
    public String testConsumer(){
        String result = restService.remoteCall();
        log.info("请求的结果:{}",result);
        return result;
    }

}
