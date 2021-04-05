package com.kh.aboo.user.generation.model.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.generationWon.model.vo.GenerationWon;

public interface GenerationService {

	
	Generation selectGenerationForAuth(Generation generation);
	
	void insertGeneration(Generation generation);
	
	//아이디 찾기 전 확인
	Generation selectFindId(Generation generation);
	
	//아이디 찾기 메일
	void findIdEmail(Generation generation ,String authPathId);
	
	//비밀번호 찾기 전 확인
	Generation selectFindPassword(Generation generation);
	
	//비밀번호 찾기 메일
	void findPasswordEmail(Generation generation, String password);
	
	//선영  세대 리스트
	Map<String,Object> selectGenerationWonList(int currentPage,String generationIdx);

	//세대원 수정
	int updateGenerationWonModify(GenerationWon generationWon);
	
	int updateGenerationWonDelete(GenerationWon generationWon);
	
	int insertGenerationWonAdd(GenerationWon generationWon);

	//세대 정보
	Generation selectGeneration(Generation generation);
	
	int updateGenerationModify(Generation generation);
	
	//이메일 인증
	void authEmail(Generation generation ,String authPathEmail);

	//이메일 인증전 수 확인
	int selectGenerationEmailCnt(Generation generation);
	
	//이메일 인증 성공시 이메일 업데이트
	int updateGenerationEmail(Generation generation);
	
	//세대원 수 확인
	int selectGenerationWonCnt(Generation generation);
	
	//문자 인증번호
	int authTell(String tell, HttpSession httpSession);
	 
	//문자 인증전 수 확인
	int selectGenerationTellCnt(Generation generation);
	
	//휴대폰 인증 성공시 이메일 업데이트
	int updateGenerationTell(Generation generation);
	 
	 

}
