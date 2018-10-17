package com.example.rabbit.consume;

import com.google.common.base.Stopwatch;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by dingyabin001
 * Date: 2018/10/15.
 * Time:17:34
 */
@Slf4j
public abstract class BaseConcumer<T> {


    @RabbitHandler
    public void consume(@Payload T message, @Headers Map<String, Object> properties, Channel channel) throws IOException {
        Stopwatch started = Stopwatch.createStarted();
        try {
            handleTraceId(message, properties);
            onMessage(message);
        } catch (Exception e) {
            log.error("BaseConcumer#consume()---->error", e);
        } finally {
            started.stop();
            Object o = properties.get(AmqpHeaders.DELIVERY_TAG);
            if (o != null) {
                channel.basicAck(Long.parseLong(o.toString()), false);
            }
            String queue = properties.get(AmqpHeaders.CONSUMER_QUEUE).toString();
            String exchenge = properties.get(AmqpHeaders.RECEIVED_EXCHANGE).toString();
            String routingKey = properties.get(AmqpHeaders.RECEIVED_ROUTING_KEY).toString();
            log.info("队列:{}, 交换机:{}, 路由键:{}, 消费完成,耗时:{}ms", queue, exchenge, routingKey, started.elapsed(TimeUnit.MILLISECONDS));
        }
    }




    private void handleTraceId(T message,Map<String, Object> properties) {
        Object traceId = properties.get("traceId");
        MDC.put("traceId", (traceId != null) ? traceId.toString() : UUID.randomUUID().toString().toUpperCase().replace("-", ""));
        log.info("收到mq消息,message={}", message);
    }



    protected abstract void onMessage(T message) throws Exception;

}
