package com.kh.aboo.mypage.myalarm.model.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kh.aboo.mypage.myalarm.model.repository.MyAlarmRepository;
import com.kh.aboo.mypage.myalarm.model.service.MyAlarmService;
import com.kh.aboo.mypage.myalarm.model.vo.MyAlarm;
import com.kh.aboo.user.generation.model.repository.GenerationRepository;
import com.kh.aboo.user.generation.model.vo.Generation;


@Service
public class MyAlarmServiceImpl implements MyAlarmService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final MyAlarmRepository myAlarmRepository;
	
	public MyAlarmServiceImpl(MyAlarmRepository myAlarmRepository){
		this.myAlarmRepository = myAlarmRepository;
	}

	//아파트관련 알람추가
	@Override
	public void insertAptAlarm(String issueContent, String apartmentIdx) {
		
		int res = myAlarmRepository.insertAptAlarm(issueContent, apartmentIdx);
		
		if(res > 0) {
			
			List<Generation> genList = new ArrayList<Generation>();
			genList = myAlarmRepository.selectGenerationByApt(apartmentIdx);
			/*
			 * 
			 * 
			 * for (Generation generation : genList) { if(generation.getTell() != null) {
			 * 
			 * int msg = sendAlarmMsg(generation.getTell() , issueContent);
			 * 
			 * if(msg == 202) { System.out.println("문자성공"); }else {
			 * System.out.println("문자 실패"); }
			 * 
			 * } }
			 */
			
			
			System.out.println("알람등록");
		}else {
			System.out.println("등록실패");
		}
	}

	//개인관련 알람추가
	@Override
	public void insertPvAlarm(String issueContent, String generationIdx) {
		
		int res = myAlarmRepository.insertPvAlarm(issueContent, generationIdx);
		
		if(res > 0) {
			
			Generation generation = myAlarmRepository.selectGenerationByIdx(generationIdx);
			
			/*
			 * if(generation.getTell() != null) {
			 * 
			 * int msg = sendAlarmMsg(generation.getTell() , issueContent);
			 * 
			 * if(msg == 202) { System.out.println("문자성공"); }else {
			 * System.out.println("문자 실패"); }
			 * 
			 * 
			 * }
			 */
			
			System.out.println("알람등록");
		}else {
			System.out.println("등록실패");
		}
		
	}

	
	//알람리스트 불러오기
	@Override
	public List<MyAlarm> selectIssue(String generationIdx, String apartmentIdx) {
	
		return myAlarmRepository.selectIssue(generationIdx, apartmentIdx);
	}
	
	//알람메시지 보내기
	public int sendAlarmMsg(String tell,String issueContent) {
		
		String method = "POST";
		String url = "/sms/v2/services//messages";
		String timestamp = Long.toString(System.currentTimeMillis());
		String accessKey = "";
		String secretKey = "";
		
		String signature = makeSignature(url, timestamp, method, accessKey, secretKey);
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");
		header.add("x-ncp-apigw-timestamp", timestamp);
		header.add("x-ncp-iam-access-key", accessKey);
		header.add("x-ncp-apigw-signature-v2", signature);
		
		JSONObject params = new JSONObject();
		JSONObject params2 = new JSONObject();
		JSONArray messages = new JSONArray();
		try {
			params.put("type", "SMS");
			params.put("from", "");
			params.put("content", "[ABOO:아파트를 부탁해] " + issueContent);
			params2.put("to", tell);
			messages.put(params2);
			params.put("messages", messages);
			String body = params.toString();
			
			RequestEntity<String> request = 
					RequestEntity
					.post("https://sens.apigw.ntruss.com/sms/v2/services//messages")
					.headers(header)
					.body(body);
			
			ResponseEntity<String> response = restTemplate.exchange(request, String.class);
			return response.getStatusCodeValue();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 400;
		
	}
	
	
	
	public String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey) {
		String space = " ";
		String newLine = "\n";
		
		String message = new StringBuilder().append(method).append(space)
				.append(url).append(newLine).append(timestamp).append(newLine)
				.append(accessKey).toString();
		
		SecretKeySpec signingKey;
		String encodeBase64String = "";
		
		try {
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			encodeBase64String = Base64.encodeBase64String(rawHmac);
 		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encodeBase64String;
		
	}
	

}
