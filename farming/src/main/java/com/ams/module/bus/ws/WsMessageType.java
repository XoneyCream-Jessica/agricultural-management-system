package com.ams.module.bus.ws;

public enum WsMessageType {
    WARNING(1),
    INFO(2),
    SUCCESS(3),
    ERROR(4),
    ;


    private int code;

    WsMessageType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
