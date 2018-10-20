package com.example.eureka.consumer.controller;

import com.example.eureka.consumer.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provider.client.service.ProviderService;
import provider.common.model.Weight;

import javax.annotation.Resource;

/**
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
    private LoadBalancerClient loadBalancerClient;

    @Resource
    private ProviderService providerService;

    @Resource
    private RestService restService;


    @RequestMapping("/test")
    public Weight testConsumer() {

        ServiceInstance provider = loadBalancerClient.choose("PROVIDER");
        log.info("---------------{}:{}", provider.getHost(), provider.getPort());

        Weight weight = new Weight(23.5, 33.5, null);

        Weight weight1 = providerService.provider(weight);
        log.info("请求providerService.provider()结果是:{}", weight1);

        Weight weight2 = restService.remoteCall(weight);
        log.info("请求restService.remoteCall()结果是:{}", weight2);

        return weight1;
    }



    @RequestMapping("api/test")
    public String gateway(){
        return "ok";
    }



}
