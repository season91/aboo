package com.kh.aboo.admin.manageschedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class ManageScheduleController {
	
	@GetMapping("manageschedule")
	public void manageSchedule() {};

}
