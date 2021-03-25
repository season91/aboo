package com.kh.aboo.user.generation.model.service;

import com.kh.aboo.user.generation.model.vo.Generation;

public interface GenerationService {

	
	Generation selectGenerationForAuth(Generation generation);
	
	void insertGeneration(Generation generation);
	
	//아이디 찾기 전 확인
	Generation selectFindId(Generation generation);
	
	//아이디 찾기 메일
	void authenticationIdMail(Generation generation ,String authPath);
	
	//비밀번호 찾기 전 확인
	Generation selectFindPassword(Generation generation);
	
	//비밀번호 변경
	void updateFindPassword(Generation generation);
	
	//비밀번호 찾기 메일
	void authenticationPasswordMail(Generation generation, String password);

}
