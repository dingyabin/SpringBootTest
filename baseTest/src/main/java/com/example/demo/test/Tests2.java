package com.example.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Properties;


/**
 * Created by MrDing
 * Date: 2017/12/31.
 * Time:23:42
 */
@RunWith(JUnit4.class)
public class Tests2 {

    @Test
    public void test2() throws Exception {
        PathMatchingResourcePatternResolver defaultResourceLoader = new PathMatchingResourcePatternResolver();
        Resource[] resource = defaultResourceLoader.getResources("classpath:**/*.properties");
        for (Resource resource1 : resource) {
            boolean exists = resource1.exists();
            System.out.println(exists);

            System.out.println(resource1.getURL());

            String filename = resource1.getFilename();
            System.out.println(filename);

            Properties properties = new Properties();
            properties.load(resource1.getInputStream());

            System.out.println(properties);
        }
    }
}
