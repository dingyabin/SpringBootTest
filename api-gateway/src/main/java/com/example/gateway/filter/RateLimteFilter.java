package com.example.gateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by MrDing
 * Date: 2018/10/20.
 * Time:12:49
 */
@Component
public class RateLimteFilter extends ZuulFilter {

    private final RateLimiter rateLimiter = RateLimiter.create(4);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        if (!rateLimiter.tryAcquire()){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }
}
