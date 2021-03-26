package com.kh.aboo.mypage.mymgmtfee.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.mypage.mymgmtfee.model.repository.MyMgmtfeeRepository;
import com.kh.aboo.mypage.mymgmtfee.model.service.MyMgmtfeeService;

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
				.cntPerPage(2)
				.type("board")
				.total(myMgmtfeeRepository.selectContentCnt(generationIdx))
				.build();
		System.out.println(paging.toString());
		
		Map<String, Object> commandMap = new HashMap<>();
		Map<String, Object> generationMap = new HashMap<>();
		generationMap.put("paging", paging);
		generationMap.put("generationIdx", generationIdx);
		
		List<Mgmtfee> myMgmtfeeList = myMgmtfeeRepository.selectMyMgmtfeeList(generationMap);
		
		System.out.println("서비수"+myMgmtfeeList);
		commandMap.put("paging", paging);
		commandMap.put("myMgmtfeeList", myMgmtfeeList);

		return commandMap;
	}

	@Override
	public Mgmtfee selectMyMgmtfeeByMgmtfeeIdx(String mgmtfeeIdx) {
		return myMgmtfeeRepository.selectMyMgmtfeeByMgmtfeeIdx(mgmtfeeIdx);
	}

	@Override
	public Map<String, Object> selectMyMgmtfeeDate(String mgmtfeeIdx) {
		return myMgmtfeeRepository.selectMyMgmtfeeDate(mgmtfeeIdx);
	}

	
}
