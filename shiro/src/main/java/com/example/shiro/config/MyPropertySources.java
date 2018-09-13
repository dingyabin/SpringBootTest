package com.example.shiro.config;

import com.example.shiro.utils.ConfigUtil;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;


/**
 * Created by MrDing
 * Date: 2018/1/1.
 * Time:11:35
 */
public class MyPropertySources extends PropertySourcesPlaceholderConfigurer {


    @Override
    protected Properties mergeProperties() throws IOException {
        Properties properties = super.mergeProperties();
        ConfigUtil.reLoad(properties);
        return properties;
    }

}
