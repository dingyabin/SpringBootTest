package com.example.eurake.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 * @author dingyabin
 * @date 2018/4/10
 * Time: 14:52
 * function:
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaProviderApplication {


    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderApplication.class, args);
    }
}
