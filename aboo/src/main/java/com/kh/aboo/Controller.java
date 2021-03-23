package com.kh.aboo;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

	// 곧 지울 파일 임!!! 다 옮겨지면 지울 예정
	// 아영시작 
	@GetMapping("generation")
	public String index() {
		return "generation/index";
	}
	
	@GetMapping("admin/index")
	public String admin() {
		return "admin/index";
	}
	
	@GetMapping("myapt/parking")
	public void parking() {};
	
	@GetMapping("mypage/mgmtfee")
	public void mgmtfee() {};
	
	@GetMapping("mypage/mgmtfeedetail")
	public void mgmtfeedetail() {};
	
	@GetMapping("mypage/myvehicle")
	public void myvehicle() {};
	
	@GetMapping("admin/mgmtfeelist")
	public void adminMgmtfee() {};
	
	@GetMapping("admin/mgmtfeeupload")
	public void adminMgmtfeeUpload() {};
	
	@GetMapping("admin/vehicle")
	public void adminVehicle() {};
	
	// 아영끝
	
	// 민희꺼 테스트
	@GetMapping("myapt/vote/votelist")
	public void voteList() {};
	
	
}