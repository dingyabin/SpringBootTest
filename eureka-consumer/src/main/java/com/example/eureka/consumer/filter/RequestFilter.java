package com.example.eureka.consumer.filter;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author dingyabin
 * @date 2018/4/19
 * Time: 16:22
 * function:
 */
@Slf4j
@WebFilter("/*")
@Order(value = 1)
public class RequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("收到http请求...");
        String traceId = request.getHeader("traceId");
        if (Strings.isNullOrEmpty(traceId)) {
            traceId = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
        }
        MDC.put("traceId", traceId);
        filterChain.doFilter(request, response);
        log.info("http请求结束...");
    }
}
