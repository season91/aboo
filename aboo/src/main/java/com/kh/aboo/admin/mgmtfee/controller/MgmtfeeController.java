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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.admin.mgmtfee.model.service.MgmtfeeService;
import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.admin.mgmtfee.model.vo.MgmtfeeOverdue;
import com.kh.aboo.common.code.AlarmCode;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.mypage.myalarm.model.service.MyAlarmService;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
public class MgmtfeeController {
	private final MgmtfeeService mgmtfeeService;
	private final MyAlarmService myAlarmService;

	public MgmtfeeController(MgmtfeeService mgmtfeeService, MyAlarmService myAlarmService) {
		this.mgmtfeeService = mgmtfeeService;
		this.myAlarmService = myAlarmService;
	}

	// 페이징처리
	@GetMapping("admin/mgmtfee")
	public void adminMgmtfee(@RequestParam(defaultValue = "1") int page, @SessionAttribute(name = "admin", required = false) Admin admin, @RequestParam(defaultValue = "apartmentIdx") String standard,  @RequestParam(defaultValue = "apartmentIdx") String keyword, Model model) {
		String apartmentIdx = admin.getApartmentIdx();
		// 반환형은 map이고 여기엔 검색기준, 세대정보, 검색값, 페이징, 검색결과list가 들어있다.
		// 자세한건 service impl에 구현
		// view에서는 페이징부분이 관건인데, choose문을 searchType을 이용해 페이징처리 분기를 나눈다.
		model.addAllAttributes(mgmtfeeService.selectMgmtfeeList(page, apartmentIdx, standard, keyword));
	};
		

	// 업로드된 엑셀파일 읽고 DB에 넣어주기. 비동기통신
	@PostMapping(value = "admin/mgmtfee/uploadimpl")
	@ResponseBody
	public String mgmtUpload(@RequestParam MultipartFile file, @SessionAttribute(name = "admin", required = false) Admin admin) {
		Map<String, Object> commandMap = mgmtfeeService.mgmtfeeRead(file);
		// 업로드할때 관리자의 아파트번호의 아파트세대에게 관리비를 보내야한다.
		
		String apartmentIdx = admin.getApartmentIdx();
		List<Mgmtfee> mgmtfeeList = mgmtfeeService.insertMgmtfee(commandMap, apartmentIdx);
		
		if(mgmtfeeList == null || mgmtfeeList.size() == 0 || mgmtfeeList.get(0).getPeriodPayment().equals("")) {
			return "fail";
		}
		
		// 성공시 알람넣어주기.
		for (int i = 0; i < mgmtfeeList.size(); i++) {
			myAlarmService.insertPvAlarm(AlarmCode.ADD_MGMTFEE+"", mgmtfeeList.get(i).getGenerationIdx());
		}
		
		return "success";

	}

