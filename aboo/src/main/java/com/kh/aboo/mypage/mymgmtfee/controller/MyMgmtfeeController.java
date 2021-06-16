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
import com.kh.aboo.common.code.AlarmCode;
import com.kh.aboo.mypage.myalarm.model.service.MyAlarmService;
import com.kh.aboo.mypage.mymgmtfee.model.service.MyMgmtfeeService;
import com.kh.aboo.mypage.mymgmtfee.model.vo.MgmtfeePayment;
import com.kh.aboo.user.generation.model.vo.Generation;

@Controller
@RequestMapping("mypage")
public class MyMgmtfeeController {

	private final MyMgmtfeeService myMgmtfeeService;
	private final MyAlarmService myAlarmService;

	public MyMgmtfeeController(MyMgmtfeeService myMgmtfeeService, MyAlarmService myAlarmService) {
		this.myMgmtfeeService = myMgmtfeeService;
		this.myAlarmService = myAlarmService;
	}

	// 페이징
	@GetMapping("mymgmtfee")
	public void myMgmtfee(@RequestParam(defaultValue = "1") int page,
			@SessionAttribute(name = "generation", required = false) Generation generation, Model model) {
		String generationIdx = generation.getGenerationIdx();
		System.out.println("page" + page);

		model.addAllAttributes(myMgmtfeeService.selectMyMgmtfeeList(page, generationIdx));
		model.addAttribute(generation);
	};

	// 관리비상세
	@GetMapping("mymgmtfee/detail")
	public void myMgmtfeeDetail(@RequestParam String mgmtfeeidx, Model model) {
		Mgmtfee mgmtfee = myMgmtfeeService.selectMyMgmtfeeByMgmtfeeIdx(mgmtfeeidx);
		Map<String, Object> mgmtdate = myMgmtfeeService.selectMyMgmtfeeDate(mgmtfeeidx);

		// 연체료정보도 보내준다
		MgmtfeeOverdue mgmtfeeOverdue = myMgmtfeeService.selectMyMgmtfeeOverdue(mgmtfeeidx);
		if (mgmtfeeOverdue != null) {
			model.addAttribute(mgmtfeeOverdue);
		}

		model.addAttribute(mgmtfee);
		model.addAttribute("mgmtdate", mgmtdate);
	};

	//선영 결제
	@PostMapping("mymgmtfee/payment")
	@ResponseBody
	public String payment(@RequestBody MgmtfeePayment mgmtfeePayment) {
		String generationIdx = myMgmtfeeService.selectPaymentGenerationIdx(mgmtfeePayment.getMgmtfeeIdx());
		myAlarmService.insertPvAlarm(AlarmCode.PAY_MGMTFEE+"",generationIdx);
		myMgmtfeeService.insertPayment(mgmtfeePayment); 
		return "success";

	}

}
