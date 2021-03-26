package com.kh.aboo.mypage.mymgmtfee.model.service;

import java.util.Map;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;

public interface MyMgmtfeeService {

	Map<String, Object> selectMyMgmtfeeList(int currentPage, String generationIdx);
	
	Mgmtfee selectMyMgmtfeeByMgmtfeeIdx(String mgmtfeeIdx);
	
	Map<String,Object> selectMyMgmtfeeDate(String mgmtfeeIdx);

}
