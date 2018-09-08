package com.example.eureka.consumer.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        filterChain.doFilter(request, response);
        log.info("http请求结束...");
    }
}