	// 관리비 엑셀 양식 다운로드 받기
	@GetMapping("admin/mgmtfeeformdownload")
	public ResponseEntity<FileSystemResource> mgmtFormDownload(@SessionAttribute(name = "admin", required = false) Admin admin) {
		// 엑셀양식 다운로드하게
		// 만드려면 필요한것 : 아파트번호
		// 아파트번호는 로그인한 관리자 세션을 통해 가져온다.
		// 관리인 정보 기준으로 아파트 번호 받아와 세대정보 보내준다.
		String apartmentIdx = admin.getApartmentIdx();
		File file  = mgmtfeeService.selectGenerationList(apartmentIdx);
		
		// 내보내기
		HttpHeaders headers= new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName(), Charset.forName("UTF-8"))
				.build());
		FileSystemResource resource = new FileSystemResource(file);
		
		return ResponseEntity.ok().headers(headers).body(resource);
	}

	// 수정,삭제하기의 수정
	@GetMapping("admin/mgmtfee/modify")
	public void mgmtfeeModify(@SessionAttribute(name = "admin", required = false) Admin admin, @RequestParam String mgmtfeeidx, Model model) {
		//관리비번호로 내역조회
		//조회관리비 기준세대정보 가져오기
		Mgmtfee mgmtfee = mgmtfeeService.selectMgmtfeeByMgmtfeeIdx(mgmtfeeidx);
		
		//로그인한 관리자의 아파트번호와 관리비의 아파트번호가 일치해야만 열람 가능
		if(!admin.getApartmentIdx().equals(mgmtfee.getApartmentIdx())) {
			throw new ToAlertException(ErrorCode.AUTH03);
		}
		
		String generationIdx = mgmtfee.getGenerationIdx();
		Generation generation = mgmtfeeService.selectGenerationByGenerationIdx(generationIdx);
		
		// 둘다 조회가 되었다면 내역 넘기고, 둘중 하나라도 조회내역이없다면 에러 발동한다.
		if(mgmtfee != null && generation != null) {
			
			model.addAttribute(mgmtfee);
			model.addAttribute(generation);
			model.addAttribute("overdue",mgmtfeeService.selectMgmtfeeOverdue(mgmtfee.getMgmtfeeIdx()));
		} else {
			throw new ToAlertException(ErrorCode.SMGMT01);
		}
	}
	
	@PostMapping("admin/mgmtfee/modifyimpl")
	public String mgmtfeeModifyImpl(Mgmtfee mgmtfee, @RequestParam String overdueFee, @RequestParam String isPaymentText, Model model) {
		
		if(isPaymentText.equals("미납")) {
			mgmtfee.setIsPayment(0);
		} else {
			mgmtfee.setIsPayment(1);
		}
		
		// 연체료가 없다면 안보내주고 있다면 보내준다.
		if(overdueFee != "0") {
			MgmtfeeOverdue mgmtfeeOverdue = new MgmtfeeOverdue();
			mgmtfeeOverdue.setMgmtfeeIdx(mgmtfee.getMgmtfeeIdx());
			mgmtfeeOverdue.setOverdueFee(overdueFee);
			int res = mgmtfeeService.updateMgmtfeeOverdue(mgmtfeeOverdue);
			if(res > 0) {
				model.addAttribute("overdue",mgmtfeeService.selectMgmtfeeOverdue(mgmtfee.getMgmtfeeIdx()));
			}
		} 
		
		Mgmtfee updateMgmtefee = mgmtfeeService.updateMgmtfee(mgmtfee);
		// 업데이트내역이 있다면 수정완료, 없다면 실패안내. mgmt update는 프로시저라 int로 판단안함. 
		if(updateMgmtefee != null) {
			myAlarmService.insertPvAlarm(AlarmCode.MODIFY_MGMTFEE+"", mgmtfee.getGenerationIdx());
			model.addAttribute("alertMsg", "수정이 완료되었습니다.");
			model.addAttribute("url", "/admin/mgmtfee/modify?mgmtfeeidx="+mgmtfee.getMgmtfeeIdx());
			model.addAttribute("mgmtfee",updateMgmtefee);
		} else {
			model.addAttribute("alertMsg", "수정이 실패하였습니다.");
			model.addAttribute("url", "/admin/mgmtfee/modify?mgmtfeeidx="+mgmtfee.getMgmtfeeIdx());
		}
		return "common/result";
	}
	
	@GetMapping("admin/mgmtfeedelete")
	public String mgmtfeeDelete(@RequestParam String mgmtfeeidx, Model model) {
		int res = mgmtfeeService.updateMgmtfeeIsDel(mgmtfeeidx);
		
		// 삭제가 되었다면 완료, 삭제내역이없다면 실패 안내.
		if(res > 0) {
			model.addAttribute("alertMsg", "삭제가 완료되었습니다.");
			model.addAttribute("url", "/admin/mgmtfee");
		} else {
			model.addAttribute("alertMsg", "삭제가 실패하였습니다. 다시 확인해주세요.");
			model.addAttribute("url", "/admin/mgmtfee");
		}

		return "common/result";
	}
	
	//비동기통신이면 model 사용 못한다.
	@GetMapping("admin/mgmtfeedeletelist")
	@ResponseBody
	public void  mgmtfeeDeleteList(@RequestParam List<String> mgmtfeeidx) {
		for (int i = 0; i < mgmtfeeidx.size(); i++) {
			int res = mgmtfeeService.updateMgmtfeeIsDel(mgmtfeeidx.get(i));
			// 삭제가 되었다면 완료, 삭제내역이없다면 실패 안내.
			if(res == 0) {
				throw new ToAlertException(ErrorCode.DMGMT01);
			}
		}

	}
}
