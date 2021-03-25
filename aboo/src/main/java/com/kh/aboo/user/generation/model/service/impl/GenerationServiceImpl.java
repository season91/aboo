package com.kh.aboo.user.generation.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.kh.aboo.user.generation.model.repository.GenerationRepository;
import com.kh.aboo.user.generation.model.service.GenerationService;
import com.kh.aboo.user.generation.model.vo.Generation;

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
	public void authenticateEmail(Generation generation, String authPath) {
		// 내부적으로 Map<String,List<k>> 를 구현
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "findid");
		body.add("userId", generation.getId());
		body.add("authPath", authPath);
		// RestTemplate의 기본 Content-type은 application/json
		// Content-type을 form-url-encoded로 설정해서 통신해보기
		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(Configcode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		System.out.println(System.nanoTime());
		mail.send(generation.getEmail(), "인증번호 발송", message);
	}

	@Override
	public Generation selectfindid(Generation generation) {
		return generationRepository.selectfindid(generation);
	}

}
