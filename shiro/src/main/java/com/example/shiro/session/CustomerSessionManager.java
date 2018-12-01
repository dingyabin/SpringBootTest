package com.example.shiro.session;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by MrDing
 * Date: 2018/9/14.
 * Time:0:30
 */
public class CustomerSessionManager extends DefaultWebSessionManager {

    private final String TOKEN = "authorization-token";

    @Override
    protected void onStart(Session session, SessionContext context) {
        this.storeSessionId(session, context);
        super.onStart(session, context);
    }


    private void storeSessionId(Session session, SessionContext context) {
        if (!WebUtils.isHttp(context)) {
            return;
        }
        Serializable sessionId = session.getId();
        if (sessionId == null) {
            throw new IllegalArgumentException("sessionId cannot be null when persisting for subsequent requests.");
        }
        HttpServletResponse response = WebUtils.getHttpResponse(context);
        response.addHeader(TOKEN, sessionId.toString());
    }


    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 从请求头中获取token
        String token = WebUtils.toHttp(request).getHeader(TOKEN);
        // 判断是否有值
        if (StringUtils.isNotBlank(token)) {
            // 设置当前session状态
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "url");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        }
        return super.getSessionId(request, response);
    }


    //可防止同一次请求中多次重复从sessionDao中读取同一个Session
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        ServletRequest servletRequest = null;
        Serializable sessionId = getSessionId(sessionKey);
        if (sessionKey instanceof WebSessionKey) {
            servletRequest = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if (servletRequest != null && sessionId != null) {
            Session session = (Session) servletRequest.getAttribute(sessionId.toString());
            if (session != null) {
                return session;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if (servletRequest != null && sessionId != null) {
            servletRequest.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
