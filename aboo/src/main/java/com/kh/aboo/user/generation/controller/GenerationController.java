package com.kh.aboo.user.generation.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.aboo.user.generation.model.service.GenerationService;
import com.kh.aboo.user.generation.model.vo.Generation;

@Controller
public class GenerationController {

	@Autowired
	private PasswordEncoder encoder;

	private final GenerationService generationService;

	public GenerationController(GenerationService generationService) {
		this.generationService = generationService;
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
		return "success";

	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("generation");
		return "index/index";
	}

	@GetMapping("mypage/findid")
	public String findId() {
		return "mypage/findId";
	}

	@PostMapping("mypage/findidimpl")
	@ResponseBody
	public String findidImpl(@RequestBody Generation generation, HttpSession session) {

		Generation findGeneration = generationService.selectfindid(generation);
		System.out.println(findGeneration);

		if (findGeneration == null) {
			return "fail";
		} else {

			String authPath = UUID.randomUUID().toString();
			session.setAttribute("authPath", authPath);
			session.setAttribute("findGeneration", findGeneration);

			generationService.authenticateEmail(generation, authPath);
			return "success";

		}

	}

	@GetMapping("mypage/findidresult")
	public String findidResult(HttpSession session) {
		return "mypage/findIdResult";
	}

	@GetMapping("mypage/findidcertified")
	public String findidCertified(@RequestParam String certifiedNum, HttpSession session, Model model) {

		String authPath = (String) session.getAttribute("authPath");
		Generation findGeneration = (Generation) session.getAttribute("findGeneration");

		if (!certifiedNum.equals(authPath)) {
			model.addAttribute("alertMsg", "인증번호가 일치 하지 않습니다");
		}

		model.addAttribute("url", "/mypage/findidresult");
		model.addAttribute("findGeneration", findGeneration);

		return "common/result";

	}

	
	@GetMapping("mypage/findpassword")
	public String findPassword() {
		return "mypage/findPassword";
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
