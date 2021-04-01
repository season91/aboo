package com.kh.aboo.mypage.myalarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.aboo.mypage.myalarm.model.service.MyAlarmService;

@Controller
@RequestMapping("mypage")
public class MyAlarmController {
	
	private final MyAlarmService myAlarmService;
	
	public MyAlarmController(MyAlarmService myAlarmService) {
		this.myAlarmService = myAlarmService;
	}
	
	@GetMapping("myalarm")
	public void myAlarm() {};

}
