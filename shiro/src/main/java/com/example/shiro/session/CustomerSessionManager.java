package com.example.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * Created by MrDing
 * Date: 2018/9/14.
 * Time:0:30
 */
public class CustomerSessionManager extends DefaultWebSessionManager {

    //可防止同一次请求中多次重复从sessionDao中读取同一个Session
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Session session = null;
        ServletRequest servletRequest = null;
        Serializable sessionId = getSessionId(sessionKey);
        if (sessionKey instanceof WebSessionKey) {
            servletRequest = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if (servletRequest != null && sessionId != null) {
            session = (Session) servletRequest.getAttribute(sessionId.toString());
        }
        if (session == null) {
            session = retrieveSessionFromDataSource(sessionId);
        }
        if (servletRequest != null && sessionId != null) {
            servletRequest.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
