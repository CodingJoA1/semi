package kh.semiProject.chatting.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ChatHandler extends TextWebSocketHandler{
	private static List<WebSocketSession> list = new ArrayList<>();
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session , TextMessage message) throws Exception{
		String payload = message.getPayload();
		log.info("payload : " + payload);
		
		for(WebSocketSession sess : list) {
			sess.sendMessage(message);
		}
		
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
		log.info(session+" 클라 접속");
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info(session+" 클라 접속 해제");
		list.remove(session);
	}
	
	
}
