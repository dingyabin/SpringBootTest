package com.example.eureka.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provider.client.service.ProviderService;
import provider.common.model.Weight;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by MrDing
 * Date: 2018/10/20.
 * Time:14:17
 */
@Slf4j
@RestController
@RequestMapping("/feign-hystrix")
public class FeignHystrixController {

    @Resource
    private ProviderService providerService;


    @RequestMapping("/api/test")
    public String feignHystrix() {
        Weight provider = providerService.provider(new Weight(11, 11,""));
        if (provider==null){
           return "null...............";
        }
        return provider.toString();
    }



}
