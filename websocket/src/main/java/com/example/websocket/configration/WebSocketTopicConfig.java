package com.example.websocket.configration;

import com.example.websocket.intercepter.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by dingyabin on 2018/4/20.
 * Time: 15:11
 * function:
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketTopicConfig extends AbstractWebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/webSocketServer").withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        super.configureMessageBroker(registry);
        registry.enableSimpleBroker("/topic");
    }


    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        super.configureClientOutboundChannel(registration);
        registration.setInterceptors(createUserInterceptor());

    }

    @Bean
    public UserInterceptor createUserInterceptor() {
        return new UserInterceptor();
    }



}
