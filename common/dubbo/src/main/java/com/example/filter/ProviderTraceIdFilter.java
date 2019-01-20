package com.example.filter;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * Created by dingyabin001
 * Date: 2018/11/1.
 * Time:14:18
 */
public class ProviderTraceIdFilter implements Filter {


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = RpcContext.getContext().getAttachment("traceId");
        if (traceId == null) {
            traceId = UUID.randomUUID().toString().toUpperCase();
        }
        MDC.put("traceId", traceId);
        return invoker.invoke(invocation);
    }


}
