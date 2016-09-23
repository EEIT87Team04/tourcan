package experimental;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// @ServerEndpoint("/ws/ServerSide")
public class WsDemo {
	@OnOpen
	public void welcome(Session session){
		try {
			if (session.isOpen()) {
				System.out.println();
				session.getBasicRemote().sendText("Hello, user.");
				session.getBasicRemote().sendText("This is an WebSocket demo on Tomcat.");
				session.getBasicRemote().sendText("Send any message, I will echo back.");
			}
		} catch (IOException e0) {
			try {
				session.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e0.printStackTrace();
		}
	}

	@OnMessage
	public void printout(Session session, String msg, boolean last) {
		try {
			if (session.isOpen()) {
				System.out.println();
				session.getBasicRemote().sendText("Hello, " + msg, last);
			}
		} catch (IOException e0) {
			try {
				session.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e0.printStackTrace();
		}
	}
}
