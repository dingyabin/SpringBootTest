package com.example.shiro;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;


/**
 * @author MrDing
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan("com.example.shiro.filter")
@EnableTransactionManagement
@MapperScan("com.example.shiro.dao")
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }


    @Bean("propertySourcesPlaceholderConfigurer")
    public PropertySourcesPlaceholderConfigurer configurer() throws IOException {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:**/*.properties")
        );
        return configurer;
    }



}
