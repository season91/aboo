package com.kh.aboo.mypage.mymgmtfee.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.admin.mgmtfee.model.vo.MgmtfeeOverdue;
import com.kh.aboo.mypage.mymgmtfee.model.service.MyMgmtfeeService;
import com.kh.aboo.mypage.mymgmtfee.model.vo.MgmtfeePayment;
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
	public void mgmtfee(@RequestParam(defaultValue = "1") int page,
			@SessionAttribute(name = "generation", required = false) Generation generation, Model model) {
		String generationIdx = generation.getGenerationIdx();
		System.out.println("page" + page);

		model.addAllAttributes(myMgmtfeeService.selectMyMgmtfeeList(page, generationIdx));
		model.addAttribute(generation);
	};

	// 관리비상세
	@GetMapping("mymgmtfee/detail")
	public void mgmtfeedetail(@RequestParam String mgmtfeeidx,
			@SessionAttribute(name = "generation", required = false) Generation generation, Model model) {
		System.out.println("넘어오나?" + mgmtfeeidx);
		Mgmtfee mgmtfee = myMgmtfeeService.selectMyMgmtfeeByMgmtfeeIdx(mgmtfeeidx);
		Map<String, Object> mgmtdate = myMgmtfeeService.selectMyMgmtfeeDate(mgmtfeeidx);

		System.out.println("mgmtfee" + mgmtfee);
		// 연체료정보도 보내준다
		MgmtfeeOverdue mgmtfeeOverdue = myMgmtfeeService.selectMyMgmtfeeOverdue(mgmtfeeidx);
		if (mgmtfeeOverdue != null) {
			model.addAttribute(mgmtfeeOverdue);
			System.out.println("연체있다" + mgmtfeeOverdue);
		}

		model.addAttribute(mgmtfee);
		model.addAttribute("mgmtdate", mgmtdate);
	};

	// 아영
	@GetMapping("myvehicle")
	public void myvehicle() {
	};

	
	//선영 결제
	@PostMapping("payment")
	@ResponseBody
	public String payment(@RequestBody MgmtfeePayment mgmtfeePayment) {
		int res = myMgmtfeeService.insertPayment(mgmtfeePayment);
		if (res > 0) {
			return "success";
		}
		return "fail";
	}

}
