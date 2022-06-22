package amt.du.clientwebsocket.controller;

import amt.du.clientwebsocket.mdb.WebSocketMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MakeWebSocketRequest{
	
	@Autowired
	private WebSocketMessageHandler immediatePaymentMessageListerner;

	@ResponseBody
	@RequestMapping(value="/message", method=RequestMethod.GET)
	public String sendMQ(@RequestParam(value="message") String message) throws Exception{
		immediatePaymentMessageListerner.processMessage(message);
		return "message sent";
	}

	@ResponseBody
	@RequestMapping(value="/")
	public String home() {
		return "working fine";
	}

	
}