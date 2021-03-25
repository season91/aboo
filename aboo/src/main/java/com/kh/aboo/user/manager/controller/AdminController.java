package com.kh.aboo.user.manager.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.util.file.FileUtil;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.service.AdminService;
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
public class AdminController {

	@Autowired
	private PasswordEncoder encoder;

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	// 선영
	@GetMapping("admin/index")
	public String admin() {
		return "admin/index";
	}

	// 선영
	@GetMapping("admin/login")
	public String login() {
		return "admin/login";
	}

	// 선영
	@PostMapping("admin/loginimpl")
	@ResponseBody
	public String loginimpl(@RequestBody Admin adminInfo, HttpSession session) {

		// adminInfo : 받아와서 맵핑 해주는 객체 이름
		// admin : 진짜 generation 정보가 담긴 객체 이름

		Admin admin = adminService.selectGenerationForAuth(adminInfo);
		if (admin == null) {
			return "fail";
		}
		session.setAttribute("admin", admin);
		return "sussece";

	}

	// 선영
	@GetMapping("admin/authority")
	public String adminAuthority(@RequestParam(defaultValue = "1") int page
			,@SessionAttribute(name = "admin", required = false)Admin admin, Model model) {
				
		model.addAllAttributes(adminService.selectauthorityList(page,admin.getApartmentIdx()));
		
		return "admin/authority";
	}

	// 선영
	@PostMapping("admin/authorityadd")
	@ResponseBody
	public String authorityAdd(@RequestBody Generation generationInfo,
			@SessionAttribute(name = "admin", required = false) Admin admin) {

		int res = adminService.insertGeneration(generationInfo, admin.getApartmentIdx());

		if (res == 0) {
			throw new ToAlertException(ErrorCode.AUTH01);
		}
		return "susesse";
	}

	// 선영
	@GetMapping("admin/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/admin/index";
	}

	// 선영 어드민 추가 메서드 이거 쓰세용
	@GetMapping("admin/add")
	public void adminAdd() {
		Admin admin = new Admin();
		admin.setId("admin2");
		admin.setPassword(encoder.encode("admin2"));
		admin.setName("어드민2");
		admin.setTell("010-9268-0961");
		admin.setEmail("suny10312@naver.com");
		String birth = "2000-02-28";
		java.sql.Date birthday = java.sql.Date.valueOf(birth);
		admin.setBirth(birthday);
		System.out.println(admin);

		adminService.insertAdmin(admin);
	}

}
