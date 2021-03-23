package com.kh.aboo.member.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.aboo.member.model.repository.MemberRepository;
import com.kh.aboo.member.model.service.MemberService;
import com.kh.aboo.member.model.vo.Member;

import common.code.Configcode;
import common.mail.MailSender;

@Service
public class MemberServiceImpl implements MemberService{

	// 우리가 만든 애들은 싱글톤이고 스레드세이프하지않다.
	// 그래서 생성자주입으로 넣어주겠다.
	private final MemberRepository memberRepository;
	
	@Autowired
	private MailSender mail; // 우리가 구현한 MailSender 의존성 주입 받기.
	
	@Autowired
	private RestTemplate http;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Override
	public Member selectMemberById(String userId) {
		return memberRepository.selectMemberById(userId);
	}

	@Override
	public void authenicateEmail(Member persistInfo, String authPath) {
		
		// 일반맵 말고 내부적으로 Map<String, List<K>> 로 구현해놓은 map 사용한다.
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "temp_join"); //view 경로
		body.add("userId", persistInfo.getUserId()); //view 
		body.add("authPath", authPath); // 유니크url
		
		
		// RestTemplate 의 기본 content-type은 application/json
		// content-type을 form-url-encoded로 설정해서 통신해보기
		// post라서 body가 있어야 한다.
		RequestEntity<MultiValueMap<String, String>> request = RequestEntity
				.post(Configcode.DOMAIN+"/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.body(body);
		
		// exchange 주어진 RequestEntity에 지정된 요청을 실행하고 응답을 ResponseEntity로 반환합니다.
		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();

		// 느리니까 메일보내는건 비동기로 해보자.
		mail.send(persistInfo.getEmail(), "회원가입을 축하드립니다.", message);
		
	}


	@Override
	public int insertMember(Member member) {
		member.setPassword(encoder.encode(member.getPassword()));
		return memberRepository.insertMember(member);
	}
	
	@Override
	public Member authenticateUser(Member member) {
		Member authInfo = memberRepository.selectMemberForAuth(member.getUserId());
		// 앞에는 사용자가 입력한 인코딩안된 pw와 데이터베이스의 인코딩된 pw와 매치를 시켜서 맞으면 로그인 성공
		// null이거나 불일치시 null 반환
		if(authInfo == null || !encoder.matches(member.getPassword(), authInfo.getPassword())) {
			return null;
		}
		
		return authInfo;
		
	}
}
