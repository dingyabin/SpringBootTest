package com.example.eureka.consumer.configration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import provider.client.feign.FeignIntercepter;

/**
 * Created by MrDing
 * Date: 2018/10/14.
 * Time:0:04
 */
@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignIntercepter();
    }
}
