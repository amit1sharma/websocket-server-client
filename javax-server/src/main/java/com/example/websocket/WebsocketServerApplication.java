package com.example.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocket
public class WebsocketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketServerApplication.class, args);
	}

}
