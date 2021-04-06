package com.kh.aboo.admin.index.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.aboo.admin.index.model.service.IndexNoticeService;
import com.kh.aboo.admin.schedule.model.service.ScheduleService;
import com.kh.aboo.bdmin.notice.model.vo.Notice;
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
public class AdminIndexController {
	
	private final IndexNoticeService indexNoticeService;
	private final ScheduleService scheduleService;
	
	public AdminIndexController(IndexNoticeService indexNoticeService,ScheduleService scheduleService) {
		this.indexNoticeService = indexNoticeService;
		this.scheduleService = scheduleService;
	}
	
	@GetMapping("admin/index")
	public String admin(HttpSession session, Model model) {
		List<Notice> noticeList = indexNoticeService.selectIndexNotice();
		model.addAttribute("noticeList", noticeList);
		
		Admin admin = (Admin) session.getAttribute("admin");
		
		model.addAttribute("schedule",scheduleService.selectScheduleByMonth());
		model.addAttribute("aptName",scheduleService.selectAptNameByIdx(admin.getApartmentIdx()));
		
		return "admin/index";
	}
	
}
