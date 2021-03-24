package com.kh.aboo.member.generation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GenerationController {

	@GetMapping("login")
	public String login() {
		return "generation/login";
	}

	@GetMapping("loginimpl")
	public void loginimpl() {
	}

	@GetMapping("logout")
	public void logout() {
		
		
	}

}
