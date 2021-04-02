package com.kh.aboo.user.manager.model.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;


public interface AdminService {
	//선영 로그인
	Admin selectAdminForAuth(Admin admin);

	void insertAdmin(Admin admin);

	//선영 어드민 세대 추가
	int insertGeneration(Generation generation, String apartmentIdx);

	//선영 어드민 세대 리스트
	public Map<String,Object> selectAuthorityList(int currentPage, String apartmentIdx, String kind, String keyword);

	//아이디 찾기 전 확인
	Admin selectfindId(Admin admin);
	
	//아이디 찾기 메일
	void findIdEmail(Admin admin ,String authPath);
	
	//비밀번호 찾기 전 확인
	Admin selectFindPassword(Admin admin);
	
	//비밀번호 찾기 메일
	void findPasswordEmail(Admin admin, String password);
	
	//어드민 정보 
	Admin selectAdmin(Admin admin);
	

	int updateAdminModify(Admin admin);

	//이메일 인증
	void authEmail(Admin admin ,String authPathEmail);
	
	//이메일 인증 성공시 이메일 업데이트
	int updateAdminEmail(Admin admin);
	
	//세대 초기화
	void updateResetGeneration(Generation generation);

	//세대 삭제
	void updateDeleteGeneration(Generation generation);

	//번호 인증
	int authTell(String tell, HttpSession session);
	
	//번호 인증 성공시 번호 업데이트
	int updateAdminTell(Admin admin);
}
