package com.ckzcb.ape.common.ws.models;

import lombok.Getter;

/**
 * @ClassName WsType
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/30 15:28
 * @Version 1.0
 */
@Getter
public enum WsType {
    SYSTEM(0, "系统"),
    TEXT(1, "文本"),
    BINARY(2, "二进制"),
    PING(3, "ping"),
    PONG(4, "pong"),
    CLOSE(5, "关闭"),
    UNKNOWN(6, "未知");;
    private int type;
    private String name;

    private WsType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static WsType getByType(int type) {
        for (WsType wsType : WsType.values()) {
            if (wsType.type == type) {
                return wsType;
            }
        }
        return null;
    }

}
