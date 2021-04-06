package com.kh.aboo.mypage.myalarm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.aboo.admin.schedule.model.service.ScheduleService;
import com.kh.aboo.mypage.myalarm.model.service.MyAlarmService;
import com.kh.aboo.mypage.myalarm.model.vo.MyAlarm;
import com.kh.aboo.user.generation.model.vo.Generation;

@Controller
@RequestMapping("mypage")
public class MyAlarmController {
	
	private final MyAlarmService myAlarmService;
	private final ScheduleService scheduleService;
	
	public MyAlarmController(MyAlarmService myAlarmService,ScheduleService scheduleService) {
		this.myAlarmService = myAlarmService;
		this.scheduleService = scheduleService;
	}
	
	//알람리스트 불러오기
	@GetMapping("myalarm")
	public String myAlarm(HttpSession session,Model model) {
		
		Generation generation = (Generation) session.getAttribute("generation");
		
		
		List<MyAlarm> myAlarmList = new ArrayList<>();
		myAlarmList	= myAlarmService.selectIssue(generation.getGenerationIdx(), generation.getApartmentIdx());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (MyAlarm myAlarm : myAlarmList) {
			
			try {
				String issueDate = myAlarm.getIssueDate();
				Date d = sdf.parse(issueDate);
				issueDate = sdf.format(d);
				myAlarm.setIssueDate(issueDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		

		model.addAttribute("alarmList",myAlarmList);
		model.addAttribute("building",generation.getBuilding() + "동 " + generation.getNum() + "호");
		model.addAttribute("aptName",scheduleService.selectAptNameByIdx(generation.getApartmentIdx()));
		
		return "mypage/myalarm";
	};
	

	
	
	

}
