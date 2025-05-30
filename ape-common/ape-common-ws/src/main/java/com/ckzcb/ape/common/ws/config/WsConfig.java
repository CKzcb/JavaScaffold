package com.ckzcb.ape.common.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassName WsConfig
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/30 14:52
 * @Version 1.0
 */
@Configuration
public class WsConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
