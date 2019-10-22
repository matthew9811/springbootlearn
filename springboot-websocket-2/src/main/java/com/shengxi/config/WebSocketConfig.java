package com.shengxi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 开启使用STOMP，用来传输基于代理的消息，此时控制器支持使用
 * 启用@messageMapping 就像使用 @RequestMapping 一样
 *
 * @author yan
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * 注册STOMP 协议的节点（endpoint） 并映射到指定的URL
     * 注册一个 STOMP 的 endpoint ，并指定使用 SockJS协议
     *
     * @param registry 注册器
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/endpointLeung").withSockJS();
    }

    /**
     * 配置消息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //广播式应配置一个 /topic 的消息代理
        registry.enableSimpleBroker("/topic");
    }
}
