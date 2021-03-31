package com.kh.aboo.member.model.service;

import com.kh.aboo.member.model.vo.Member;

public interface MemberService {
	
	// 여기 인터페이스에서 먼저 선언하고 impl에 구현한다.
	Member selectMemberById(String userId);
	
	// 메일발송 선언
	void authenicateEmail(Member member, String authPath);

	// 회원가입
	int insertMember(Member member);
	
	// 로그인
	Member authenticateUser(Member member);
}
