package com.example.rabbit.client;

import com.example.rabbit.constant.Constant;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dingyabin001
 * Date: 2018/10/15.
 * Time:18:08
 */
@Slf4j
@Component
public class MqSender implements DisposableBean {

    private static RabbitTemplate rabbitTemplate;

    private static ExecutorService executorService = new ThreadPoolExecutor(
            128, 1024,
            1L, TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(),
            new ThreadFactoryBuilder().setNameFormat("MQ-Sender-%d").build(),
            new ThreadPoolExecutor.AbortPolicy()
    );


    @Override
    public void destroy() {
        executorService.shutdown();
    }


    public MqSender(RabbitTemplate rabbitTemplate) {
        MqSender.rabbitTemplate = rabbitTemplate;
    }


    public static void send(String key, Object message) {
        send(Constant.TOPIC_EXCHANGE, key, message);
    }


    public static void send(String exchange, String key, Object message) {
        Map<String, String> map = MDC.getCopyOfContextMap();
        executorService.execute(() -> {
            try {
                rabbitTemplate.convertAndSend(exchange, key, message, m -> {
                    String traceId = (map == null) ? null : map.get("traceId");
                    if (traceId == null) {
                        traceId = UUID.randomUUID().toString().toUpperCase();
                    }
                    m.getMessageProperties().setHeader("traceId", traceId);
                    return m;
                });
            } catch (Exception e) {
                log.error("发送失败........", e);
            }
        });
    }

}
