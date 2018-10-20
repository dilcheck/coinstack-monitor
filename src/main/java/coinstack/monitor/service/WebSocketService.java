package coinstack.monitor.service;


import coinstack.monitor.model.Block;
import coinstack.monitor.model.Transaction;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import org.springframework.stereotype.Service;

@Service
@ClientEndpoint
public class WebSocketService extends AbstractService {

	/**
	 * Websocket open. 
	 */
	@OnOpen
	public void handleOpen() {
		logger.info("client is now connected...");
	}

	/**
	 * Websocket listener.
	 * @param message server message.
	 * @throws IOException objectMapper exception.
	 */
	@OnMessage
	public void handleMessage(byte[] message) throws IOException  {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(message);
		int type = jsonNode.get("Type").asInt();
		
		if(type == 0) {
			//Block
			Block block = mapper.readValue(jsonNode.get("Payload").toString(), Block.class);
			System.out.println(block); // free to use
		}else {
			//Transaction
			Transaction transaction = mapper.readValue(jsonNode.get("Payload").toString(), Transaction.class);
			System.out.println(transaction); // free to use
		}
	}
	
	/**
	 * Websocket close.
	 */
	@OnClose
	public void handleClose() {
		logger.info("client is now disconnected...");
	}

	/**
	 * Websocket error.
	 * 
	 * @param t exception.
	 */
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
}
