package com.kh.aboo.bdmin.management.model.service;

import java.util.Map;

import com.kh.aboo.bdmin.management.model.vo.ApartApplication;
import com.kh.aboo.bdmin.management.model.vo.Bdmin;
import com.kh.aboo.user.apartment.model.vo.Apartment;

public interface ManagementService {

	Bdmin selectBdminForAuth(Bdmin bdmin);
	
	// [아파트 목록]
	// 1. 페이징 화면 구현을 위한 list
	Map<String, Object> selectApartList(int currentPage, String standard, String keyword);
	
	// 2. 서비스 신청서 보기 list
	Map<String, Object> selectApartApplicationList(int currentPage, String standard, String keyword);
	
	// 3. 아파트 상세정보
	Apartment selectApartment(String apartmentIdx);
	
	// 4. 아파트 수정
	int updateApartment(Apartment apartment);
	
	// [서비스신청 확인]
	// 1. 서비스 신청서 상세
	ApartApplication selectApartApplication(String applicationIdx);
	
	// 2. 서비스 신청 처리
	void procedureApartApplicationUpdate(ApartApplication apartApplication);
	
	//[입점신청서]
	int insertApartApplication(ApartApplication apartApplication);
}
