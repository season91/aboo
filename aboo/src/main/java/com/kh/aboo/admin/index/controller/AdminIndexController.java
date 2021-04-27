package com.kh.aboo.admin.index.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.aboo.admin.index.model.service.IndexManagementService;
import com.kh.aboo.admin.index.model.service.IndexNoticeService;
import com.kh.aboo.admin.schedule.model.service.ScheduleService;
import com.kh.aboo.bdmin.notice.model.vo.Notice;
import com.kh.aboo.board.used.model.service.UsedService;
import com.kh.aboo.board.used.model.service.impl.UsedServiceImpl;
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
public class AdminIndexController {

	private final IndexNoticeService indexNoticeService;
	private final ScheduleService scheduleService;
	private final UsedService usedService;
	private final IndexManagementService indexManagementService;

	public AdminIndexController(IndexNoticeService indexNoticeService, ScheduleService scheduleService,
			UsedService usedService, IndexManagementService indexManagementService) {
		this.indexNoticeService = indexNoticeService;
		this.scheduleService = scheduleService;
		this.usedService = usedService;
		this.indexManagementService = indexManagementService;
	}

	@GetMapping("admin/index")
	public String admin(HttpSession session, Model model) {
		// 민희-공지사항
		List<Notice> noticeList = indexNoticeService.selectIndexNotice();
		model.addAttribute("noticeList", noticeList);
		
		// 희원-스캐쥴
		Admin admin = (Admin) session.getAttribute("admin");
		model.addAttribute("schedule", scheduleService.selectScheduleByMonth());
		model.addAttribute("aptName", scheduleService.selectAptNameByIdx(admin.getApartmentIdx()));
		
		//아영-관리비
		List<Integer> list = indexManagementService.selectMgmtfeeMonthFee(admin.getApartmentIdx());
		model.addAttribute("list", list);
		
		
		//선영-작성글현황
		int usedCnt = usedService.selectUsedBrdTodayCnt(admin.getApartmentIdx());
		int infoCnt = usedService.selectInfoBrdTodayCnt(admin.getApartmentIdx());
		int intCnt = usedService.selectIntBrdTodayCnt(admin.getApartmentIdx());
		System.out.println(usedCnt + ":" + infoCnt + ":" + intCnt);
	

		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("infoCnt", infoCnt);
		model.addAttribute("intCnt", intCnt);

		return "admin/index";
	}

}
