package com.kh.aboo.admin.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.admin.schedule.model.service.ScheduleService;
import com.kh.aboo.admin.schedule.model.vo.Schedule;
import com.kh.aboo.board.info.model.service.InfoService;
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
@RequestMapping("admin/schedule")
public class ScheduleController {
	
	private final ScheduleService scheduleService;
	
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	@GetMapping("addschedule")
	public String schedule() {
		return "admin/schedule";
	}
	
	@PostMapping("addscheduleimpl")
	public String addScheduleImpl(@SessionAttribute(name = "admin", required = false) Admin admin,Schedule schedule,Model model) {
		
		//나중에 세션값으로 바꾸기
		schedule.setApartmentIdx("100000");
		
		int res = scheduleService.insertSchedule(schedule);
		
		if(res > 0) {
			model.addAttribute("alertMsg", "일정이 등록되었습니다.");
			model.addAttribute("url", "/admin/schedule/addschedule");
		}else {
			model.addAttribute("alertMsg", "일정 등록에 실패하였습니다.");
			model.addAttribute("url", "/admin/schedule/addschedule");
		}
		
		return "common/result";
	}

}