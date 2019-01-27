package com.example.xunwu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.example")
public class XunWuApplication {

    public static void main(String[] args) {
        SpringApplication.run(XunWuApplication.class, args);
    }

}

