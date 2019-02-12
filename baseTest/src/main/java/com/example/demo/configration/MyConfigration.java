package com.example.demo.configration;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.demo.datasource.DynamicDataSource;
import com.example.demo.model.Student;
import com.github.pagehelper.PageInterceptor;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

import static com.example.demo.aop.DataSourceType.DS_A;
import static com.example.demo.aop.DataSourceType.DS_B;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:0:45
 */
@Slf4j
@Configuration
public class MyConfigration {


    @Value("${mybatis.mapperLocations}")
    public String mapperXmlLocation;


    @Bean
    @ConfigurationProperties("student")
    public Student student() {
        System.out.println("student........................init");
        return new Student();
    }


    @Bean
    public Student student2() {
        //此处student()并不会new 一个新的student，而是会用上面的返回值
        //换句话说:(student2()==student())=true
        Student student = student();
        student.setName("update");
        //student(),student2()的name都是“update”
        return student;
    }


    /**
     * spring会自动把实现mybatis拦截器的类给注入SqlSessionFactoryBean
     *
     * @return PageInterceptor
     */
    @Bean
    public PageInterceptor getPageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.put("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }


    @Bean("ds_a")
    @ConfigurationProperties("ds-a.datasource")
    public DataSource firstDataSource() {
        return new DruidDataSource();
    }


    @Bean("ds_b")
    @ConfigurationProperties("ds-b.datasource")
    public DataSource secDataSource() {
        return new DruidDataSource();
    }


    @Primary
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSources = Maps.newHashMap();
        dataSources.put(DS_A.getType(), firstDataSource());
        dataSources.put(DS_B.getType(), secDataSource());
        dynamicDataSource.setTargetDataSources(dataSources);
        dynamicDataSource.setDefaultTargetDataSource(firstDataSource());
        return dynamicDataSource;
    }


}
