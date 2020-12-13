package com.isharefox.share.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.ExceptionWebSocketHandlerDecorator;
import org.springframework.web.socket.handler.LoggingWebSocketHandlerDecorator;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
public class WebSocketConfig {
    /**
     * websocket java服务端
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }


//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        //异常、日志装饰器 装饰消息处理
//        registry.addHandler(new ExceptionWebSocketHandlerDecorator(new LoggingWebSocketHandlerDecorator(wsHandler())), "/websocketTest")
//                .addInterceptors(new HttpSessionHandshakeInterceptor());
//                //.setAllowedOrigins(*); 跨域
//    }
//
//    @Bean
//    public ServletServerContainerFactoryBean createWebSocketContainer() {
//        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//        container.setMaxTextMessageBufferSize(8192);
//        container.setMaxBinaryMessageBufferSize(8192);
//        return container;
//    }
//
//    @Bean
//    public WebSocketHandler wsHandler() {
//        return new TextWebSocketHandler() {
//
//            @Override
//            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//                System.out.println("建立连接");
//                super.afterConnectionEstablished(session);
//            }
//
//            @Override
//            protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//                System.out.println("接收到客户端的消息" + message.getPayload());
//            }
//        };
//    }
}