package com.kh.aboo.user.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.aboo.user.admin.model.service.AdminService;
import com.kh.aboo.user.admin.model.vo.Admin;
import com.kh.aboo.user.generation.model.vo.Generation;

@Controller
public class AdminController {

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("admin/login")
	public String login() {
		return "admin/login";
	}

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

	@GetMapping("admin/authority")
	public String adminAuthority() {
		return "admin/authority";
	}
	
	@PostMapping("admin/authorityadd")
	@ResponseBody
	public String authorityAdd(@RequestBody Generation generationInfo) {
		
		
		System.out.println(generationInfo);
		
		return "susesse";
	}

}
