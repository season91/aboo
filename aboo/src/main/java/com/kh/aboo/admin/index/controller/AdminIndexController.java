package com.kh.aboo.admin.index.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

	public AdminIndexController(IndexNoticeService indexNoticeService, ScheduleService scheduleService,
			UsedService usedService) {
		this.indexNoticeService = indexNoticeService;
		this.scheduleService = scheduleService;
		this.usedService = usedService;
	}

	@GetMapping("admin/index")
	public String admin(HttpSession session, Model model) {
		List<Notice> noticeList = indexNoticeService.selectIndexNotice();
		model.addAttribute("noticeList", noticeList);

		List<Map<String, Object>> usedMap = usedService.selectUsedBrdYearCnt();
		List<Map<String, Object>> infoMap = usedService.selectInfoBrdYearCnt();
		List<Map<String, Object>> intMap = usedService.selectIntBrdYearCnt();

		List<Object> usedList = new ArrayList<Object>();
		for (Map<String, Object> map : usedMap) {
			usedList.add(map.get("CNT"));

		}
		List<Object> infoList = new ArrayList<Object>();
		for (Map<String, Object> map : infoMap) {
			infoList.add(map.get("CNT"));

		}
		List<Object> intList = new ArrayList<Object>();
		for (Map<String, Object> map : intMap) {
			intList.add(map.get("CNT"));
		}

		System.out.println("usedList" + usedList);
		System.out.println("infoList" + infoList);
		System.out.println("intList" + intList);

		Admin admin = (Admin) session.getAttribute("admin");

		model.addAttribute("schedule", scheduleService.selectScheduleByMonth());
		model.addAttribute("aptName", scheduleService.selectAptNameByIdx(admin.getApartmentIdx()));

		model.addAttribute("usedList", usedList);
		model.addAttribute("infoList", infoList);
		model.addAttribute("intList", intList);

		return "admin/index";
	}

}