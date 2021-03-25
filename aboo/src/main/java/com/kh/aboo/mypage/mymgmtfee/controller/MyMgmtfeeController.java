package com.kh.aboo.mypage.mymgmtfee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.aboo.mypage.mymgmtfee.model.service.MyMgmtfeeService;

@Controller
@RequestMapping("mypage")
public class MyMgmtfeeController {
	
	private final MyMgmtfeeService myMgmtfeeService;
	
	public MyMgmtfeeController(MyMgmtfeeService myMgmtfeeService) {
		this.myMgmtfeeService = myMgmtfeeService;
	}

	// 아영
	@GetMapping("mymgmtfee")
	public void mgmtfee() {};
	
	// 아영
	@GetMapping("mymgmtfee/detail")
	public void mgmtfeedetail() {};
	
	// 아영
	@GetMapping("myvehicle")
	public void myvehicle() {};
	
}
