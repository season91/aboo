package com.kh.aboo.admin.mgmtfee.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.admin.mgmtfee.model.service.MgmtfeeService;
import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.common.util.file.FileUtil;

@Controller
public class MgmtfeeController {
	private final MgmtfeeService mgmtfeeService;

	public MgmtfeeController(MgmtfeeService mgmtfeeService) {
		this.mgmtfeeService = mgmtfeeService;
	}


	// 아영 : 업로드된 엑셀파일 읽기. 비동기통신
	@PostMapping(value = "admin/mgmtfee/uploadimpl")
	@ResponseBody
	public String mgmtUpload(@RequestParam MultipartFile file, Model model) {
		System.out.println("여기오나??");

		Map<String, Object> commandMap = mgmtfeeService.mgmtfeeRead(file);
		List<Mgmtfee> mgmtfeeList = mgmtfeeService.insertMgmtfee(commandMap);
		System.out.println(mgmtfeeList.size());

		// 성공 실패 분기나누기
		if (mgmtfeeList == null || mgmtfeeList.size() == 0) {
			System.out.println("실패유");
			return "fail";
		}

		return "success";

	}

	// 아영 : 관리비 엑셀 양식 다운로드 받기
	@GetMapping("admin/mgmtfeeformdownload")
	public ResponseEntity<FileSystemResource> mgmtFormDownload() {
		// 엑셀양식 다운로드하게
		System.out.println("양식만들기시작");
		//관리자 세션에 따른 아파트 정보 기준으로 양식 만든다.
		String apartmentIdx = "";
		
		FileUtil fileUtil = new FileUtil();
		// mgmtfeeFormExcel 엑셀 양식 호출.
		File file = fileUtil.mfmtgeeFormExcel(apartmentIdx);
		
		// 내보내기
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(
				ContentDisposition.builder("attachment").filename(file.getName(), Charset.forName("UTF-8")).build());
		FileSystemResource resource = new FileSystemResource(file);

		return ResponseEntity.ok().headers(headers).body(resource);
	}


}
