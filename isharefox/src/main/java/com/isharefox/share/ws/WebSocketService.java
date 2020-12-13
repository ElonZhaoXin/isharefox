package com.isharefox.share.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试WebSocket
 * 这里的@Component注解,只是让WebSocketConfig去识别ServerEndpoint
 * 每个websocketsession连接都会创建一个新的实例
 *
 * @see com.isharefox.share.config.WebSocketConfig
 */
@ServerEndpoint("/websocket/{resourceId}/{orderId}")
@Component
@Slf4j
public class WebSocketService {

    /**
     * 用来存放每个订单客户端的连接会话session
     * key：orderId
     */
    private static ConcurrentHashMap<String, Session> ORDER_SESSION_MAP = new ConcurrentHashMap<>(256);

    /**
     * 资源编号
     */
    private String resouceId;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 在客户初次连接时触发
     * 这里会为客户端创建一个session，这个session并不是我们所熟悉的httpsession
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("orderId") String orderId,
                       @PathParam("resourceId") String resourceId,
                       Session session) {
        log.info("Websocket订单支付创建,订单号:{}", orderId);
        ORDER_SESSION_MAP.put(orderId, session);
        this.orderId = orderId;
        this.resouceId = resourceId;
    }

    /**
     * 在客户端与服务器端断开连接时触发
     */
    @OnClose
    public void onClose(Session session) {
        ORDER_SESSION_MAP.remove(this.orderId);
        log.info("Websocket订单支付关闭,订单号:{}", orderId);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param session
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("Websocket 订单[{}]发送消息:{}", orderId, message);
        //todo
    }

    /**
     * 给指定订单客户端页面发送消息
     *
     * @param message
     */
    public static void pushMessage(String orderId, String message) {
        try {
            sendMessage(ORDER_SESSION_MAP.get(orderId), message);
        } catch (IOException e) {
            log.error("Websocket 发送点对点订单消息，发生IO异常", e);
        }
    }

    /**
     * 给所有在线客户端推送消息
     * @param message
     */
    public static void pushMessage(String message) {
        ORDER_SESSION_MAP.values().forEach(session -> {
            try {
                sendMessage(session, message);
            } catch (IOException e) {
                log.error("Websocket 推送主题消息，发生IO异常", e);
            }
        });
    }

    /**
     * 给客户端页面发送消息
     *
     * @param message
     */
    public static void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }


    /**
     * 给所有客户端发送消息
     *
     * @param message
     */
    public static void groupMessage(String message) {
        for (Session session : ORDER_SESSION_MAP.values()) {
            try {
                sendMessage(session, message);
            } catch (IOException e) {
                log.error("Websocket 发送订单消息，发生IO异常", e);
            }
        }
    }

    /**
     * 获取当前连接数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return ORDER_SESSION_MAP.size();
    }

}