package com.kh.aboo.mypage.mymgmtfee.model.service;

import java.util.Map;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.admin.mgmtfee.model.vo.MgmtfeeOverdue;
import com.kh.aboo.mypage.mymgmtfee.model.vo.MgmtfeePayment;

public interface MyMgmtfeeService {

	// [관리비 페이지 페이징]
	// 2, 관리비 리스트 가져온다
	Map<String, Object> selectMyMgmtfeeList(int currentPage, String generationIdx);
	
	// 3. 연체료 가져오는 쿼리
	MgmtfeeOverdue selectMyMgmtfeeOverdue(String mgmtfeeIdx);
		
	// [관리비 상세 페이지]
	// 1. 관리비번호로 상세내역 가져온다
	Mgmtfee selectMyMgmtfeeByMgmtfeeIdx(String mgmtfeeIdx);
	
	// 2. 고지월로 제공을 위한 조회
	Map<String,Object> selectMyMgmtfeeDate(String mgmtfeeIdx);
	
	//선영 결제
	void insertPayment(MgmtfeePayment mgmtfeePayment);

	String selectPaymentGenerationIdx(String mgmtfeeIdx);
}
