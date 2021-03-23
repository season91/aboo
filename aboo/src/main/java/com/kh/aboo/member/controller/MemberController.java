package com.kh.aboo.member.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.member.model.service.MemberService;
import com.kh.aboo.member.model.vo.Member;
import com.kh.aboo.member.validator.MemberValidator;

import common.code.Configcode;
import common.code.ErrorCode;
import common.exception.ToAlertException;

//Controller 어노테이션이 하는 일 : 해당 클래스가 Controller임을 스프링에게 알려준다.
//해당 클래스를 bean으로 등록. 컨트롤러와 관련된 어노테이션을 해당 클래스내에서 사용할 수 있게 해준다.

//종류
//@RequestMapping : 메서드와 매핑시킬 url 및 http method를 지정

//@GetMapping : 메서드와 매핑시킬 url 지정, get 방식 요청만 매핑된다.

//@PostMapping : 메서드와 매핑시킬 url 지정, post 방식 요청만 매핑된다.

//@RequestParam : 파라미터를 전달받을 수 있다. 파라미터명과 변수명이 일치할 경우에는 생략 가능하다.
// RequestParam 어노테이션을 사용해 파라미터 바인드해주는 친구는 FormHttpMessageConverter가 동작.
// FormHttpMessageConverter : from-url-encoded 컨텐츠 타입의 http 메세지를 파싱해서 객체에 바인드 해주는 역할을 수행하는 인터페이스

//@RequestBody : MappingJacksonHttpMessageConverter가 동작 application/json 컨텐츠 타입의 메시지를 파싱해서 객체에 바인드해주는 역할 수행

//@ModelAttribute : 요청 파라미터를 vo에 바인드, model 객체의 attribute에도 VO를 담는다.

//@ResponseBody : 어노테이션이 작성된 메서드가 반환하는 값을 응답 body에 넣어 응답한다.

//@RequestHeader : 요청헤더를 컨트롤러 메서드에 바인드
// HttpEntity : Http 통신과 관련된 데이터들을 저장하고 있는 객체
// RequestEntity, ResponseEntity : HttpEntity 상속받는다. 

//@SessionAttribute : 원하는 Session의 attribute를 컨트롤러 메서드 파라미터에 바인드. 거의 안쓰긴 함. session util을 내부에서만들어서 사용하기 때문임

//@PathVariable : url 템플릿에 담긴 파라미터 값을 컨트롤러 메서드 파라미터에 바인드 해준다. 게시판 여러개 만들때 주로 사용한다. 
// 컨트롤러 1개에 보드-노티스 보드-먼스아티클 보드-아티클 등등 뒤에 PathVariable 넣고 사용한다. jsp하나 테이블하나 컨트롤 하나. 웨어절에 타입 분기를 나눠준다.

//@CookieValue :  Cookie 컨트롤러 메서드 매개변수에 바인드

//** 주의사항! **
//Controller의 메서드에 Servlet 객체(HttpServletRequest, HttpServletResponse, Session)를 선언해두면 
//Spring이 메서드 호출할때 Servlet 객체를 담아 보내준다. 
//하지만! 대체할 수 있는 객체를 문서에서 찾는 습관을 들이자.

@RequestMapping("member")
@Controller
public class MemberController {
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private final MemberService memberService;
	private final MemberValidator memberValidator;
	
	// 생성자로 의존성 주입.
	public MemberController(MemberService memberService, MemberValidator memberValidator) {
		this.memberService = memberService;
		this.memberValidator = memberValidator;
	}
	
	//@InitBinder : 특정 컨트롤러에서 validator를 사용하기 위해 지정
	// value : value 속성에 지정한 요청 파라미터명(이건모르겠다) 또는 modelAttribute명에 대해서만 validator가 동작한다.
	// http의 request 파라미터를 의미
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		//webDataBinder : 컨트롤러 메서드의 파라미터에 데이터 바인드 해주는 역할을 하는 클래스
		webDataBinder.addValidators(memberValidator);
	}
	
	// view 지정방법
	// 1. ModelAndView 객체를 만들어서 setViewName 메서드에 view 경로를 지정 (옛날방법)
	// 2. view 경로 반환
	// 3. 아무것도 반환하지 않을 경우 url을 view 경로로 지정.
	
	@GetMapping("join")
	public void join() {}

	@GetMapping("idcheck")
	@ResponseBody // 메서드가 반환하는 값을 응답 body에 담아준다. JSP를 찾지 않는다. 경로재지정이 아닐때 사용한다.
	public String idCheck(String userId) {
		if(memberService.selectMemberById(userId) != null) {
			return "fail";
		}
		return "success";
	}
	
	// 사용자정보 session에 담아두구 메일발송 -> 우리가 지정한 경로로 다시요청하면 그때 session에 담긴 사용자정보 가져와 DB에 넣고 session 삭제.
	// HTTP session을 가져와야겠지? 
	// 스프링의 로우단에 직접 접근하는건 좋은 방법은 아님 request, response 만지는 것! 하지만 session은 예외이다.ㅎ
	@PostMapping("mailauth") // vo는 @RequestParam 없어도 된다. 스프링이 알아서 바인드 한다.
	public String authenicateEmail(@Valid Member persistInfo, Errors errors, HttpSession session, Model model) {
		// @Valid 어노테이션 붙이면 Validator를 구현한 MemberValidator가 작동한다.
		// 매개변수 순서가 errors는 꼭 @valid 지정된 파라미터 바로 뒤에 위치해야한다!!

		if(errors.hasErrors()) {
			//부분적으로 하고싶을땐 hasfieldErrors 사용
			return "member/join";
			
		}
		
		// 유니크 아이디 만들어서 세션에 유저정보 넣어주자
		String authPath = UUID.randomUUID().toString();
		session.setAttribute("authPath", authPath);
		session.setAttribute("persistInfo", persistInfo);
		
		memberService.authenicateEmail(persistInfo, authPath);
		model.addAttribute("alertMsg","이메일이 발송되었습니다.");
		model.addAttribute("url", "/index");
		return "common/result";
		
	}
	
	
	@GetMapping("joinimpl/{authPath}")
	public String joinImpl(@PathVariable("authPath") String urlPath, HttpSession session, @SessionAttribute("authPath") String sessionPath
			, @SessionAttribute("persistInfo") Member member, Model model) {
		// 두 경로가 같은경우에만 회원가입승인
		
		if(!urlPath.equals(sessionPath)) {
			throw new ToAlertException(ErrorCode.AUTH02);
		}
		
		memberService.insertMember(member);
		session.removeAttribute("persistInfo");
		
		model.addAttribute("alertMsg","회원가입이 완료되었습니다.");
		model.addAttribute("url", Configcode.DOMAIN+"/index");

		return "common/result";
	}
	
	@GetMapping("login")
	public void login() {};
	
	@PostMapping("loginimpl")
	@ResponseBody
	public String loginImpl(@RequestBody Member member, HttpSession session) {
		// jsp에서 member를 json으로 보내주기 때문에 @RequestParam을 쓰면 값을 못가져온다. (model attribute만 읽는 애다)
		// @RequestBody : 컨텐츠타입이 application/json인 경우 사용. restAPI통신은 거의 이타입임. 붙여야 json을 자바 객체로 바인드할 수 있다.
		Member userInfo = memberService.authenticateUser(member);

		if(userInfo == null) {
			return "fail";
		}
		session.setAttribute("userInfo", userInfo);
		return "success";
	}
	
	@GetMapping("mypage")
	public void mypage() {};
}
