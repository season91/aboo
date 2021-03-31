package com.kh.aboo.member.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kh.aboo.member.model.repository.MemberRepository;
import com.kh.aboo.member.model.vo.Member;

@Component
public class MemberValidator implements Validator{
	
	// 컨트롤러가 아니라서 service를 부르는건 와꾸가 안맞음. Repository 부르자. 생성자주입
	private final MemberRepository memberRepository;
	
	public MemberValidator(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	//어떤 조건의 컨트롤러 메서드 파라미터를 검증할 것인지 작성
	@Override
	public boolean supports(Class<?> clazz) {
		// Class<?> clazz : 컨트롤러 파라미터의 class 객체
		return Member.class.equals(clazz);
	}

	//검증할 코드 작성
	@Override
	public void validate(Object target, Errors errors) {
		// Object target : 컨트롤러 메서드의 파라미터
		// Errors errors : 검증에 실패할 경우, 내용을 저장할 Error 객체. 컨트롤러 메서드의 파라미터로 전달된다.

		Pattern pattern = Pattern.compile("^(?!.*[ㄱ-힣])(?=.*\\W)(?=.*\\d)(?=.*[a-zA-Z])(?=.{8,})");
		Member persistInfo = (Member) target;
		// 1. 사용자가 넘긴 아이디가 존재하지 않는 아이디가 밎는지
		if(memberRepository.selectMemberById(persistInfo.getUserId()) != null) {
			errors.rejectValue("userId", "error.userId", "이미 존재하는 아이디입니다.");
		}
		
		// 2. 비밀번호가 8자리이상 숫자,영문,특문이 각1개씩 들어있는지
		// 정규표현식에 맞다면(특문에안들어있다면) 값이 flase가 뜰꺼니까 true로 변경해주기..??
		if(!pattern.matcher(persistInfo.getPassword()).find()) {
			errors.rejectValue("password", "error.password", "비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상인 문자열입니다.");
		}
		
		// 3. 사용자가 넘긴 이메일이 존재하지 않는 이메일인지
		if(memberRepository.selectMemberByEmail(persistInfo.getEmail()) > 0) {
			errors.rejectValue("email", "error.email", "이미 존재하는 이메일입니다.");
		}
		
		// 4. 사용자가 넘긴 휴대폰 번호가 존재하지 않는 휴대폰 번호인지
		if(memberRepository.selectMemberByTell(persistInfo.getTell()) > 0) {
			errors.rejectValue("tell", "error.tell", "이미 존재하는 휴대폰번호 입니다.");
		}

	}

}
