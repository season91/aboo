package com.kh.aboo.bdmin.management.model.service;

import java.util.Map;

import com.kh.aboo.bdmin.management.model.vo.ApartApplication;
import com.kh.aboo.bdmin.management.model.vo.Bdmin;
import com.kh.aboo.bdmin.management.model.vo.ManagerApplication;
import com.kh.aboo.user.apartment.model.vo.Apartment;
import com.kh.aboo.user.manager.model.vo.Admin;

public interface ManagementService {

	Bdmin selectBdminForAuth(Bdmin bdmin);
	
	// [아파트 목록]
	// 1. 페이징 화면 구현을 위한 list
	Map<String, Object> selectApartList(int currentPage, Map<String, Object> searchMap);
	
	// 2. 서비스 신청서 보기 list
	Map<String, Object> selectApartApplicationList(int currentPage, Map<String, Object> searchMap);
	
	// 3. 아파트 상세정보
	Apartment selectApartment(String apartmentIdx);
	
	// 4. 아파트 수정
	int updateApartment(Apartment apartment);
	
	// [서비스신청 확인]
	// 1. 서비스 신청서 상세
	ApartApplication selectApartApplication(String applicationIdx);
	
	// 2. 서비스 신청 처리
	void procedureApartApplicationUpdate(ApartApplication apartApplication);
	
	//[서비스신청서 작성]
	int insertApartApplication(ApartApplication apartApplication);
	
	//[어드민 매니저 권한관리]
	// 기본페이징
	Map<String, Object> selectAdminList(int page, Map<String, Object> searchMap);
	
	int insertAdmin(Admin admin, String apartmentInfo);

	// 매니저 권한회수
	int updateAdminIsDel(String managerIdx);

	// [선영] 어드민 신청 폼 테이블에 insert
	int insertManagerContact(ManagerApplication managerApplication);
	
	// [선영] 어드민 신청 테이블 아이디 체크 
	int selectManagerContactId(String  id);
	
	// [매니저 계정 신청]
	// 1. 신청 목록 페이징
	Map<String, Object> selectAdminApplicationList(int page, Map<String, Object> searchMap);
	
	// 2. 상세조회
	Map<String, Object> selectAdminApplication(String managerApplicationIdx);
	
	// 3. 어드민 계정 신청서 업데이트 및 어드민 계정 생성
	String updateAdminApplication(ManagerApplication application, String apartmentInfo);
}
