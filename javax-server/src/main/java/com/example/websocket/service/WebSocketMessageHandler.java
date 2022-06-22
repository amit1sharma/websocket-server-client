package com.example.websocket.service;

import com.example.websocket.config.WebSocketServerEndpoint;
import com.example.websocket.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.Session;
import java.util.List;

/**
 * * @author Amit Sharma
 **/
@Component
public class WebSocketMessageHandler {

    @Autowired
    private WebSocketServerEndpoint webSocketServerEndpoint;


    @PostConstruct
    public void webSocketServerEndpoint(){
        webSocketServerEndpoint.addMessageHandler(new WebSocketServerEndpoint.MessageHandler() {
            @Override
            public void handleMessage(String message, Session session) {
                prepareAndSendResponse(message, session);
            }
        });
    }

    @Autowired
    private List<Worker> workers;

    public void prepareAndSendResponse(String param, Session session){
        workers.stream().forEach(service -> {
            try {
                ResponseData rd = service.doWork(param);
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            webSocketServerEndpoint.sendMessage(rd.toString(), session);
                            System.out.println(this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

}
