package com.kh.aboo.user.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;

import com.kh.aboo.common.util.ramdom.Ramdom;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.service.AdminService;
import com.kh.aboo.user.manager.model.vo.Admin;
import com.kh.aboo.user.manager.validator.AdminValidator;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private PasswordEncoder encoder;

	Ramdom random = new Ramdom();

	private final AdminService adminService;
	private final AdminValidator adminValidator;

	public AdminController(AdminService adminService, AdminValidator adminValidator) {
		this.adminService = adminService;
		this.adminValidator = adminValidator;
	}

	@InitBinder(value = "admin")
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(adminValidator);

	}

	// 선영
	@GetMapping("index")
	public String admin() {
		return "admin/index";
	}

	// 선영
	@GetMapping("login")
	public String login() {
		return "admin/login";
	}

	// 선영
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/admin/index";
	}

	// 선영
	@PostMapping("loginimpl")
	@ResponseBody
	public String loginimpl(@RequestBody Admin adminInfo, HttpSession session) {

		// adminInfo : 받아와서 맵핑 해주는 객체 이름
		// admin : 진짜 admin 정보가 담긴 객체 이름

		Admin admin = adminService.selectAdminForAuth(adminInfo);
		if (admin == null) {
			return "fail";
		}
		session.setAttribute("admin", admin);
		return "sussece";

	}

	// 선영
	@GetMapping("authority")
	public String adminAuthority(@RequestParam(defaultValue = "1") int page
			,@SessionAttribute(name = "admin", required = false) Admin admin
			,@RequestParam(defaultValue = "apartmentIdx") String kind
			,@RequestParam(defaultValue = "keyword") String keyword
			,Model model) {
		
		String apartmentIdx = admin.getApartmentIdx();
		
		model.addAllAttributes(adminService.selectAuthorityList(page,apartmentIdx,kind,keyword));

		return "admin/authority";
	}

	// 선영
	@PostMapping("authorityadd")
	public String authorityAdd(Generation generationInfo,
			@SessionAttribute(name = "admin", required = false) Admin admin, Model model) {

		adminService.insertGeneration(generationInfo, admin.getApartmentIdx());

		model.addAttribute("alertMsg", "추가 되었습니다.");
		model.addAttribute("url", "/admin/authority");
		return "common/result";

	}

	// 선영
	@GetMapping("findid")
	public String findId() {
		return "admin/findId";
	}

	@PostMapping("findidimpl")
	@ResponseBody
	public String findIdImpl(@RequestBody Admin adminInfo, HttpSession session) {
		System.out.println(adminInfo);

		Admin findAdmin = adminService.selectfindId(adminInfo);

		if (findAdmin == null) {
			return "fail";
		} else {

			String authPathId = UUID.randomUUID().toString().replace("-", "");
			authPathId = authPathId.substring(0, 10);

			session.setAttribute("authPathId", authPathId);
			session.setAttribute("findAdmin", findAdmin);
			adminService.findIdEmail(adminInfo, authPathId);
			return "success";
		}

	}

	@GetMapping("authid")
	@ResponseBody
	public String authId(@RequestParam String certifiedNum, HttpSession session, Model model) {

		String authPathId = (String) session.getAttribute("authPathId");

		System.out.println("아이디 인증번호" + authPathId);

		if (!certifiedNum.equals(authPathId)) {
			return "fail";
		}

		return "success";

	}

	@GetMapping("findidresult")
	public String findIdResult(HttpSession session, Model model) {

		Admin findAdmin = (Admin) session.getAttribute("findAdmin");

		model.addAttribute("findAdmin", findAdmin);

		return "/admin/findIdResult";
	}

	@GetMapping("findpassword")
	public String findPassword() {
		return "admin/findPassword";
	}

	// 선영 임시 비밀번호 발급과 DB변경
	@PostMapping("findpasswordimpl")
	@ResponseBody
	public String findPasswordImpl(@RequestBody Admin adminInfo, HttpSession session, Model model) {

		Admin findAdmin = adminService.selectFindPassword(adminInfo);
		System.out.println(findAdmin);
		if (findAdmin == null) {
			return "fail";
		} else {

			String password = random.randomPw();

			System.out.println("임시 번호 : " + password);

			adminService.findPasswordEmail(findAdmin, password); // 메일 보내기

			return "success";

		}

	}

	@GetMapping("/mypage/modifyinfo")
	public String modifyInfo(@SessionAttribute(name = "admin") Admin admin, Model model) {

		Admin selectAdmin = adminService.selectAdmin(admin);
		System.out.println(selectAdmin);

		model.addAttribute("selectAdmin", selectAdmin);
		return "admin/mypage/modifyInfo";

	}

	@PostMapping("/mypage/modifyupdate")
	public String modifyInfo(@Valid Admin adminValid, Errors errors, @SessionAttribute(name = "admin") Admin admin,
			Model model) {

		Admin selectAdmin = adminService.selectAdmin(admin);
		if (errors.hasErrors()) {
			model.addAttribute("selectAdmin", selectAdmin);
			return "admin/mypage/modifyInfo";
		}

		adminValid.setManagerIdx(admin.getManagerIdx());
		adminService.updateAdminModify(adminValid);

		model.addAttribute("alertMsg", "수정되었습니다.");
		model.addAttribute("url", "/admin/mypage/modifyinfo");
		return "common/result";
	}

	// 이메일 인증
	@PostMapping("/mypage/modifyemailimpl")
	@ResponseBody
	public String modifyEmailImpl(@RequestBody Admin adminInfo, HttpSession session) {

		String authPathEmail = UUID.randomUUID().toString().replace("-", "");
		authPathEmail = authPathEmail.substring(0, 10);

		session.setAttribute("authPathEmail", authPathEmail);
		adminService.authEmail(adminInfo, authPathEmail);

		return "success";

	}

	// 이메일 인증
	@PostMapping("/mypage/authemail")
	@ResponseBody
	public String authEmail(@RequestBody Map<String, Object> info, HttpSession session) {

		String certifiedNum = (String) info.get("certifiedNum");
		String authPathEmail = (String) session.getAttribute("authPathEmail");

		if (!certifiedNum.equals(authPathEmail)) {
			return "fail";
		}

		Admin admin = (Admin) session.getAttribute("admin");
		String email = (String) info.get("email");
		admin.setEmail(email);
		adminService.updateAdminEmail(admin);
		return "success";
	}

	
	// 번호 인증
	@PostMapping("/mypage/modifytellimpl")
	@ResponseBody
	public String modifyTellImpl(@RequestBody Admin adminInfo, HttpSession session) {

		int res = adminService.authTell(adminInfo.getTell(), session);

		if (res != 202) {
			
			return "fail";
		}

		return "success";

	}

	// 번호 인증
	@PostMapping("/mypage/authtell")
	@ResponseBody
	public String authTell(@RequestBody Map<String, Object> info, HttpSession session) {
		String certifiedPNum = (String) info.get("certifiedPNum");
		String authPathTell = (String) session.getAttribute("authPathTell");

		if (!certifiedPNum.equals(authPathTell)) {
			return "fail";
		}

		Admin admin = (Admin) session.getAttribute("admin");
		String tell = (String) info.get("tell");
		admin.setTell(tell);
		adminService.updateAdminTell(admin);
		return "success";

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 세대 초기화
	@PostMapping("authorityreset")
	@ResponseBody
	public String authorityReset(@RequestBody Generation GenerationInfo, @SessionAttribute("admin") Admin admin) {
		System.out.println(GenerationInfo);

		String apartmentIdx = admin.getApartmentIdx();
		GenerationInfo.setApartmentIdx(apartmentIdx);

		adminService.updateResetGeneration(GenerationInfo);

		return "success";

	}

	// 세대 삭제
	@PostMapping("authoritydelete")
	@ResponseBody
	public String authorityDelete(@RequestBody Generation GenerationInfo) {

		adminService.updateDeleteGeneration(GenerationInfo);

		return "success";

	}

	// 선영 어드민 추가 메서드 이거 쓰세용
	@GetMapping("add")
	public void adminAdd() {
		Admin admin = new Admin();
		admin.setId("admin3");
		admin.setPassword(encoder.encode("admin3"));
		admin.setName("어드민3");
		admin.setTell("010-9268-0961");
		admin.setEmail("psuny1031@naver.com");
		String birth = "2000-02-28";
		java.sql.Date birthday = java.sql.Date.valueOf(birth);
		admin.setBirth(birthday);
		System.out.println(admin);

		adminService.insertAdmin(admin);
	}

}
