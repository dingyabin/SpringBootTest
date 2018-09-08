package com.example.shiro.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by MrDing
 * Date: 2018/9/6.
 * Time:22:13
 */

@Configuration
public class MybatisConfig {


    @Bean("dataSource")
    @ConfigurationProperties("shiro.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }


    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(){
       return  new DataSourceTransactionManager(dataSource());
    }


}
