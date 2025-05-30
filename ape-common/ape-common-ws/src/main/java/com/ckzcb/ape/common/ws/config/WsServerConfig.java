package com.ckzcb.ape.common.ws.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName WsServerConfig
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/30 14:51
 * @Version 1.0
 */
@Configuration
public class WsServerConfig extends ServerEndpointConfig.Configurator {

    @Override
    public boolean checkOrigin(String originHeaderValue) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return false;
        }
        HttpServletRequest httpServletRequest = attributes.getRequest();
        if (httpServletRequest.getParameter("userId") == null) {
            return false;
        }
        return super.checkOrigin(originHeaderValue);
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        sec.getUserProperties().put("userId", request.getParameterMap().get("userId").getFirst());
        super.modifyHandshake(sec, request, response);
    }
}
