package com.kh.aboo.bdmin.management.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
@RequestMapping("/bdmin")
public class ManagementController {
	
	@Autowired
	private PasswordEncoder encoder;
	
	private final ManagementService managementService;
	
	public ManagementController(ManagementService managementService) {
		this.managementService = managementService;
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	// [로그인 로그아웃]
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
		return "redirect:/bdmin/login";
	}

	// [관리업무- 1.아파트관리]
	// 현재 서비스 이용 중인 아파트리스트
	@GetMapping("/management/apartment")
	public void apartment(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "basic") String standard, @RequestParam(defaultValue = "basic") String keyword, Model model) {
		
		// 반환형은 map이고 여기엔 검색기준, 검색값, 페이징, 검색결과list가 들어있다.
		// 자세한건 service impl에 구현
		// view에서는 페이징부분이 관건인데, choose문을 searchType을 이용해 페이징처리 분기를 나눈다.
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType", standard);
		searchMap.put("keyword", keyword);
		model.addAllAttributes(managementService.selectApartList(page, searchMap));
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
		model.addAttribute("url", "/bdmin/management/apartment");
		return "common/result";
	}
	
	// [관리업무- 2.아파트 서비스 신청 관리]
	// 입점문의건 목록
	@GetMapping("/management/contactuslist")
	public void contactList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "basic") String standard, @RequestParam(defaultValue = "basic") String keyword, Model model) {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType", standard);
		searchMap.put("keyword", keyword);
		model.addAllAttributes(managementService.selectApartApplicationList(page, searchMap));
	}
	
	// 입점문의 상세
	@GetMapping("/management/contactusdetail")
	public void contactDetail(String applicationIdx, Model model) {
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
	
	// [관리업무- 3.아파트관리자 관리]
	// 아파트관리자 목록
	@GetMapping("/management/adminauthority")
	public void adminAuthority(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "basic") String standard, @RequestParam(defaultValue = "basic") String keyword, Model model) {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType", standard);
		searchMap.put("keyword", keyword);
		
		if(standard.equals("apartmentName")) {
			String[] apartmentIndx = keyword.split("/");
			// [0]은 아파트이름이고 [1]아파트 idx이다.
			keyword = apartmentIndx[1];
			searchMap.put("keyword", keyword);
		}
		
		System.out.println(searchMap);
		model.addAllAttributes(managementService.selectAdminList(page, searchMap));
	}
	
	// 관리자 신규등록
	@PostMapping("/management/adminadd")
	public String adminAdd(Admin admin, @RequestParam String apartmentInfo, Model model) {
		
		String[] apartmentIndx = apartmentInfo.split("/");
		// [0]은 아파트이름이고 [1]아파트 idx이다.
		admin.setApartmentIdx(apartmentIndx[1]);
		admin.setPassword(encoder.encode(admin.getPassword()));
		managementService.insertAdmin(admin);
		
		model.addAttribute("alertMsg", "권한부여가 되었습니다.");
		model.addAttribute("url", "/bdmin/management/adminauthority");
		return "common/result";
	}
	
	// 비동기통신
	@GetMapping("/management/admindelete")
	@ResponseBody
	public void adminDelete(@RequestParam String manageridx) {
		 managementService.updateAdminIsDel(manageridx);
	}
		
}
