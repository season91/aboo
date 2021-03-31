package com.kh.aboo.myapt.aptSchedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("myapt")
public class AptScheduleController {
	
	@GetMapping("schedule")
	public void aptSchedule(){}

}
