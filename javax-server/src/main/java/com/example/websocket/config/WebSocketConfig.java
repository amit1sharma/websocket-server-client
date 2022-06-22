package com.example.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
@EnableWebSocket
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpoint() {
        ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
        return new ServerEndpointExporter();
    }

    @Bean
    public CustomSpringConfigurator customSpringConfigurator() {
        return new CustomSpringConfigurator();
    }


}