package com.kh.aboo.myapt.parking.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.admin.car.model.vo.Car;
import com.kh.aboo.admin.car.model.vo.CarApplication;
import com.kh.aboo.myapt.parking.model.service.ParkingService;
import com.kh.aboo.myapt.parking.validator.CarApplicationValidator;
import com.kh.aboo.user.generation.model.vo.Generation;

@Controller
public class ParkingController {
	// 내 아파트 주차현황
	
	private final ParkingService parkingService;
	private final CarApplicationValidator carApplicationValidator;
	
	public ParkingController(ParkingService parkingService, CarApplicationValidator carApplicationValidator) {
		this.parkingService = parkingService;
		this.carApplicationValidator = carApplicationValidator;
	}
	
	@GetMapping("/myapt/chat")
	public void chatGenerationTest() {
		
	}

	@InitBinder("carApplication")
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(carApplicationValidator);
	}
	
	@GetMapping("/myapt/parking")
	public void parking(@SessionAttribute(name = "apartmentIdx", required = false) String apartmentIdx, 
			@SessionAttribute(name = "generation", required = false) Generation generation, 
			HttpSession session, Model model) {
		if(generation != null) {
			apartmentIdx = generation.getApartmentIdx();

		}
		// 주차가능대수 조회
		Map<String, Object> parkingMap = parkingService.possibleParking(apartmentIdx);
		session.removeAttribute("apartmentIdx");
		model.addAllAttributes(parkingMap);
	}
	
	// 차량신청
	@GetMapping("/myapt/parking/application")
	public void carApplication(@SessionAttribute(name = "generation", required = false) Generation generation,  Model model){
		// 신청한 내역이 있는지 확인한다.
		List<CarApplication> carApplicationCheck = parkingService.selectCarApplicationByGenerationIdx(generation.getGenerationIdx());
		if(carApplicationCheck != null && carApplicationCheck.size() > 0) {
			model.addAttribute("carApplicationList",carApplicationCheck);
		}
		
	}
	
	@GetMapping("/myapt/parking/applicationimpl")
	@ResponseBody //비동기통신
	public String carApplicationImpl(@Valid @ModelAttribute CarApplication carApplication, Errors errors, @SessionAttribute(name = "generation", required = false) Generation generation, Model model){

		carApplication.setApartmentIdx(generation.getApartmentIdx());
		carApplication.setGenerationIdx(generation.getGenerationIdx());

		// 1.신청 온 차량번호 양식을 검증한다
		if(errors.hasErrors()) {
			return "/myapt/parking/application";
		}

		// 2. 신청가능 상태를 확인한다. 
		// 이미 등록된차량인지 세대가 2대등록이상 신청인지 확인 후 없으면 신청가능
		// 신청한 이력이 없다면 신청가능
		// 차량번호와 세대관리번호로 신청 테이블에 넣어준다.
		Car car = parkingService.selectCarByApplicationInfo(carApplication);
		CarApplication carApplicationCheck = parkingService.selectCarApplication(carApplication);
		
		if(car == null && carApplicationCheck == null) {
			// 등록된 차량도, 신청한 이력도 없다면 신청가능
			// 차량번호와 세대관리번호로 신청 테이블에 넣어준다.
			int res = parkingService.insertCarApplication(carApplication);
			if(res > 0) {
				return "success";
			} else {
				return "fail";
			}

		} else {
			return "application";
		}

	}

}
