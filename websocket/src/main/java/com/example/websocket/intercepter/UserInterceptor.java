package com.example.websocket.intercepter;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;

/**
 *
 * @author dingyabin
 * @date 2018/4/23
 * Time: 14:41
 * function:
 */
public class UserInterceptor extends ChannelInterceptorAdapter {



    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        super.preSend(message, channel);
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())){
            Object raw = message.getHeaders().get(StompHeaderAccessor.NATIVE_HEADERS);

        }
        return message;
    }

}
