package com.kh.aboo.board.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board/info")
public class InfoController {


	@GetMapping("listinfo")
	public void listInfo() {};
	
	@GetMapping("detailinfo")
	public void detailInfo() {};
	
	@GetMapping("addinfo")
	public void addInfo() {};
	
	@GetMapping("editinfo")
	public void editInfo() {};
	
	
}
