package amt.du.clientwebsocket.services;

import amt.du.clientwebsocket.mdb.WebSocketMessageHandler;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FinderBean {
     private Set<WebSocketMessageHandler> allPrototypeTypeBeans = new HashSet<>();

     public void register(WebSocketMessageHandler beanToRegister) {
          this.allPrototypeTypeBeans.add(beanToRegister);
     }
     
     public Set<WebSocketMessageHandler> getAllPrototypeTypeBeans(){
    	 return allPrototypeTypeBeans;
     }
}