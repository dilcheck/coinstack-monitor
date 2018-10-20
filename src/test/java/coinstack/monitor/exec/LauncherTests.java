package coinstack.monitor.exec;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import coinstack.monitor.service.WebSocketService;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = WebSocketService.class)
public class LauncherTests {

	@Autowired
	WebSocketService webSocketService;

	@Test
	public void contextLoads() {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(webSocketService, URI.create("ws://localhost:3000/websocket"));
			Thread.sleep(100000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
