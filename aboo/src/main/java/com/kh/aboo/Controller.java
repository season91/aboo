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
	
	
	
	@GetMapping("common/adminresult")
	public void adminresult() {};
	
}