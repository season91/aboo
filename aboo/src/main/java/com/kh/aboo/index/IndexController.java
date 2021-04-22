package com.kh.aboo.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("index")
	public String index() {
		return "index/index";
	}
	
	@GetMapping("about")
	public String about() {
		return "about/about";
	}

}
