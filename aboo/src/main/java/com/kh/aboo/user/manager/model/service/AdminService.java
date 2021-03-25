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
	public Map<String,Object> selectauthorityList(int currentPage,String apartmentIdx);

}