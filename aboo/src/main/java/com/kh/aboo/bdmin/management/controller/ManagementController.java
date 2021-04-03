package com.kh.aboo.bdmin.management.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.aboo.bdmin.management.model.service.ManagementService;
import com.kh.aboo.bdmin.management.model.vo.ApartApplication;
import com.kh.aboo.bdmin.management.model.vo.Bdmin;
import com.kh.aboo.user.apartment.model.vo.Apartment;

@Controller
@RequestMapping("/bdmin")
public class ManagementController {
	
	private final ManagementService managementService;
	
	public ManagementController(ManagementService managementService) {
		this.managementService = managementService;
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	// 로그인성공시 session 넣어주기, 세션명 bdmin
	@PostMapping("/loginimpl")
	@ResponseBody
	public String loginImpl(@RequestBody Bdmin bdminInfo, HttpSession session) {
		Bdmin bdmin = managementService.selectBdminForAuth(bdminInfo);
		
		if(bdmin == null) {
			return "fail";
		}

		session.setAttribute("bdmin", bdmin);
		return "success";
	}
	
	// 로그아웃시 세션지운다.
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("bdmin");
		return "redirect:/admin/index";
	}

	
	// 현재 서비스 이용 중인 아파트리스트
	@GetMapping("/management/apartment")
	public void apartment(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "basic") String standard, @RequestParam(defaultValue = "basic") String keyword, Model model) {
		
		// 반환형은 map이고 여기엔 검색기준, 검색값, 페이징, 검색결과list가 들어있다.
		// 자세한건 service impl에 구현
		// view에서는 페이징부분이 관건인데, choose문을 searchType을 이용해 페이징처리 분기를 나눈다.
		model.addAllAttributes(managementService.selectApartList(page, standard, keyword));
	}
	
	@GetMapping("/management/apartmentdetail")
	public void apartmentDetail(@RequestParam String apartmentIdx, Model model) {
		System.out.println(apartmentIdx);
		
		model.addAttribute(managementService.selectApartment(apartmentIdx));
	}
	
	@PostMapping("/management/apartment/modify")
	public String apartmentModify(Apartment apartment, Model model) {
		System.out.println(apartment);
		managementService.updateApartment(apartment);
		model.addAttribute("alertMsg", "수정이 완료되었습니다");
		model.addAttribute("url", "/bdmin/apartment");
		return "common/result";
	}
	
	
	// 입점문의건 목록
	@GetMapping("/management/contactuslist")
	public void apartmentContactList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "basic") String standard, @RequestParam(defaultValue = "basic") String keyword, Model model) {
		model.addAllAttributes(managementService.selectApartApplicationList(page, standard, keyword));
	}
	
	// 입점문의 상세
	@GetMapping("/management/contactusdetail")
	public void apartmentContactDetail(String applicationIdx, Model model) {
		System.out.println(applicationIdx);
		model.addAttribute(managementService.selectApartApplication(applicationIdx));
	}
	
	// 입점문의 승인, 반려 업데이트
	@PostMapping("/contactus/process")
	public String contactUsProcess(ApartApplication apartApplication, Model model) {
		System.out.println("처리시작?"+apartApplication.getApplicationIdx()+"처리?"+apartApplication.getIsProcess());
		//프로시저로 처리
		managementService.procedureApartApplicationUpdate(apartApplication);
		model.addAttribute("alertMsg", "처리가 완료되었습니다.");

		model.addAttribute("url", "/bdmin/management/contactuslist");
		return "common/result";
	}
	
	@GetMapping("/contactus")
	public String contactUs() {
		System.out.println("오나");
		return "/bdmin/contactus";
	}
	
	@PostMapping("/contactusimpl")
	public String contactUsImpl(ApartApplication apartApplication, Model model) {
		// 신청서 작성시 신청서 insert한다.
		System.out.println(apartApplication);
		managementService.insertApartApplication(apartApplication);
		model.addAttribute("alertMsg", "등록신청이 완료되었습니다. 처리까지는 4~5일 소요되며, 승인시 담당자가 연락할 예정입니다.");
		model.addAttribute("url", "/bdmin/contactus");
		return "common/result";
	}
}
