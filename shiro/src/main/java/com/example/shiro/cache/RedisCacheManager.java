package com.example.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * Created by MrDing
 * Date: 2018/9/14.
 * Time:1:03
 */
public class RedisCacheManager implements CacheManager {

    private RedisCache redisCache = new RedisCache();

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return redisCache;
    }

}
