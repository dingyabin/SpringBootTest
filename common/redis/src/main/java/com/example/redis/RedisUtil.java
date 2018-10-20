package com.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by MrDing
 * Date: 2018/10/17.
 * Time:23:34
 */
@Slf4j
@Configuration
public class RedisUtil {

    private static JedisPool pool;


    @Bean
    @ConfigurationProperties(prefix = "spring.redis.pool")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setUsePool(true);
        factory.setPoolConfig(jedisPoolConfig());
        log.info("JedisConnectionFactory bean init success.......");
        return factory;
    }


    @Bean("jedisPool")
    public JedisPool getJedisPool() {
        JedisConnectionFactory connectionFactory = getConnectionFactory();
        RedisUtil.pool = new JedisPool(
                connectionFactory.getPoolConfig(),
                connectionFactory.getHostName(),
                connectionFactory.getPort(),
                connectionFactory.getTimeout(),
                connectionFactory.getPassword()
        );
        return RedisUtil.pool;
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