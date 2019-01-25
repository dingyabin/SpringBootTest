package com.example.demo.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by MrDing
 * Date: 2019/1/25.
 * Time:20:11
 */
public class TestRedis {


    public static void main(String[] args) {


        Set<HostAndPort> set = new HashSet<>();


        set.add(new HostAndPort("192.168.1.105", 7000));
        set.add(new HostAndPort("192.168.1.105", 7001));
        set.add(new HostAndPort("192.168.1.105", 7002));
        set.add(new HostAndPort("192.168.1.105", 7003));
        set.add(new HostAndPort("192.168.1.105", 7004));
        set.add(new HostAndPort("192.168.1.105", 7005));




        JedisCluster jedisCluster = new JedisCluster(set );

        String hello = jedisCluster.get("hello");
        System.out.println(hello);
    }


}
