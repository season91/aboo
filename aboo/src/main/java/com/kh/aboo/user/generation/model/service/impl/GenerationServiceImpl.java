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
	public Generation selectFindId(Generation generation) {
		return generationRepository.selectFindId(generation);
	}

	@Override
	public void authenticationIdMail(Generation generation, String authPath) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "findid");
		body.add("id", generation.getId());
		body.add("authPath", authPath);
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
	public void updateFindPassword(Generation generation) {
		String password = generation.getPassword();
		generation.setPassword(encoder.encode(password));
		System.out.println("이제 바뀔번호 : " +password);
		generationRepository.updateFindPassword(generation);
	}

	@Override
	public void authenticationPasswordMail(Generation generation, String password) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "findpassword");
		body.add("id", generation.getId());
		body.add("password",password);
		System.out.println("메일 보낼번호 : " +password);
		
		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(Configcode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		mail.send(generation.getEmail(), "메일", message);		
	}

	
}
