package com.kh.aboo.user.generation.model.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.aboo.common.code.Configcode;
import com.kh.aboo.common.mail.MailSender;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.user.generation.model.repository.GenerationRepository;
import com.kh.aboo.user.generation.model.service.GenerationService;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.generationWon.model.vo.GenerationWon;

@Service
public class GenerationServiceImpl implements GenerationService {

	@Autowired
	private MailSender mail;

	@Autowired
	private RestTemplate http;

	@Autowired
	private PasswordEncoder encoder;

	private final GenerationRepository generationRepository;

	public GenerationServiceImpl(GenerationRepository generationRepository) {
		this.generationRepository = generationRepository;
	}

	@Override
	public Generation selectGenerationForAuth(Generation generation) {

		Generation authInfo = generationRepository.selectGenerationForAuth(generation.getId());
		if (authInfo == null || !encoder.matches(generation.getPassword(), authInfo.getPassword())) {
			return null;
		}

		return authInfo;
	}

	@Override
	public void insertGeneration(Generation generation) {
		generationRepository.insertGeneration(generation);
	}

	@Override
	public Generation selectFindId(Generation generation) {
		return generationRepository.selectFindId(generation);
	}

	@Override
	public void findIdEmail(Generation generation, String authPathId) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "findid");
		body.add("id", generation.getId());
		body.add("authPath", authPathId);
		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(Configcode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		mail.send(generation.getEmail(), "메일", message);

	}

	@Override
	public Generation selectFindPassword(Generation generation) {
		return generationRepository.selectFindPassword(generation);
	}

	@Override
	public void findPasswordEmail(Generation generation, String password) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "findpassword");
		body.add("id", generation.getId());
		body.add("password", password);
		System.out.println("메일 보낼번호 : " + password);

		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(Configcode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		mail.send(generation.getEmail(), "메일", message);

		System.out.println("바뀔번호");
		generation.setPassword(encoder.encode(password));
		generationRepository.updateFindPassword(generation);

	}

	@Override
	public Map<String, Object> selectGenerationWonList(int currentPage, String generationIdx) {
		Paging paging = Paging.builder().currentPage(currentPage).blockCnt(5).cntPerPage(5).type("board")
				.total(generationRepository.selectContentCnt(generationIdx)).build();
		Map<String, Object> commandMap = new HashMap<String, Object>();
		Map<String, Object> generationMap = new HashMap<String, Object>();
		
		generationMap.put("paging", paging);
		generationMap.put("generationIdx", generationIdx);

		commandMap.put("paging", paging);
		commandMap.put("generationWonList", generationRepository.selectGenerationWonList(generationMap));

		return commandMap;
	}

	// 세대원 수정
	@Override
	public int updateGenerationWonModify(GenerationWon generationWon) {
		return generationRepository.updateGenerationWonModify(generationWon);
	}

	// 세대원 삭제
	@Override
	public int updateGenerationWonDelete(GenerationWon generationWon) {
		return generationRepository.updateGenerationWonDelete(generationWon);
	}

	// 세대원 추가
	@Override
	public int insertGenerationWonAdd(GenerationWon generationWon) {
		return generationRepository.insertGenerationWonAdd(generationWon);
	}

	// 세대 정보
	@Override
	public Generation selectGeneration(Generation generation) {
		return generationRepository.selectGeneration(generation);
	}

	@Override
	public int updateGenerationModify(Generation generation) {
		String password = generation.getPassword();
		generation.setPassword(encoder.encode(password));
		return generationRepository.updateGenerationModify(generation);
	}

	@Override
	public void authEmail(Generation generation, String authPathEmail) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "email");
		body.add("id", generation.getId());
		body.add("authPath", authPathEmail);
		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(Configcode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		mail.send(generation.getEmail(), "이메일 인증 메일", message);
	}

	@Override
	public int updateGenerationEmail(Generation generation) {
		return generationRepository.updateGenerationEmail(generation);
	}

	@Override
	public int authTell(String tell, HttpSession httpSession) {

		String method = "POST";
		String url = "아이디";
		String timestamp = Long.toString(System.currentTimeMillis());
		String accessKey = "";
		String secretKey = "";

		String signature = makeSignature(url, timestamp, method, accessKey, secretKey);
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");
		header.add("x-ncp-apigw-timestamp", timestamp);
		header.add("x-ncp-iam-access-key", accessKey);
		header.add("x-ncp-apigw-signature-v2", signature);

		String authPathTell = makeCertNum();
		httpSession.setAttribute("authPathTell", authPathTell);

		JSONObject params = new JSONObject();
		JSONObject params2 = new JSONObject();
		JSONArray messages = new JSONArray();
		try {
			params.put("type", "SMS");
			params.put("from", "01092680961");
			params.put("content", "[ABOO:아파트를 부탁해] 본인 확인을 위해 인증번호 [" + authPathTell + "]를 입력해주세요.");
			params2.put("to", tell);
			messages.put(params2);
			params.put("messages", messages);
			String body = params.toString();

			RequestEntity<String> request = RequestEntity
					.post("https://sens.apigw.ntruss.com/sms/v2/services/아이디/messages")
					.headers(header).body(body);

			ResponseEntity<String> response = http.exchange(request, String.class);
			return response.getStatusCodeValue();

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return 400;

	}

	// 인증번호 생성 메서드
	public String makeCertNum() {
		Random random = new Random();
		String certNum = "";

		for (int i = 0; i < 6; i++) {
			String rand = Integer.toString(random.nextInt(10));

			if (!certNum.contains(rand)) {
				certNum += rand;
			} else {
				i -= 1;
			}
		}

		return certNum;
	}

	//인증키 암호화
	public String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey) {
		String space = " ";
		String newLine = "\n";

		String message = new StringBuilder().append(method).append(space).append(url).append(newLine).append(timestamp)
				.append(newLine).append(accessKey).toString();

		SecretKeySpec signingKey;
		String encodeBase64String = "";

		try {
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			encodeBase64String = Base64.encodeBase64String(rawHmac);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
			e.printStackTrace();
		}

		return encodeBase64String;

	}

	@Override
	public int selectGenerationWonCnt(Generation generation) {
		return generationRepository.selectContentCnt(generation.getGenerationIdx());
	}

	@Override
	public int updateGenerationTell(Generation generation) {
		return generationRepository.updateGenerationTell(generation);
	}

}
