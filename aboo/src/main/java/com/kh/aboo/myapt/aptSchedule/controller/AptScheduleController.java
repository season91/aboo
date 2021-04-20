package com.kh.aboo.myapt.aptSchedule.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.aboo.admin.schedule.model.service.ScheduleService;
import com.kh.aboo.admin.schedule.model.vo.Schedule;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
@RequestMapping("myapt")
public class AptScheduleController {
	
	private final ScheduleService scheduleService;
	
	public AptScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	@GetMapping("schedule")
	public String aptSchedule(@RequestParam(defaultValue = "1") int page, Model model,HttpSession session){
		
		Generation generation = (Generation) session.getAttribute("generation");
		Admin admin = (Admin) session.getAttribute("admin");

		if(admin != null) {
			String apartmentIdx = admin.getApartmentIdx();
			List<Schedule> scheduleList = new ArrayList<>();
			scheduleList = scheduleService.selectScheduleListForCalendar(apartmentIdx);

			model.addAttribute("schedule",scheduleList);
			model.addAttribute("aptName",scheduleService.selectAptNameByIdx(apartmentIdx));
			System.out.println(scheduleService.selectAptNameByIdx(apartmentIdx));
			
			
		}else {
			String apartmentIdx = generation.getApartmentIdx();
			List<Schedule> scheduleList = new ArrayList<>();
			scheduleList = scheduleService.selectScheduleListForCalendar(apartmentIdx);

			model.addAttribute("schedule",scheduleList);
			model.addAttribute("aptName",scheduleService.selectAptNameByIdx(apartmentIdx));
			System.out.println(scheduleService.selectAptNameByIdx(apartmentIdx));
		}
		
		return "myapt/schedule";
		
		
	}
	
	

}
