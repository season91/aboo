package com.kh.aboo.mypage.mymgmtfee.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.mypage.mymgmtfee.model.service.MyMgmtfeeService;
import com.kh.aboo.user.generation.model.vo.Generation;

@Controller
@RequestMapping("mypage")
public class MyMgmtfeeController {
	
	private final MyMgmtfeeService myMgmtfeeService;
	
	public MyMgmtfeeController(MyMgmtfeeService myMgmtfeeService) {
		this.myMgmtfeeService = myMgmtfeeService;
	}

	// 페이징
	@GetMapping("mymgmtfee")
	public void mgmtfee(@RequestParam(defaultValue = "1") int page,@SessionAttribute(name = "generation", required = false) Generation generation, Model model) {
		String generationIdx = generation.getGenerationIdx();
		System.out.println("page"+page);
		model.addAllAttributes(myMgmtfeeService.selectMyMgmtfeeList(page, generationIdx));
		
	};
	
	// 관리비상세
	@GetMapping("mymgmtfee/detail")
	public void mgmtfeedetail(@RequestParam String mgmtfeeidx,@SessionAttribute(name = "generation", required = false) Generation generation, Model model) {
		System.out.println("넘어오나?"+mgmtfeeidx);
		Mgmtfee mgmtfee = myMgmtfeeService.selectMyMgmtfeeByMgmtfeeIdx(mgmtfeeidx);
		Map<String,Object> mgmtdate = myMgmtfeeService.selectMyMgmtfeeDate(mgmtfeeidx);

		model.addAttribute(mgmtfee);
		model.addAttribute("mgmtdate",mgmtdate);
		System.out.println(mgmtdate.get("YEAR"));
		System.out.println(mgmtdate.get("MONTH"));
	};
	
	// 아영
	@GetMapping("myvehicle")
	public void myvehicle() {};
	
}
