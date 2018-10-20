package com.example.shiro.cache;

import com.example.redis.RedisUtil;
import com.example.shiro.utils.ConfigUtil;
import com.google.common.collect.Sets;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Set;

/**
 * Created by MrDing
 * Date: 2018/9/14.
 * Time:1:08
 */
public class RedisCache<K, V> implements Cache<K, V> {


    private final String PREFIX = "shiro_cache_";


    @Override
    @SuppressWarnings("unchecked")
    public V get(K key) throws CacheException {
        byte[] v = RedisUtil.getAutoJedis().get(getKey(key));
        if (v != null) {
            return (V) SerializationUtils.deserialize(v);
        }
        return null;
    }


    @Override
    public V put(K key, V value) throws CacheException {
        byte[] k = getKey(key);
        byte[] v = SerializationUtils.serialize(value);
        RedisUtil.getAutoJedis().setex(k, ConfigUtil.getInt("shiro.session.timeout"), v);
        return value;
    }


    @Override
    public V remove(K key) throws CacheException {
        V previous = get(key);
        RedisUtil.getAutoJedis().del(getKey(key));
        return previous;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<K> keys() {
        Set<byte[]> keys = RedisUtil.getAutoJedis().keys((PREFIX + "*").getBytes());
        Set<K> set = Sets.newHashSet();
        if (keys != null) {
            for (byte[] key : keys) {
                set.add((K)new String(key));
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        return null;
    }


    private byte[] getKey(K key) {
        if (key instanceof String) {
            return (PREFIX + key).getBytes();
        }
        return SerializationUtils.serialize(key);
    }

}
