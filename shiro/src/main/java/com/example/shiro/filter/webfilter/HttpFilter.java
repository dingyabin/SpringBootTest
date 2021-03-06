package com.example.shiro.filter.webfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Created by MrDing
 * Date: 2018/11/15.
 * Time:23:17
 */
@Order(0)
@WebFilter("/*")
public class HttpFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(HttpFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        HttpServletRequestWrapper request = new HttpServletRequestWrapper(httpServletRequest);
        HttpServletResponseWrapper reponse = new HttpServletResponseWrapper(httpServletResponse);
        String traceId = request.getHeader("traceId");
        if (traceId == null) {
            traceId = UUID.randomUUID().toString().replaceAll("-", "");
        }
        MDC.put("traceId", traceId);
        logger.info("收到http请求，ip={}, method={}, url={},header={}",
                getRealIpAddress(request),
                request.getMethod(),
                request.getRequestURL().toString(),
                getRequestHeader(request)
        );
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        reponse.addHeader("traceId", traceId);
        stopWatch.stop();
        logger.info("返回response信息:header={},cost={}ms", getResponseHeader(reponse), stopWatch.getTotalTimeMillis());
    }


    private String getRequestHeader(HttpServletRequestWrapper requestWrapper) {
        StringBuilder header = new StringBuilder("{");
        Enumeration<String> names = requestWrapper.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            ;
            header.append(String.format("\"%s \": \"%s\", ", name, requestWrapper.getHeader(name)));
        }
        header.append("}");

        return header.toString();
    }


    private String getResponseHeader(HttpServletResponseWrapper response) {
        StringBuilder header = new StringBuilder("{");
        Collection<String> headerNames = response.getHeaderNames();
        headerNames.forEach(name -> header.append(String.format("\"%s\" :\"%s\", ", name, response.getHeader(name))));
        header.append("}");
        ;
        return header.toString();
    }


    private String getRealIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-UserLoginDTO-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-UserLoginDTO-IP");
            ;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
