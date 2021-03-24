package com.kh.aboo.user.admin.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.util.file.FileUtil;
import com.kh.aboo.user.admin.model.service.AdminService;
import com.kh.aboo.user.admin.model.vo.Admin;
import com.kh.aboo.user.admin.model.vo.Mgmtfee;
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


	// 아영 : 업로드된 엑셀파일 읽기. 비동기통신
	@PostMapping(value="admin/mgmtfee/uploadimpl",headers = ("content-type=multipart/*"))
	public ResponseEntity<HttpStatus> mgmtUpload(@RequestParam MultipartFile file, Model model) {
		System.out.println("여기오나??");

		Map<String, Object> commandMap = adminService.mgmtfeeRead(file);
		List<Mgmtfee> mgmtfeeList = adminService.addMgmtfee(commandMap);
		System.out.println(mgmtfeeList.toString());
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}
	
	// 아영 : 관리비 엑셀 양식 다운로드 받기
	@GetMapping("admin/mgmtfeeformdownload")
	public ResponseEntity<FileSystemResource> mgmtFormDownload() {
		// 엑셀양식 다운로드하게
		System.out.println("양식만들기시작");
		
		FileUtil fileUtil = new FileUtil();
		// mgmtfeeFormExcel 엑셀 양식 호출.
		File file = fileUtil.mfmtgeeFormExcel();
		
		// 내보내기
		HttpHeaders headers= new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName(), Charset.forName("UTF-8"))
				.build());
		FileSystemResource resource = new FileSystemResource(file);
		
		return ResponseEntity.ok().headers(headers).body(resource);
	}
	
}
