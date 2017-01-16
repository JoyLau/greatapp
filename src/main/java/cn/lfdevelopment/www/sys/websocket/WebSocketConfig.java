/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by LiuFa on 2017/1/16.
 * cn.lfdevelopment.www.sys.websocket
 * DevelopmentApp
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/anon/socket").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
//        registry.setApplicationDestinationPrefixes("/websocket");
    }

}
