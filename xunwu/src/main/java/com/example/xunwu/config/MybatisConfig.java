package com.example.xunwu.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by MrDing
 * Date: 2018/9/6.
 * Time:22:13
 */
@Configuration
@MapperScan(value = "com.example.xunwu.dao")
public class MybatisConfig {


    @Bean("dataSource")
    @ConfigurationProperties(prefix = "xunwu.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }


    @Bean("transactionManager")
    public DataSourceTransactionManager transactionManager(){
       return  new DataSourceTransactionManager(dataSource());
    }


}
