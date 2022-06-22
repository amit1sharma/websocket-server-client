package com.example.websocket.config;

import org.springframework.stereotype.Component;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/amt", configurator = CustomSpringConfigurator.class)
@Component
public class WebSocketServerEndpoint {

  private MessageHandler messageHandler;

  public void addMessageHandler(MessageHandler msgHandler) {
    System.out.println(this);
    this.messageHandler = msgHandler;
  }

  @OnOpen
  public void onOpen(Session session) {
    System.out.println(String.format("WebSocket opened: %s", session.getId()));
  }

  @OnMessage
  public void onMessage(String txt, Session session) throws IOException {
    System.out.println(String.format("Message received: %s", txt));
    System.out.println(this);
    if(this.messageHandler!=null)
      this.messageHandler.handleMessage(txt, session);
  }

  @OnClose
  public void onClose(CloseReason reason, Session session) {
    System.out.println(String.format("Closing a WebSocket (%s) due to %s", session.getId(), reason.getReasonPhrase()));
  }

  @OnError
  public void onError(Session session, Throwable t) {
    t.printStackTrace();
    System.out.println(String.format("Error in WebSocket session %s%n", session == null ? "null" : session.getId()));
  }

  synchronized public void sendMessage(String message, Session session) throws Exception {
      if (session != null && session.isOpen()) {
        try {
          session.getBasicRemote().sendText(message);
        }catch(Throwable th){
          th.printStackTrace();
          throw th;
        }
      }else{
        throw new Exception("Unable to send message connection is closed.");
      }
  }
  public static interface MessageHandler {
    public void handleMessage(String message, Session session);
  }
}