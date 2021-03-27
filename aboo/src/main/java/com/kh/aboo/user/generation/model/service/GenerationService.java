package com.kh.aboo.user.generation.model.service;

import java.util.Map;

import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.generationWon.model.vo.GenerationWon;

public interface GenerationService {

	
	Generation selectGenerationForAuth(Generation generation);
	
	void insertGeneration(Generation generation);
	
	//아이디 찾기 전 확인
	Generation selectFindId(Generation generation);
	
	//아이디 찾기 메일
	void authenticationIdMail(Generation generation ,String authPath);
	
	//비밀번호 찾기 전 확인
	Generation selectFindPassword(Generation generation);
	
	//비밀번호 찾기 메일
	void authenticationPasswordMail(Generation generation, String password);
	
	//선영 어드민 세대 리스트
	Map<String,Object> selectGenerationWonList(int currentPage,String apartmentIdx);

	//세대원 수정
	int updateGenerationWonModify(GenerationWon generationWon);
	
	int updateGenerationWonDelete(GenerationWon generationWon);
	
	int insertGenerationWonAdd(GenerationWon generationWon);

	Generation selectGeneration(Generation generation);
	
	int updateGenerationModify(Generation generation);
}
