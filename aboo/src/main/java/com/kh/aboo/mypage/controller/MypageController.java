package com.kh.aboo.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.aboo.mypage.model.service.MypageService;

@Controller
@RequestMapping("mypage")
public class MypageController {
	
	private final MypageService mypageService;
	
	public MypageController(MypageService mypageService) {
		this.mypageService = mypageService;
	}

	// 아영
	@GetMapping("mgmtfee")
	public void mgmtfee() {};
	
	// 아영
	@GetMapping("mgmtfee/detail")
	public void mgmtfeedetail() {};
	
	// 아영
	@GetMapping("myvehicle")
	public void myvehicle() {};
	
}
