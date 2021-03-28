package com.kh.aboo.user.manager.model.service;

import java.util.Map;

import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;


public interface AdminService {
	//선영 로그인
	Admin selectGenerationForAuth(Admin admin);

	void insertAdmin(Admin admin);

	//선영 어드민 세대 추가
	int insertGeneration(Generation generation, String apartmentIdx);

	//선영 어드민 세대 리스트
	public Map<String,Object> selectAuthorityList(int currentPage,String apartmentIdx);
	
	//아이디 찾기 전 확인
	Admin selectfindId(Admin admin);
	
	//아이디 찾기 메일
	void authenticationIdMail(Admin admin ,String authPath);
	
	//비밀번호 찾기 전 확인
	Admin selectFindPassword(Admin admin);
	
	//비밀번호 찾기 메일
	void authenticationPasswordMail(Admin admin, String password);
	
	//어드민 정보 
	Admin selectAdmin(Admin admin);

	int updateAdminModify(Admin admin);

}
