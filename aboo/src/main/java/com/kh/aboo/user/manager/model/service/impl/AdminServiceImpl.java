package com.kh.aboo.user.manager.model.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.common.code.ConfigCode;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.mail.MailSender;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.user.generation.model.repository.GenerationRepository;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.repository.AdminRepository;
import com.kh.aboo.user.manager.model.service.AdminService;
import com.kh.aboo.user.manager.model.vo.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private MailSender mail;

	@Autowired
	private RestTemplate http;

	private final AdminRepository adminRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public void insertAdmin(Admin admin) {
		adminRepository.insertAdmin(admin);
	}

	@Override
	public Admin selectAdminForAuth(Admin admin) {

		Admin authInfo = adminRepository.selectAdminForAuth(admin.getId());
		if (authInfo == null || !encoder.matches(admin.getPassword(), authInfo.getPassword())) {
			return null;
		}

		return authInfo;
	}

	// 선영 어드민 세대 추가
	@Override
	public int insertGeneration(Generation generation, String apartmentIdx) {
		String Separator = adminRepository.selectApartmentBySeparator(apartmentIdx); // 아파트 구분자
		String id = Separator + generation.getBuilding() + "d" + generation.getNum() + "h";
		
		generation.setId(id);
		
		if (adminRepository.selectGenerationIdCnt(generation) > 0 ) {
			throw new ToAlertException(ErrorCode.IDCHECK02); 
		}
			
		generation.setPassword(encoder.encode("123"));
		generation.setApartmentIdx(apartmentIdx);
		return adminRepository.insertGeneration(generation);
	}

	public Map<String, Object> searchMap(String apartmentIdx, String kind, String keyword) {
		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("apartmentIdx", apartmentIdx);
		searchMap.put("keyword", keyword);

		switch (kind) {
		case "apartmentIdx":
			// 기본 페이징
			searchMap.put("searchType", "apartmentIdx");
			break;
		case "generation":
			// 세대로 검색
			Generation generation = new Generation();
			String[] buildingAndNum = keyword.split("-");
			generation.setBuilding(buildingAndNum[0]);
			generation.setNum(buildingAndNum[1]);
			generation.setApartmentIdx(apartmentIdx);
			
			//null이면 0을 반환
			String generationIdx = Optional.ofNullable(adminRepository.selectGenerationByBuildingAndNum(generation)).orElse("0");
						
			searchMap.put("searchType", "generation");
			searchMap.put("generationIdx",generationIdx);
			
			break;
			
		case "building":
			//키워드로 검색
			searchMap.put("searchType", "building");			
			break;

		}

		return searchMap;
	}

	@Override
	public Map<String, Object> selectAuthorityList(int currentPage, String apartmentIdx, String kind, String keyword) {
		Map<String, Object> searchMap = searchMap(apartmentIdx, kind, keyword);

		Paging paging = Paging.builder().currentPage(currentPage).blockCnt(5).cntPerPage(10).type("board")
				.total(adminRepository.selectContentCnt(searchMap)).build();

		searchMap.put("paging", paging);
		searchMap.put("authorityList", adminRepository.selectAuthorityList(searchMap));

		return searchMap;
	}

	@Override
	public Admin selectfindId(Admin admin) {
		return adminRepository.selectFindId(admin);
	}

	@Override
	public void findIdEmail(Admin admin, String authPathId) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "findid");
		body.add("id", admin.getId());
		body.add("authPath", authPathId);
		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(ConfigCode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		mail.send(admin.getEmail(), "❗ [ABOO:아파트를 부탁해] 아이디 인증번호 발급", message);

	}

	@Override
	public Admin selectFindPassword(Admin admin) {
		return adminRepository.selectFindPassword(admin);
	}

	@Override
	public void findPasswordEmail(Admin admin, String password) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "findpassword");
		body.add("id", admin.getId());
		body.add("password", password);
		System.out.println("메일 보낼번호 : " + password);

		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(ConfigCode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		mail.send(admin.getEmail(), "❗ [ABOO:아파트를 부탁해] 임시 비밀번호 발급", message);

		admin.setPassword(encoder.encode(password));
		adminRepository.updateFindPassword(admin);

	}

	// 어드민 정보 불러오기
	@Override
	public Admin selectAdmin(Admin admin) {
		return adminRepository.selectAdmin(admin);
	}

	// 정보 업데이트
	@Override
	public int updateAdminModify(Admin admin) {
		String password = admin.getPassword();
		admin.setPassword(encoder.encode(password));
		return adminRepository.updateAdminModify(admin);
	}


	//이메일 보내기전 수 확인
	@Override
	public int selectAdminEmailCnt(Admin admin) {
		return adminRepository.selectAdminEmailCnt(admin);
	}
	

	// 이메일 인증 이메일 보내기
	@Override
	public void authEmail(Admin admin, String authPathEmail) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "email");
		body.add("id", admin.getId());
		body.add("authPath", authPathEmail);
		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(ConfigCode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		mail.send(admin.getEmail(), "❗ [ABOO:아파트를 부탁해] 이메일 인증번호 발급", message);
	}

	// 이메일 업데이트
	@Override
	public int updateAdminEmail(Admin admin) {
		return adminRepository.updateAdminEmail(admin);
	}

	// 세대 초기화 하기
	@Override
	public void updateResetGeneration(Generation generation) {
		adminRepository.updateDeleteGeneration(generation);
		generation.setPassword(encoder.encode("123"));
		adminRepository.insertGeneration(generation);
	}

	// 세대 삭제 하기
	@Override
	public void updateDeleteGeneration(Generation generation) {
		adminRepository.updateDeleteGeneration(generation);
	}
	
	
	//문자 보내기전  수 확인
	@Override
	public int selectAdminTellCnt(Admin admin) {
		return adminRepository.selectAdminTellCnt(admin);
	}
	

	//문자 발송
	@Override
	public int authTell(String tell, HttpSession httpSession) {

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

		String authPathTell = makeCertNum();
		httpSession.setAttribute("authPathTell", authPathTell);

		JSONObject params = new JSONObject();
		JSONObject params2 = new JSONObject();
		JSONArray messages = new JSONArray();
		try {
			params.put("type", "SMS");
			params.put("from", "");
			params.put("content", "[ABOO:아파트를 부탁해] 본인 확인을 위해 인증번호 [" + authPathTell + "]를 입력해주세요.");
			params2.put("to", tell);
			messages.put(params2);
			params.put("messages", messages);
			String body = params.toString();

			RequestEntity<String> request = RequestEntity
					.post("https://sens.apigw.ntruss.com/sms/v2/services//messages").headers(header).body(body);

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

	// 인증키 암호화
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
	public int updateAdminTell(Admin admin) {
		return adminRepository.updateAdminTell(admin);
	}



}
