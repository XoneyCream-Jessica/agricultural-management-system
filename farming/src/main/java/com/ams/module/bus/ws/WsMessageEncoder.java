package com.ams.module.bus.ws;

import com.alibaba.fastjson.JSONObject;


import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


public class WsMessageEncoder implements Encoder.Text<WsMessage> {


    @Override
    public String encode(WsMessage message) throws EncodeException {
        try {
            return JSONObject.toJSONString(message);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        //可忽略
    }

    @Override
    public void destroy() {
        //可忽略
    }
}
