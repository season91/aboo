package com.kh.aboo.admin.car.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.admin.car.model.service.CarService;
import com.kh.aboo.admin.car.model.vo.Car;
import com.kh.aboo.admin.car.model.vo.CarApplication;
import com.kh.aboo.common.code.Configcode;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
public class CarController {
	
	private final CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}

	// 1.일반 페이징처리하기
	@GetMapping("admin/car")
	public String car(@RequestParam(defaultValue = "1") int page, @SessionAttribute(name = "admin", required = false) Admin admin,  @RequestParam(defaultValue = "apartmentIdx") String standard, @RequestParam(defaultValue = "apartmentIdx") String keyword, Model model) {
		String apartmentIdx = admin.getApartmentIdx();
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("apartmentIdx", apartmentIdx);
		String link = "";
		// 페이징 처리타입 3개
		switch (standard) {
		case "apartmentIdx":
			// 기본 페이징
			searchMap.put("searchType", "apartmentIdx");
			break;
		case "carNumber":
			// 차량번호 검색
			searchMap.put("searchType", "carNumber");
			searchMap.put("carNumber", keyword);
			link = "/carnumber";
			model.addAttribute("keyword",keyword);
			break;
		case "generationInfo":
			// 세대정보로 검색
			Generation generation = new Generation();
			String[] generationInfo = keyword.split("-");
			generation.setApartmentIdx(apartmentIdx);
			generation.setBuilding(generationInfo[0]);
			generation.setNum(generationInfo[1]);
			System.out.println(generation);
			
			// 조회된 세대관리번호를 map에 담아준다.
			String generationIdx = carService.selectGenerationByBuildingAndNum(generation).getGenerationIdx();
			searchMap.put("searchType", "generationIdx");
			searchMap.put("generationIdx", generationIdx);
			link = "/generation";
			model.addAttribute("keyword",keyword);
			break;
		}
		
		model.addAllAttributes(carService.selectCarList(page, searchMap));
		return "admin/car"+link;
	}
	
	// 2. 입주자 차량등록 신청건 페이징처리하기
	@GetMapping("admin/car/application")
	public String carApplication(@RequestParam(defaultValue = "1") int page,@SessionAttribute(name = "admin", required = false) Admin admin, Model model) {
		String apartmentIdx = admin.getApartmentIdx();
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("apartmentIdx", apartmentIdx);

		model.addAllAttributes(carService.selectCarApplicationList(page, searchMap));
		return "admin/car/application";
	}
	
	@GetMapping("admin/caradd")
	public String vehicleAdd(@RequestParam String building, @SessionAttribute(name = "admin", required = false) Admin admin, @RequestParam String num, @RequestParam String carNumber, Model model){
		// 전달받은 아파트번호,동,호수 정보로 Generation 가져온다.
		Generation generationInfo = new Generation();
		generationInfo.setApartmentIdx(admin.getApartmentIdx());
		generationInfo.setBuilding(building);
		generationInfo.setNum(num);
		Generation generation = carService.selectGenerationByBuildingAndNum(generationInfo);
		System.out.println(generation);
		
		// 전달받은 차량번호를 세대정보와 함께 넣어준다.
		Car car = new Car();
		car.setGenerationIdx(generation.getGenerationIdx());
		car.setApartmentIdx(generation.getApartmentIdx());
		car.setCarNumber(carNumber);
		car.setCarQR(Configcode.QRCODE_PATH.desc);
		
		String res = carService.insertAndQRWrite(generation.getGenerationIdx(), car);
		model.addAttribute("alertMsg", res);
		model.addAttribute("url", "car");

		return "common/result";
	}
	
	// 비동기, 차량 삭제
	@GetMapping("admin/cardelete")
	@ResponseBody
	public void carDelete(@RequestParam List<String> caridx) {
		for (int i = 0; i < caridx.size() ; i++) {
			int res = carService.deleteCar(caridx.get(i));
			// 삭제 되었다면 완료, 아니면 실패
			if(res == 0) {
				throw new ToAlertException(ErrorCode.DM01);
			}
		}
	}
	
	// 비동기, 차량 승인
	@GetMapping("admin/carapplcation/approval")
	@ResponseBody
	public void carApplicationImpl(@RequestParam List<String> applicationidx) {
		// 1. 승인하면 처리상태 바꿔준다
		for (int i = 0; i < applicationidx.size() ; i++) {
			int res = carService.updateCarApplicationApproval(applicationidx.get(i));
			// 삭제 되었다면 완료, 아니면 실패
			if(res == 0) {
				throw new ToAlertException(ErrorCode.DM01);
			}
			
			// 2. QR코드 생성하고 차량 DB에 넣어준다.
			// applicationidx 로 신청정보 가져와 생성해준다
			CarApplication carApplication = carService.selectCarApplication(applicationidx.get(i));
			
			Car car = new Car();
			car.setGenerationIdx(carApplication.getGenerationIdx());
			car.setApartmentIdx(carApplication.getApartmentIdx());
			car.setCarNumber(carApplication.getAplctCarNumber());
			car.setCarQR(Configcode.QRCODE_PATH.desc);
			
			carService.insertAndQRWrite(carApplication.getGenerationIdx(), car);
		}

	}
	
	// 비동기, 차량 삭제 (신청 반려)
	@GetMapping("admin/carapplcation/reject")
	@ResponseBody
	public void carApplicationDelete(@RequestParam List<String> applicationidx) {
		// 1. 반려하면 처리상태 바꿔준다
		for (int i = 0; i < applicationidx.size() ; i++) {
			int res = carService.updateCarApplicationReject(applicationidx.get(i));
			// 반려 되었다면 완료, 아니면 실패
			if(res == 0) {
				throw new ToAlertException(ErrorCode.DM01);
			}
		}

	}
	
	
	// 차량 수정
	@GetMapping("admin/carmodify")
	public String carModify(Car car, @RequestParam String generationInfo,@SessionAttribute(name = "admin", required = false) Admin admin, Model model) {
		// 아파트번호넣어주기
		String apartmentIdx = admin.getApartmentIdx();
		car.setApartmentIdx(apartmentIdx);
		// 1. 세대정보로 세대관리번호 가져온다
		Generation generationTemp = new Generation();
		String[] generationArr = generationInfo.split("-");
		generationTemp.setApartmentIdx(apartmentIdx);
		generationTemp.setBuilding(generationArr[0]);
		generationTemp.setNum(generationArr[1]);
		
		Generation generation = carService.selectGenerationByBuildingAndNum(generationTemp);
		car.setGenerationIdx(generation.getGenerationIdx());
		
		// 2. 완성된 car로 수정한다
		int res = carService.updateCar(car);
		if(res > 0) {
			model.addAttribute("alertMsg", "수정이 완료되었습니다.");
		}
		
		model.addAttribute("url", "/admin/car");
		return "common/result";
	}
	
	// QR로인해 해당 url 접근시 관리자가 차량 입차 체크한다.
	@GetMapping("admin/carread")
	public String carRead(@RequestParam String generationidx, @RequestParam String caridx, Model model) {
		// 전달받은 세대관리번호의 차량번호가 일치하는지 확인하고 진행한다.
		List<String> carIdxCheckList = carService.selectCarNumberByGenerationIdx(generationidx);
		String carIdx = "";
		for (int i = 0; i < carIdxCheckList.size(); i++) {
			String carIdxCheck = carIdxCheckList.get(i);
			// 일치하다면 idx 가져온다.
			if(carIdxCheckList.get(i).equals(caridx)) {
				carIdx = carIdxCheck;
			} 
		}
		// 리스트로 비교를해서 포문돌려서 포문안에 이 if문을 넣는다.
		if(carIdx.equals(caridx)) {
			String msg = carService.updateIsInCar(caridx); 
			model.addAttribute("alertMsg", msg);
		} else { 
			model.addAttribute("alertMsg", "실패하였습니다. 등록정보를 확인하세요.");
		}
		
		model.addAttribute("url", "/myapt/parking");
		
		return "common/result";
	}


}
