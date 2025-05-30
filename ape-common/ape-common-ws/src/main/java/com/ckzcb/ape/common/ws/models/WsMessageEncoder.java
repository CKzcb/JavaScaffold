package com.ckzcb.ape.common.ws.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

/**
 * @ClassName WsMessageEncoder
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/30 15:38
 * @Version 1.0
 */

public class WsMessageEncoder implements Encoder.Text<WsMessage> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String encode(WsMessage object) throws EncodeException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
