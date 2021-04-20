package com.kh.aboo.admin.schedule.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.admin.schedule.model.service.ScheduleService;
import com.kh.aboo.admin.schedule.model.vo.Schedule;
import com.kh.aboo.board.info.model.service.InfoService;
import com.kh.aboo.common.code.AlarmCode;
import com.kh.aboo.mypage.myalarm.model.service.MyAlarmService;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
@RequestMapping("admin/schedule")
public class ScheduleController {
	
	private final ScheduleService scheduleService;
	private final MyAlarmService myAlarmService;
	
	public ScheduleController(ScheduleService scheduleService,MyAlarmService myAlarmService) {
		this.scheduleService = scheduleService;
		this.myAlarmService = myAlarmService;
	}
	
	@GetMapping("addschedule")
	public String schedule(@RequestParam(defaultValue = "1") int page,HttpSession session,Model model,@RequestParam(defaultValue = "apartmentIdx") String standard,  @RequestParam(defaultValue = "apartmentIdx") String keyword) {
		
		Admin admin = (Admin) session.getAttribute("admin");
			
		String apartmentIdx = admin.getApartmentIdx();
		model.addAllAttributes(scheduleService.selectScheduleList(page, apartmentIdx, standard, keyword));
		model.addAttribute("aptName",scheduleService.selectAptNameByIdx(apartmentIdx));
			
			return "admin/schedule";
	}
	
	@PostMapping("addscheduleimpl")
	public String addScheduleImpl(@SessionAttribute(name = "admin", required = false) Admin admin,Schedule schedule,Model model) {
		
		//나중에 세션값으로 바꾸기
		schedule.setApartmentIdx(admin.getApartmentIdx());
		String content = schedule.getScheduleCon();
		content = content.replaceAll("(\r\n|\r|\n|\n\r)", "");
		schedule.setScheduleCon(content);
		
		int res = scheduleService.insertSchedule(schedule);
		
		if(res > 0) {
			myAlarmService.insertAptAlarm("'" + schedule.getScheduleCon() + "' " + AlarmCode.ADD_SCHEDULE, admin.getApartmentIdx());
			System.out.println(AlarmCode.ADD_SCHEDULE);
			model.addAttribute("alertMsg", "일정이 등록되었습니다.");
			model.addAttribute("url", "/admin/schedule/addschedule");
		}else {
			model.addAttribute("alertMsg", "일정 등록에 실패하였습니다.");
			model.addAttribute("url", "/admin/schedule/addschedule");
		}
		
		return "common/result";
	}
	
	@PostMapping("modifyschedule")
	@ResponseBody
	public String modifySchedule(@RequestBody Schedule schedule) {

		int res = scheduleService.updateSchedule(schedule);
		
		if(res>0) {
			return "success";		
		}else {
			return "fail";
		}
	}
	
	@GetMapping("deleteschedule")
	@ResponseBody
	public String deleteSchedule(String scheduleIdx) {
		
		int res = scheduleService.deleteSchedule(scheduleIdx);
		
		if(res>0) {
			return "success";		
		}else {
			return "fail";
		}
	}

}
