package com.example.shiro.cache;

import com.example.shiro.config.RedisConfig;
import org.apache.ibatis.cache.Cache;
import org.springframework.util.SerializationUtils;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by MrDing
 * Date: 2018/9/13.
 * Time:21:45
 */
public class MybatisCache implements Cache {

    private String id;


    private int expire;


    public MybatisCache(String id) {
        this.id = id;
    }


    public int getExpire() {
        return expire;
    }


    public void setExpire(int expire) {
        this.expire = expire;
    }


    @Override
    public String getId() {
        return this.id;
    }


    @Override
    public void putObject(Object key, Object value) {
        byte[] k_bytes = (key.toString()).getBytes();
        byte[] v_bytes = SerializationUtils.serialize(value);
        RedisConfig.getAutoJedis().setex(k_bytes, expire, v_bytes);
    }


    @Override
    public Object getObject(Object key) {
        byte[] k_bytes = (key.toString()).getBytes();
        byte[] v_bytes = RedisConfig.getAutoJedis().get(k_bytes);
        return SerializationUtils.deserialize(v_bytes);
    }


    @Override
    public Object removeObject(Object key) {
        byte[] k_bytes = (key.toString()).getBytes();
        return RedisConfig.getAutoJedis().del(k_bytes);
    }


    @Override
    public void clear() {
    }


    @Override
    public int getSize() {
        return 0;
    }


    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
