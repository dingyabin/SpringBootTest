package com.example.eureka.consumer.mq;

import com.example.rabbit.constant.Constant;
import com.example.rabbit.consume.BaseConcumer;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by dingyabin001
 * Date: 2018/10/15.
 * Time:15:41
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        key = "ding.topic.key",
        value = @Queue(value = "ding.topic.queue", durable = "true", autoDelete = "false"),
        exchange = @Exchange(value = Constant.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC)
))
public class MqConsumeService extends BaseConcumer<String> {


    @Override
    protected void onMessage(String message) throws Exception {
        System.out.println("收到信息:" + message);
        Thread.sleep(300) ;
    }
}
