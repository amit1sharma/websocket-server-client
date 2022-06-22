package amt.du.clientwebsocket.mdb;

import amt.du.clientwebsocket.config.WebSocketClientConnectionMaker;
import amt.du.clientwebsocket.observer.WebSocketObserver;
import amt.du.clientwebsocket.services.FinderBean;
import amt.du.clientwebsocket.ws.WebsocketClientEndpoint;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.DeploymentException;
import java.io.IOException;

/**
 * this listens to our controller and push data to CB web socket connection
 * @author tp21037220
 *
 */
@Component
@Scope("prototype")
public class WebSocketMessageHandler implements WebSocketObserver {
	
	Logger log = Logger.getLogger(WebSocketMessageHandler.class);
	
	@Autowired
    private FinderBean finderBean;

	private WebsocketClientEndpoint websocketClientEndpoint;
	
	public WebsocketClientEndpoint getWebsocketClientEndpoint() {
		return websocketClientEndpoint;
	}
	
	@Autowired
	private WebSocketClientConnectionMaker connectionMaker;
	
	@PostConstruct
	public void setWSClient() throws Exception{
		finderBean.register(this);
		websocketClientEndpoint = connectionMaker.connectAndReturn();
		log.info("new instance created " + this);
	}
	
	/**
	 * 
	 * @param message
	 * @throws DeploymentException
	 * @throws IOException
	 * @throws Exception
	 */
	public void processMessage(String message) throws Exception{
		log.info(websocketClientEndpoint + " message received from controller and sending to ws server : "+message);
		websocketClientEndpoint.sendMessage(message);
		log.info(websocketClientEndpoint + " message sent to ws server");
	}
	
	@Override
	public void updateWebSocketClientObject(WebsocketClientEndpoint client) {
		this.websocketClientEndpoint = client;
	}

}
