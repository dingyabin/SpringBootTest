package com.example.filter;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * Created by dingyabin001
 * Date: 2018/11/1.
 * Time:14:18
 */
public class ConsumerTraceIdFilter implements Filter {


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = MDC.get("traceId");
        if (traceId == null) {
            traceId = UUID.randomUUID().toString().toUpperCase();
        }
        RpcContext.getContext().setAttachment("traceId", traceId);
        return invoker.invoke(invocation);
    }


}
