package com.kh.aboo.common.echo;

import java.io.IOException;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;

@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler {
	
	// 세션리스트. 세션정보는 다다름 4개
	private Vector<WebSocketSession> sessionList = new Vector<WebSocketSession>();
	// 닉네임리스트. 수동으로만듦 1개 idx가 100000
	private Vector<Object> nameList = new Vector<Object>();
	
	// [클라이언트 접속시 작동]
	// 클라이언트 연결되었을때 실행
	// 접속자 session 정보 가져온다.
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String id = "";
		if(session.getAttributes().get("admin") == null) {
			Generation generation = (Generation) session.getAttributes().get("generation");
			id = generation.getId();
			String apartmentIdx = generation.getApartmentIdx();
			//접속시에 접속자 정보 보여준다.
			//대신 접속자가 관리자라면 안내하지않는다. 어차피 받는 칸은 관리자에만 있음.
			connectList(id, apartmentIdx);
		} else if (session.getAttributes().get("generation") == null){
			Admin admin  =  (Admin) session.getAttributes().get("admin");
			id = admin.getApartmentIdx();
		}
		sessionList.add(session);
		nameList.add(id);

	}
	
	public void connectList(String id,String apartmentIdx) throws IOException {
		//이부분을 어떻게 해야할지 고민해보기..
		// Q1. 관리자 아이디를 어떻게 유동적으로 판단할것인가...
		// A -> 접속한세대의 아파트 idx기준으로 관리자를 판단해서 메시지를 보내준다. nameList에서 관리자를 아디가아닌 idx로 넣어준다.
		for (int i = 0; i < nameList.size(); i++) {
			if(nameList.get(i).equals(apartmentIdx)) {
				//관리자라면 입장자 정보 받는다
				sessionList.get(i).sendMessage(new TextMessage("[안내] "+ id +" 세대가 입장하셨습니다."));
			}
		}
	}

	
	// [클라이언트 메시지 보낼 시 작동]
	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//모든 유저에게 메시지 출력
		//하지만 우리는 1:1채팅이기에 이부분 수정해야한다.
		
		JSONObject object = new JSONObject(message.getPayload());
		// 1. 닉네임 정보 넣어주기. 받는이와 보낸이에게 보내기
		// 전송을 눌렀을때, 세션정보가 리스트의 i 번째와 같은 위치에 name을 넣어준다.
		String target = object.getString("target");
		String msg = object.getString("messeage");
		
		int fromIdx = sessionList.indexOf(session); // 보낸이..
		
		String adminId = "";
		String name = (String) nameList.get(fromIdx);
		// 보낸 세션이 admin이라면 admin에게 다 보내주기
		if(session.getAttributes().get("admin") != null) {
			int idx = sessionList.indexOf(session);
			adminId = (String) nameList.get(idx);
			name = "관리자";
		}
		
		for (int i = 0; i < sessionList.size(); i++) {
			// 닉네임이 같다면 해당 i번째 세션에 보내준다
			if(nameList.get(i).equals(target)  || sessionList.get(i).equals(session) || nameList.get(i).equals(adminId)) {
				sessionList.get(i).sendMessage(new TextMessage(name+" : "+msg));
			}
			
		}

	}
	
	// [클라이언트 접속 끊었을 시 작동]
	// 클라이언트가 연결 끊었을 때 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		int idx = sessionList.indexOf(session);
		String id = (String) nameList.get(idx);
		sessionList.remove(session);
		nameList.remove(idx);
		Generation generation = (Generation) session.getAttributes().get("generation");
		String apartmentIdx = generation.getApartmentIdx();
		disconnectList(id, apartmentIdx);

	}
	
	public void disconnectList(String id, String apartmentIdx) throws IOException {
		for (int i = 0; i < nameList.size(); i++) {
			if (nameList.get(i).equals(apartmentIdx)) {
				// 관리자라면 입장자 정보 받는다
				sessionList.get(i).sendMessage(new TextMessage("[안내] " + id + " 세대가 퇴장하셨습니다."));
			}
		}
	}
}
