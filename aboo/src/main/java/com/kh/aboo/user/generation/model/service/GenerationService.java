package com.kh.aboo.user.generation.model.service;

import com.kh.aboo.user.generation.model.vo.Generation;

public interface GenerationService {

	
	Generation selectGenerationForAuth(Generation generation);
	
	void insertGeneration(Generation generation);
	
	//아이디 찾기 전 확인
	Generation selectfindid(Generation generation);
	
	//아이디 찾기 메일 보내기
	void authenticateEmailId(Generation generation, String authPath);

	//비밀번호 찾기 전 확인
	public Generation selectFindPassword(Generation generation);
	
	//비밀번호 찾기 메일 보내기
	void authenticateEmailPassword(Generation generation, String authPath);

}
