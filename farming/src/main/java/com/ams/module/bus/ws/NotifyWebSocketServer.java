package com.ams.module.bus.ws;

import com.ams.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@ServerEndpoint(value = "/websocket/notify", encoders = {WsMessageEncoder.class})
@Slf4j
@Scope("singleton")
public class NotifyWebSocketServer {

    private static List<NotifyWebSocketServer> webSocketConnections = new ArrayList<>();

    @PostConstruct
    public void init() {
        log.info("notify ws server init success");
    }

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    protected Session session;

    /**
     * 连接建立成
     * 功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        if (this.session != null) {
            try {
                this.session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.session = session;
        webSocketConnections.add(this);
        sendMessage(new WsMessage(WsMessageType.INFO, "连接后台服务器成功，服务器时间：" + DateUtil.getTime()));
    }

    /**
     * 连接关闭
     * 调用的方法
     */
    @OnClose
    public void onClose() throws IOException {
        session.close();
        session = null;
    }

    /**
     * 收到客户端消
     * 息后调用的方法
     *
     * @param message 客户端发送过来的消息
     **/
    @OnMessage
    public void onMessage(String message, Session session) {

    }


    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 实现服务
     * 器主动推送
     */
    public void sendMessage(WsMessage message) {
        try {
            for (NotifyWebSocketServer server : webSocketConnections) {
                Session serverSession = server.session;
                if (serverSession != null && serverSession.isOpen()) {
                    serverSession.getBasicRemote().sendObject(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }
}
