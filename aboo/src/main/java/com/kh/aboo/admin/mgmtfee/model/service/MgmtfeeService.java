package com.kh.aboo.admin.mgmtfee.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.admin.mgmtfee.model.vo.MgmtfeeOverdue;
import com.kh.aboo.user.generation.model.vo.Generation;

public interface MgmtfeeService {
	// [엑셀]
	// 0. 업로드된 엑셀 읽기
	Map<String, Object> mgmtfeeRead(MultipartFile file);
	
	// 1. 관리비 DB에 넣기
	List<Mgmtfee> insertMgmtfee(Map<String,Object> commandMap, String apartmentIdx);
	
	// 2. 세대정보 엑셀다운을 위한 리스트 동,호수 리스트받아오기.
	Map<String, Object> selectGenerationList(String apartmentIdx);

	// 3. 페이징에 세대정보 넣어야해서 세대번호기준 세대정보 가져온다. 관리비 상세조회에서 쓸 세대정보 조회
	Generation selectGenerationByGenerationIdx(String generationIdx);
		
	// [관리자페이지 페이징]
	// 2. 페이징 화면 구현을 위한 list
	Map<String, Object> selectMgmtfeeList(int currentPage, Map<String, Object> searchMap);

	// 3. 동,호수로 세대정보가져온다.
	Generation selectGenerationByBuildingAndNum(Generation generation);

	// [관리비수정]
	// 1. 관리비 상세 관리비조회
	Mgmtfee selectMgmtfeeByMgmtfeeIdx(String mgmtfeeIdx);
	
	// 2. 관리비 상세를 위한 연체금액 확인
	MgmtfeeOverdue selectMgmtfeeOverdue(String mgmtfeeIdx);
		
	
	// 3. 관리비 수정
	Mgmtfee updateMgmtfee(Mgmtfee mgmtfee);
	
	// 4. 연체료 수정
	int updateMgmtfeeOverdue(MgmtfeeOverdue mgmtfeeOverdue);
	
	
	// 6. 연체료 계산하는 배치 프로시저
	void procedureMgmtOverDue();
	
	// [관리비삭제]
	int updateMgmtfeeIsDel(String mgmtfeeIdx);
}
