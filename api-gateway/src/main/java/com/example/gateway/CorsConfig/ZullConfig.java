package com.example.gateway.CorsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * Created by MrDing
 * Date: 2018/10/20.
 * Time:13:09
 */
@Configuration
public class ZullConfig {


    @Bean
    public CorsFilter urlBasedCorsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setMaxAge(300L);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);

    }


}
