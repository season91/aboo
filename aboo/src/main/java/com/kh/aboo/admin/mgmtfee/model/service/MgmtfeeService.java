package com.kh.aboo.admin.mgmtfee.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.user.generation.model.vo.Generation;

public interface MgmtfeeService {
	
	
	//아영 관리비업로드 파일 읽기
	
	Map<String, Object> mgmtfeeRead(MultipartFile file);
	
	List<Mgmtfee> insertMgmtfee(Map<String,Object> commandMap, String apartmentIdx);
	
	Map<String, Object> selectGenerationList(String apartmentIdx);

	Map<String, Object> selectMgmtfeeList(int currentPage, String apartmentIdx);
	
	Mgmtfee selectMgmtfeeByMgmtfeeIdx(String mgmtfeeIdx);
	
	Generation selectGenerationByGenerationIdx(String generationIdx);
	
	Mgmtfee updateMgmtfee(Mgmtfee mgmtfee);
	
	//연체료 계산하는 배치메서드
	void procedureMgmtOverDue();
}
