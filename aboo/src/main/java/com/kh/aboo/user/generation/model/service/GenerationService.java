package com.kh.aboo.user.generation.model.service;

import com.kh.aboo.user.generation.model.vo.Generation;

public interface GenerationService {

	
	Generation selectGenerationForAuth(Generation generation);
	
	void insertGeneration(Generation generation);
	
	//메일 보내기전에 체크
	Generation selectfindid(Generation generation);
	
	//메일
	void authenticateEmail(Generation generation, String authPath);

}
