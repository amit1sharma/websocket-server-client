package amt.du.clientwebsocket.config;

import amt.du.clientwebsocket.ws.WebsocketClientEndpoint;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class WebSocketClientConnectionMaker {
	
	Logger log = Logger.getLogger(WebSocketClientConnectionMaker.class);
	
	@Value("${cb.keystore.path}")
	private String keyStorePath;
	
	@Value("${cb.keystore.password}")
	private String keyStorePassword;
	
	@Value("${cb.websocket.protocol}")
	private String cbProtocol;
	
	@Value("${cb.websocket.domain}")
	private String cbDomain;
	
	@Value("${cb.websocket.uri}")
	private String cbURI;
	
	@Value("${pong.timeout}")
	private long timeout;
	
	{
		log.info("WebSocketClientConnectionMaker object created");
	}


	public WebsocketClientEndpoint connectAndReturn() throws Exception{
		try {
			log.info("Connecting to WS server");
			
			String requestParams="static data";
			//URI endpointURI = new URI(cbProtocol, cbDomain, cbURI + requestParams, null, null);
			
			URI endpointURI = new URI(cbProtocol, cbDomain, cbURI, null, null);

			WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(endpointURI);
			
			clientEndPoint.timeout=timeout;
			log.info("registering message handler");
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {

                	log.info("message received by websocket "+ message);
                }
            });
            return clientEndPoint;
        } catch (Exception ex) {
            log.info("URISyntaxException exception: " ,ex);
            return null;
        }
	}
}
