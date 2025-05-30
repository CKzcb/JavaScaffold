package com.ckzcb.ape.user.websocket;

import com.ckzcb.ape.common.ws.config.WsServerConfig;
import com.ckzcb.ape.common.ws.models.WsMessage;
import com.ckzcb.ape.common.ws.models.WsMessageEncoder;
import com.ckzcb.ape.common.ws.models.WsType;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ChickenWebsocket
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/30 14:25
 * @Version 1.0
 */
@Component
@ServerEndpoint(value = "/chicken/socket", configurator = WsServerConfig.class, encoders = {WsMessageEncoder.class})
public class ChickenWebsocket {
    private static final Logger logger = LoggerFactory.getLogger(ChickenWebsocket.class);

    private static AtomicInteger onlineCount = new AtomicInteger(0);
    private static Map<String, ChickenWebsocket> chickenWebsocketMap = new ConcurrentHashMap<>();
    private Session session;
    private String userId;

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.userId = (String) endpointConfig.getUserProperties().get("userId");
        this.session = session;
        if (chickenWebsocketMap.containsKey(userId)) {
            ChickenWebsocket chickenWebsocket = chickenWebsocketMap.remove(userId);
            if (chickenWebsocket != null) {
                try {
                    chickenWebsocket.session.close();
                    onlineCount.decrementAndGet();
                } catch (Exception e) {
                    logger.error("关闭连接失败", e);
                }
            }
        }

        onlineCount.incrementAndGet();
        chickenWebsocketMap.put(userId, this);
        logger.info("当前在线人数：{}", onlineCount.get());
        WsMessage message = new WsMessage(WsType.SYSTEM, "系统消息：" + userId + "已上线", "", userId);
        sendMessage(message, session);
    }

    public void sendMessage(WsMessage message, Session session) {
        try {
            session.getBasicRemote().sendObject(message);
            logger.info("server send to client, {} -> {}", userId, message);
        } catch (Exception e) {
            logger.error("发送消息失败", e);
        }
    }

    @OnClose
    public void onClose() {
        if (chickenWebsocketMap.containsKey(userId)) {
            var chickenWebsocket = chickenWebsocketMap.remove(userId);
            onlineCount.decrementAndGet();
            if (chickenWebsocket != null) {
                try {
                    chickenWebsocket.session.close();
                } catch (Exception e) {
                    logger.error("关闭连接失败", e);
                }
            }
        }
        logger.info("当前在线人数：{}", onlineCount.get());
    }

    @OnMessage
    public void onMessage(String message) {
        logger.info("server receive from client, {} -> {}", userId, message);
        for (ChickenWebsocket chickenWebsocket : chickenWebsocketMap.values()) {
            chickenWebsocket.sendMessage(
                    new WsMessage(WsType.TEXT, message, userId, chickenWebsocket.userId), chickenWebsocket.session);
        }
    }
}
