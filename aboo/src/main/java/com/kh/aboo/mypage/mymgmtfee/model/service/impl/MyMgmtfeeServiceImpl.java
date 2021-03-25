package com.kh.aboo.mypage.mymgmtfee.model.service.impl;

import org.springframework.stereotype.Service;

import com.kh.aboo.mypage.mymgmtfee.model.repository.MyMgmtfeeRepository;
import com.kh.aboo.mypage.mymgmtfee.model.service.MyMgmtfeeService;

@Service
public class MyMgmtfeeServiceImpl implements MyMgmtfeeService {

	private final MyMgmtfeeRepository myMgmtfeeRepository;
	
	public MyMgmtfeeServiceImpl(MyMgmtfeeRepository myMgmtfeeRepository) {
		this.myMgmtfeeRepository = myMgmtfeeRepository;
	}
}
