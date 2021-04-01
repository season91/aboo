package com.kh.aboo.mypage.mymgmtfee.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.admin.mgmtfee.model.vo.MgmtfeeOverdue;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.mypage.mymgmtfee.model.repository.MyMgmtfeeRepository;
import com.kh.aboo.mypage.mymgmtfee.model.service.MyMgmtfeeService;
import com.kh.aboo.mypage.mymgmtfee.model.vo.MgmtfeePayment;

@Service
public class MyMgmtfeeServiceImpl implements MyMgmtfeeService {

	private final MyMgmtfeeRepository myMgmtfeeRepository;
	
	public MyMgmtfeeServiceImpl(MyMgmtfeeRepository myMgmtfeeRepository) {
		this.myMgmtfeeRepository = myMgmtfeeRepository;
	}


	@Override
	public Map<String, Object> selectMyMgmtfeeList(int currentPage, String generationIdx) {
		Paging paging = Paging.builder()
				.currentPage(currentPage)
				.blockCnt(5)
				.cntPerPage(10)
				.type("mymgmtfee")
				.total(myMgmtfeeRepository.selectContentCnt(generationIdx))
				.build();
		System.out.println(paging.toString());
		
		Map<String, Object> commandMap = new HashMap<>();
		commandMap.put("paging", paging);
		commandMap.put("generationIdx", generationIdx);
		
		//나의 관리비 리스트 넣어주기
		List<Mgmtfee> myMgmtfeeList = myMgmtfeeRepository.selectMyMgmtfeeList(commandMap);
		commandMap.put("myMgmtfeeList", myMgmtfeeList);
		
		// 나의 관리비리스트-연체료 정보 넣어주기.
		List<MgmtfeeOverdue> myMgmtfeeOverdueList = new ArrayList<MgmtfeeOverdue>();
		MgmtfeeOverdue mgmtfeeOverdue = new MgmtfeeOverdue();
		for (int i = 0; i < myMgmtfeeList.size(); i++) {
			mgmtfeeOverdue = myMgmtfeeRepository.selectMyMgmtfeeOverdue(myMgmtfeeList.get(i).getMgmtfeeIdx());
			myMgmtfeeOverdueList.add(mgmtfeeOverdue);
		}
		commandMap.put("myMgmtfeeOverdueList", myMgmtfeeOverdueList);

		return commandMap;
	}


	@Override
	public MgmtfeeOverdue selectMyMgmtfeeOverdue(String mgmtfeeIdx) {
		return myMgmtfeeRepository.selectMyMgmtfeeOverdue(mgmtfeeIdx);
	}


	@Override
	public Mgmtfee selectMyMgmtfeeByMgmtfeeIdx(String mgmtfeeIdx) {
		return myMgmtfeeRepository.selectMyMgmtfeeByMgmtfeeIdx(mgmtfeeIdx);
	}


	@Override
	public Map<String, Object> selectMyMgmtfeeDate(String mgmtfeeIdx) {
		return myMgmtfeeRepository.selectMyMgmtfeeDate(mgmtfeeIdx);
	}


	//선영 결제
	@Override
	public void insertPayment(MgmtfeePayment mgmtfeePayment) {
		 myMgmtfeeRepository.procedureInsertPayment(mgmtfeePayment);
	}


}
