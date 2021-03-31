package com.kh.aboo.myapt.parking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.admin.car.model.vo.CarApplication;
import com.kh.aboo.myapt.parking.model.service.ParkingService;
import com.kh.aboo.user.generation.model.vo.Generation;

@Controller
public class ParkingController {
	// 내 아파트 주차현황
	
	private final ParkingService parkingService;
	
	public ParkingController(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	@GetMapping("/myapt/parking")
	public void parking(@SessionAttribute(name = "generation", required = false) Generation generation, Model model) {
		String apartmentIdx = generation.getApartmentIdx();
		
		// 주차가능대수 조회
		Map<String, Object> parkingMap = parkingService.possibleParking(apartmentIdx);
		model.addAllAttributes(parkingMap);
	}
	
	// 차량신청
	@GetMapping("/myapt/parking/application")
	public void carApplication(@SessionAttribute(name = "generation", required = false) Generation generation, Model model){
		String generationIdx= generation.getGenerationIdx();
		// 신청한 내역이 있는지 확인한다.
		List<CarApplication> carApplicationCheck = parkingService.selectCarApplicationByGenerationIdx(generationIdx);
		System.out.println(carApplicationCheck);
		if(carApplicationCheck != null && carApplicationCheck.size() > 0) {
			model.addAttribute("carApplicationList",carApplicationCheck);
		}
		
	}

	@GetMapping("/myapt/parking/applicationimpl")
	public String carApplicationImpl(@RequestParam String aplctCarNumber, @SessionAttribute(name = "generation", required = false) Generation generation, Model model){
		String generationIdx= generation.getGenerationIdx();
		CarApplication carApplication = new CarApplication();
		carApplication.setApartmentIdx("100000");
		carApplication.setGenerationIdx(generationIdx);
		carApplication.setAplctCarNumber(aplctCarNumber);
		
		// 신청 온 차량번호 검증한다..?

		// 차량번호와 세대관리번호로 신청 테이블에 넣어준다.
		int res = parkingService.insertCarApplication(carApplication);
		
		if(res > 0) {
			model.addAttribute("alertMsg", "차량 등록 신청이 되었습니다.");
		} else {
			model.addAttribute("alertMsg", "차량 등록 신청이 실패하였습니다. 관리인에게 문의해주세요.");
		}

		model.addAttribute("url", "/myapt/parking/application");
		return "common/result";
	}

}
