package com.kh.aboo.mypage.myalarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mypage")
public class MyAlarmController {
	
	@GetMapping("myalarm")
	public void myAlarm() {};

}
