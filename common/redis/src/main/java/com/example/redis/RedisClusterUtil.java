package com.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by MrDing
 * Date: 2019/1/25.
 * Time:2:11
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "enable", havingValue = "true", prefix = "example.jedisCluster")
public class RedisClusterUtil implements InitializingBean, DisposableBean {

    @Value("${example.jedisCluster.hostAndPorts}")
    private String hostAndPorts;

    private static JedisCluster jedisCluster;


    @Bean("jedisClusterPoolConfig")
    @ConfigurationProperties(prefix = "example.redis.cluster.pool")
    public JedisPoolConfig jedisClusterPoolConfig() {
        return new JedisPoolConfig();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isBlank(hostAndPorts)) {
            return;
        }
        String[] split = hostAndPorts.split(",");
        if (split.length == 0) {
            return;
        }
        Set<HostAndPort> set = new HashSet<>();
        for (String hostAndPort : split) {
            String[] hostAndPortArray = hostAndPort.trim().split(":");
            if (hostAndPortArray.length != 2) {
                continue;
            }
            set.add(new HostAndPort(hostAndPortArray[0], Integer.parseInt(hostAndPortArray[1].trim())));
        }
        if (set.isEmpty()) {
            return;
        }
        jedisCluster = new JedisCluster(set, jedisClusterPoolConfig());
    }


    public static JedisCluster getJedisCluster() {
        return jedisCluster;
    }


    @Override
    public void destroy() throws Exception {
        if (jedisCluster != null) {
            jedisCluster.close();
        }
    }
}
