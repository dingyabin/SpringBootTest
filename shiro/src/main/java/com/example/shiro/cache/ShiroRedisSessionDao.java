package com.example.shiro.cache;

import com.example.redis.RedisUtil;
import com.example.shiro.utils.ConfigUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by MrDing
 * Date: 2018/9/13.
 * Time:22:47
 */
public class ShiroRedisSessionDao extends AbstractSessionDAO {


    private final String PREFIX="shiro_session_";


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        _saveSession(session);
        return sessionId;
    }


    private void _saveSession(Session session) {
        if (session.getId()==null){
            throw new RuntimeException("session.getId()=null");
        }
        byte[] k = getKey(session.getId().toString());
        byte[] v = SerializationUtils.serialize(session);
        session.setTimeout(1000 * ConfigUtil.getLong("shiro.session.timeout"));
        RedisUtil.getAutoJedis().setex(k, ConfigUtil.getInt("shiro.session.timeout"), v);
    }


    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        }
        byte[] k = getKey(sessionId.toString());
        byte[] v = RedisUtil.getAutoJedis().get(k);
        return (Session)SerializationUtils.deserialize(v);
    }


    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session.getId()==null){
            return;
        }
        _saveSession(session);
    }


    @Override
    public void delete(Session session) {
        if (session.getId()==null){
            return;
        }
        RedisUtil.getAutoJedis().del(getKey(session.getId().toString()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys = RedisUtil.getAutoJedis().keys(getKey("*"));
        return keys.stream().map(k -> (Session)SerializationUtils.deserialize(k)).collect(Collectors.toSet());
    }


    private byte[] getKey(String key){
        return (PREFIX + key).getBytes();
    }

}
