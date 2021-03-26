package com.kh.aboo.user.generation.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.util.ramdom.Ramdom;
import com.kh.aboo.user.generation.model.service.GenerationService;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.generation.validator.GenerationValidator;
import com.kh.aboo.user.generationWon.model.vo.GenerationWon;
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
public class GenerationController {

	@Autowired
	private PasswordEncoder encoder;

	Ramdom random = new Ramdom();

	private final GenerationService generationService;
	private final GenerationValidator generationValidator;
	
	public GenerationController(GenerationService generationService,GenerationValidator generationValidator) {
		this.generationService = generationService;
		this.generationValidator = generationValidator;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(generationValidator);

	}
	
	@GetMapping("login")
	public String login() {
		return "generation/login";
	}

	@PostMapping("loginimpl")
	@ResponseBody
	public String loginimpl(@RequestBody Generation generationInfo, HttpSession session) {

		// generationInfo : 받아와서 맵핑 해주는 객체 이름
		// generation : 진짜 generation 정보가 담긴 객체 이름

		Generation generation = generationService.selectGenerationForAuth(generationInfo);
		if (generation == null) {
			return "fail";
		}
		session.setAttribute("generation", generation);
		return "sussece";

	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("generation");
		return "index/index";
	}

	@GetMapping("findid")
	public String findId() {
		return "generation/findId";
	}

	@PostMapping("findidimpl")
	@ResponseBody
	public String findidImpl(@RequestBody Generation generationInfo, HttpSession session) {

		Generation findGeneration = generationService.selectFindId(generationInfo);
		System.out.println(findGeneration);

		if (findGeneration == null) {
			return "fail";
		} else {

			String authPath = UUID.randomUUID().toString().replace("-", "");
			authPath = authPath.substring(0, 10);

			session.setAttribute("authPath", authPath);
			session.setAttribute("findGeneration", findGeneration);
			generationService.authenticationIdMail(findGeneration, authPath);
			return "success";
		}

	}

	@GetMapping("authenticationid")
	public String authenticationId(@RequestParam String certifiedNum, HttpSession session, Model model) {

		String authPath = (String) session.getAttribute("authPath");
		Generation findGeneration = (Generation) session.getAttribute("findGeneration");

		if (!certifiedNum.equals(authPath)) {
			throw new ToAlertException(ErrorCode.AH01);
		}

		model.addAttribute("url", "/findidresult");
		model.addAttribute("findGeneration", findGeneration);

		return "common/result";

	}

	@GetMapping("findidresult")
	public String findidResult() {
		return "generation/findIdResult";
	}

	@GetMapping("findpassword")
	public String findPassword() {
		return "generation/findPassword";
	}

	// 선영 임시 비밀번호 발급과 DB변경
	@PostMapping("findpasswordimpl")
	@ResponseBody
	public String findPasswordImpl(@RequestBody Generation generationInfo, HttpSession session, Model model) {

		Generation findGeneration = generationService.selectFindPassword(generationInfo);
		System.out.println(findGeneration);
		if (findGeneration == null) {
			return "fail";
		} else {

			String password = random.randomPw();

			System.out.println("임시 번호 : " + password);
			generationService.authenticationPasswordMail(findGeneration, password); // 메일 보내기

			return "success";

		}

	}

	@GetMapping("/mypage/generationwon")
	public String addGenerationWon(@RequestParam(defaultValue = "1") int page,
			@SessionAttribute(name = "generation", required = false) Generation generation, Model model) {

		model.addAllAttributes(generationService.selectGenerationWonList(page, generation.getGenerationIdx()));
		return "mypage/generationWon";
	}

	@PostMapping("/mypage/generationwonmodify")
	@ResponseBody
	public String generationWonModify(@RequestBody GenerationWon generationWon) {

		int res = generationService.updateGenerationWonModify(generationWon);
		if (res > 0) {
			return "success";
		}
		return "fail";

	}

	
	
	@PostMapping("/mypage/generationwondelete")
	@ResponseBody
	public String generationWonDelete(@RequestBody GenerationWon generationWon) {

		int res = generationService.updateGenerationWonDelete(generationWon);
		System.out.println(res);
		if (res > 0) {
			return "success";
		}
		return "fail";

	}
	
	
	
	@PostMapping("/mypage/generationwonadd")
	@ResponseBody
	public String generationWonAdd(@RequestBody GenerationWon generationWon,
			@SessionAttribute(name = "generation") Generation generation) {

		
		generationWon.setGenerationIdx(generation.getGenerationIdx());
		//추가하는 세대정보를 넣어준다
		System.out.println(generationWon);
		int res = generationService.insertGenerationWonAdd(generationWon);
		
		System.out.println(res);
		if (res > 0) {
			return "success";
		}
		return "fail";

	}
	
	
	@GetMapping("/mypage/modifyinfo")
	public String modifyInfo(@SessionAttribute(name="generation")Generation generation, Model model) {
		
		
		Generation selectGeneration = generationService.selectGeneration(generation);
		System.out.println(selectGeneration);
		
		model.addAttribute("selectGeneration", selectGeneration);
		return "mypage/modifyInfo";
	}
	
	
	@PostMapping("/mypage/modifyupdate")
	public String modifyInfo(@Valid Generation generationInfo , Errors errors,
			@SessionAttribute(name="generation")Generation generation, Model model) {
		
		System.out.println(generation);
		Generation selectGeneration = generationService.selectGeneration(generation);
		System.out.println(selectGeneration);
		if (errors.hasErrors()) {
			model.addAttribute("selectGeneration", selectGeneration);
			return "mypage/modifyInfo";
		}
		
		System.out.println(generationInfo);
		return "";
	}
	
	
	
	
	// 세대 추가 메서드 이거 쓰세용
	// 세대 더미 용
	@GetMapping("generation/add")
	public void generationAdd(String id, String password, String apartmentIdx, String building, String num) {
		Generation generation = new Generation();
		generation.setId(id);
		generation.setPassword(encoder.encode(password));
		generation.setApartmentIdx(apartmentIdx);
		generation.setBuilding(building);
		generation.setNum(num);

		generationService.insertGeneration(generation);
	}

}
