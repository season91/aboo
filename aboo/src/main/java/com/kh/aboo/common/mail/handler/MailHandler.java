package com.kh.aboo.common.mail.handler;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//요청을 받을수 있는 컨트롤러가 되어야 한다.
@Controller
public class MailHandler {

	@PostMapping("mail")
	public String writeMail(@RequestParam Map<String, Object> data, Model model) {
		
		//System.out.println(data);
		//System.out.println(header); 
		//System.out.println(data.get("mail-template"));
		
		// jsp에 뿌려줄 데이터를 모델이 담아준다.
		model.addAllAttributes(data);
		// view
		return "mail-template/" + data.get("mail-template");
		
		
	}
}
