package com.kh.aboo.user.generation.controller;

import java.util.Map;
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
import org.springframework.web.bind.annotation.ModelAttribute;
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

@Controller
public class GenerationController {

	@Autowired
	private PasswordEncoder encoder;

	Ramdom random = new Ramdom();

	private final GenerationService generationService;
	private final GenerationValidator generationValidator;

	public GenerationController(GenerationService generationService, GenerationValidator generationValidator) {
		this.generationService = generationService;
		this.generationValidator = generationValidator;
	}

	@InitBinder("generation")
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

		// generationInfo : ???????????? ?????? ????????? ?????? ??????
		// generation : ?????? generation ????????? ?????? ?????? ??????

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

			String authPathId = UUID.randomUUID().toString().replace("-", "");
			authPathId = authPathId.substring(0, 10);

			session.setAttribute("authPathId", authPathId);
			session.setAttribute("findGeneration", findGeneration);
			
			generationService.findIdEmail(findGeneration, authPathId);
			return "success";
		}

	}

	@GetMapping("authid")
	@ResponseBody
	public String authId(@RequestParam String certifiedNum, HttpSession session, Model model) {

		String authPathId = (String) session.getAttribute("authPathId");

		System.out.println("????????? ???????????? " + authPathId);

		if (!certifiedNum.equals(authPathId)) {
			return "fail";

		}

		return "success";

	}

	@GetMapping("findidresult")
	public String findIdResult(HttpSession session, Model model) {
		Generation findGeneration = (Generation) session.getAttribute("findGeneration");

		model.addAttribute("findGeneration", findGeneration);
		return "generation/findIdResult";
	}

	@GetMapping("findpassword")
	public String findPassword() {
		return "generation/findPassword";
	}

	// ?????? ?????? ???????????? ????????? DB??????
	@PostMapping("findpasswordimpl")
	@ResponseBody
	public String findPasswordImpl(@RequestBody Generation generationInfo, HttpSession session, Model model) {

		Generation findGeneration = generationService.selectFindPassword(generationInfo);
		System.out.println(findGeneration);
		if (findGeneration == null) {
			return "fail";
		} else {

			String password = random.randomPw();

			System.out.println("?????? ?????? " + password);
			generationService.findPasswordEmail(findGeneration, password); // ?????? ?????????

			return "success";

		}

	}

	@GetMapping("/mypage/generationwon")
	public String generationWon(@RequestParam(defaultValue = "1") int page,
			@SessionAttribute(name = "generation", required = false) Generation generationInfo, Model model) {

		model.addAllAttributes(generationService.selectGenerationWonList(page, generationInfo.getGenerationIdx()));
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
		// ???????????? ??????????????? ????????????
		System.out.println(generationWon);

		int cnt = generationService.selectGenerationWonCnt(generation);
		System.out.println(cnt);

		if (cnt >= 5) {
			return "fail";

		} else {

			generationService.insertGenerationWonAdd(generationWon);
			return "success";

		}

	}

	@GetMapping("/mypage/modifyinfo")
	public String modifyInfo(@SessionAttribute(name = "generation") Generation generation, Model model) {

		Generation selectGeneration = generationService.selectGeneration(generation);
		System.out.println(selectGeneration);

		model.addAttribute("selectGeneration", selectGeneration);
		return "mypage/modifyInfo";
	}

	@PostMapping("/mypage/modifyupdate")
	public String modifyInfo(@Valid Generation generationValid, Errors errors,
			@SessionAttribute(name = "generation") Generation generation, Model model) {

		Generation selectGeneration = generationService.selectGeneration(generation);
		if (errors.hasErrors()) {
			model.addAttribute("selectGeneration", selectGeneration);
			return "mypage/modifyInfo";
		}

		generationValid.setGenerationIdx(selectGeneration.getGenerationIdx());
		generationService.updateGenerationModify(generationValid);

		model.addAttribute("alertMsg", "?????????????????????.");
		model.addAttribute("url", "/mypage/modifyinfo");
		return "common/result";
	}

	// ????????? ??????
	@PostMapping("/mypage/modifyemailimpl")
	@ResponseBody
	public String modifyEmailImpl(@RequestBody Generation generationInfo, HttpSession session) {

		if (generationService.selectGenerationEmailCnt(generationInfo) > 0) {
			return "fail";
		}

		String authPathEmail = UUID.randomUUID().toString().replace("-", "");
		authPathEmail = authPathEmail.substring(0, 10);

		System.out.println("????????? ?????? " + authPathEmail);

		session.setAttribute("authPathEmail", authPathEmail);
		
		generationService.authEmail(generationInfo, authPathEmail);

		return "success";

	}

	// ????????? ??????
	@PostMapping("/mypage/authemail")
	@ResponseBody
	public String authEmail(@RequestBody Map<String, Object> info, HttpSession session) {

		String certifiedNum = (String) info.get("certifiedNum");
		String authPathEmail = (String) session.getAttribute("authPathEmail");

		if (!certifiedNum.equals(authPathEmail)) {
			return "fail";
		}

		Generation generation = (Generation) session.getAttribute("generation");
		String email = (String) info.get("email");
		generation.setEmail(email);
		generationService.updateGenerationEmail(generation);
		return "success";
	}

	// ????????? ??????
	@PostMapping("/mypage/modifytellimpl")
	@ResponseBody
	public String modifyTellImpl(@RequestBody Generation generationInfo, HttpSession session) {

		
		if (generationService.selectGenerationTellCnt(generationInfo) > 0) {
			return "fail";
		}
		
		generationService.authTell(generationInfo.getTell(), session);

		return "success";
	}

	// ????????? ??????
	@PostMapping("/mypage/authtell")
	@ResponseBody
	public String authTell(@RequestBody Map<String, Object> info, HttpSession session) {

		String certifiedPNum = (String) info.get("certifiedPNum");
		String authPathTell = (String) session.getAttribute("authPathTell");
		System.out.println(certifiedPNum);

		if (!certifiedPNum.equals(authPathTell)) {
			return "fail";
		}

		Generation generation = (Generation) session.getAttribute("generation");
		String tell = (String) info.get("tell");
		generation.setTell(tell);
		generationService.updateGenerationTell(generation);
		return "success";
	}

	// ?????? ?????? ????????? ?????? ?????????
	// ?????? ?????? ???
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
