package common.exception.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import common.exception.CustomException;

//예외들 맡아서 처리해줄 클래스.
@Controller
// Advice 지정한 패키지내의 모든 컨트롤러들의 공통 관심사를 처리하는 클래스.
@ControllerAdvice(basePackages = {"com.kh.toy","common"})
public class ExceptionController {
	
	//slf4j 사용
	Logger logger = LoggerFactory.getLogger(ExceptionController.class);

	// 우리가 구현해놓은 CustomException을 받아서 처리할 것 (즉, throw ToAlertException 인 경우 다 이렇게 처리될 것)
	@ExceptionHandler(CustomException.class)
	public String businessExceptionHandler(CustomException e, Model model) {
		model.addAttribute("alertMsg", e.error.errMsg());
		model.addAttribute("url", e.error.url());
		return "common/result";
	}
	
	// sql에러를 마이바티스 스프링이 알아서 해줄것 임
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) // 비동기통신이 값을 받아야 에러인식하니깐 500번대 에러를 보내겠다라는 의미.
	@ExceptionHandler(DataAccessException.class)
	public String dataAccexxExceptionHandler(DataAccessException e, Model model) {
		//logger.error(e.getMessage());
		e.printStackTrace();
		model.addAttribute("alertMsg", "데이터 베이스 접근 중 에러가 발생하였습니다.");
		model.addAttribute("url", "/member/login");
		System.out.println(model);
		System.out.println("Exception");
		return "common/result";
	}
}
