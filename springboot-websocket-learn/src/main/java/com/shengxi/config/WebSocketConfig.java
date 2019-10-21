package com.shengxi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author yan
 * 配置广播式websocket。
 * 1.0方式：继承AbstractWebSocketMessageBrokerConfigurer
 * 2.0方案 实现WebSocketMessageBrokerConfigurer
 * <p>
 * EnableWebSocketMessageBroker注解开启stomp协议。
 * 这样一来，Controller才会支持@MessageMapping
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * 指定stomp的endpoint，指定使用sockjs协议。
     *
     * @param registry StompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/endpointWisely").withSockJS();
    }

    /**
     * 广播式消息代理
     * @param registry MessageBrokerRegistry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableStompBrokerRelay("/topic");
    }
}
