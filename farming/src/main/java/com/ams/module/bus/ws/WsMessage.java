package com.ams.module.bus.ws;

public class WsMessage {
    private int code;

    private String message;

    public WsMessage(WsMessageType messageType, String message) {
        this.code = messageType.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
