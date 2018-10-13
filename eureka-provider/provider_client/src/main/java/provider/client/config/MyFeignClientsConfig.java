package provider.client.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MrDing
 * Date: 2018/10/14.
 * Time:0:08
 */
@Configuration
public class MyFeignClientsConfig {

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }


    @Bean
    public Request.Options options() {
        return new Request.Options(10000, 10000);
    }




}
