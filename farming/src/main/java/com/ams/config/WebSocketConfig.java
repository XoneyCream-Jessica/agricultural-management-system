package com.ams.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfig {

    private static ServerEndpointExporter server;

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        if (server == null) {
            server = new ServerEndpointExporter();
        }
        return server;
    }
}
