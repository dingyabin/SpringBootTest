package com.example.demo;


import com.example.demo.configration.MyPropertySources;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author MrDing
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan("com.example.demo.filter")
//启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
//@EnableTransactionManagement
@MapperScan("com.example.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }


    @Bean
    @Primary
    public MyPropertySources myPropertySources() throws Exception {
        MyPropertySources myPropertySources = new MyPropertySources();
        myPropertySources.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath:**/*.properties"));
        return myPropertySources;
    }

}
