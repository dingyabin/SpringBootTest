package com.example.rabbit.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MrDing
 * Date: 2018/10/17.
 * Time:20:43
 */

@Configuration
public class Configration {


    @Bean
    @ConditionalOnProperty(name = "jsonConverter", havingValue = "true", prefix = "example.rabbit.converter")
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
