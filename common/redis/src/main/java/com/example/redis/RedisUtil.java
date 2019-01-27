package com.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * Created by MrDing
 * Date: 2018/10/17.
 * Time:23:34
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "enable", havingValue = "true", prefix = "example.jedis")
public class RedisUtil implements InitializingBean {

    private static JedisPool pool;

    @Value("${example.redis.hostName}")
    private String host;

    @Value("${example.redis.password}")
    private String pwd;

    @Value("${example.redis.port}")
    private int port;


    @Bean("jedisPoolConfig")
    @ConfigurationProperties(prefix = "example.redis.pool")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }


    @Override
    public void afterPropertiesSet(){
        RedisUtil.pool = new JedisPool(jedisPoolConfig(), host, port, Protocol.DEFAULT_TIMEOUT, pwd);
    }

    /**
     * 创建代理的redis对象，代理类中实现自动关闭close()
     * @return Jedis的代理对象
     */
    public static Jedis getAutoJedis() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Jedis.class);
        enhancer.setCallback((MethodInterceptor) (o, method, args, methodProxy) -> {
            Object result = null;
            Jedis jedis = null;
            try {
                jedis = RedisUtil.pool.getResource();
                result = method.invoke(jedis, args);
            } catch (Exception e) {
                log.error("jedis.{}()出错,args={}", method.getName(), args, e);
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
            return result;
        });
        return (Jedis) enhancer.create();
    }

}
