package com.kh.aboo.admin.car.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.admin.car.model.service.CarService;
import com.kh.aboo.admin.car.model.vo.Car;
import com.kh.aboo.admin.car.model.vo.CarApplication;
import com.kh.aboo.common.code.AlarmCode;
import com.kh.aboo.common.code.ConfigCode;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.mypage.myalarm.model.service.MyAlarmService;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
public class CarController {
	
	private final CarService carService;
	private final MyAlarmService myAlarmService;
	
	public CarController(CarService carService,  MyAlarmService myAlarmService) {
		this.carService = carService;
		this.myAlarmService = myAlarmService;
	}
	
	@GetMapping("/admin/chat")
	public void chatTest() {
		
	}

	// 1.일반 페이징처리하기
	@GetMapping("admin/car")
	public void car(@RequestParam(defaultValue = "1") int page, @SessionAttribute(name = "admin", required = false) Admin admin,  @RequestParam(defaultValue = "apartmentIdx") String standard, @RequestParam(defaultValue = "apartmentIdx") String keyword, Model model) {
		String apartmentIdx = admin.getApartmentIdx();
		model.addAllAttributes(carService.selectCarList(page, apartmentIdx, standard, keyword));
	}
	
	// 2. 입주자 차량등록 신청건 페이징처리하기
	@GetMapping("admin/car/application")
	public void carApplication(@RequestParam(defaultValue = "1") int page,@SessionAttribute(name = "admin", required = false) Admin admin, @RequestParam(defaultValue = "apartmentIdx") String standard, @RequestParam(defaultValue = "apartmentIdx") String keyword, Model model) {
		String apartmentIdx = admin.getApartmentIdx();
		model.addAllAttributes(carService.selectCarApplicationList(page, apartmentIdx, standard, keyword));
	}
	
	// 건별등록
	@GetMapping("admin/caradd")
	@ResponseBody //비동기
	public String carAdd(@SessionAttribute(name = "admin", required = false) Admin admin, @RequestParam String building, @RequestParam String num, @RequestParam String carNumber){
		// 전달받은 아파트번호,동,호수 정보로 Generation 가져온다.
		System.out.println("building"+building);
		Generation generationInfo = new Generation();
		generationInfo.setApartmentIdx(admin.getApartmentIdx());
		generationInfo.setBuilding(building);
		generationInfo.setNum(num);
		Generation generation = carService.selectGenerationByBuildingAndNum(generationInfo);

		if(generation == null) {
			return "null";
		} else {
			// 전달받은 차량번호를 세대정보와 함께 넣어준다.
			Car car = new Car();
			car.setGenerationIdx(generation.getGenerationIdx());
			car.setApartmentIdx(generation.getApartmentIdx());
			car.setCarNumber(carNumber);
			
			String res = carService.insertAndQRWrite(car);
			
			myAlarmService.insertPvAlarm("'" + car.getCarNumber() + "' " +AlarmCode.ADD_CAR+"", car.getGenerationIdx());
			System.out.println(res);
			return res;
		}
		
	}
	
	// 비동기, 차량 삭제
	@GetMapping("admin/cardelete")
	@ResponseBody
	public void carDelete(@RequestParam List<String> caridx) {
		for (int i = 0; i < caridx.size() ; i++) {
			int res = carService.deleteCar(caridx.get(i));
			// 삭제 되었다면 완료, 아니면 실패
			if(res == 0) {
				throw new ToAlertException(ErrorCode.DC01);
			}
		}
	}
	
	// 비동기, 차량 승인. 비동기통신 1건이면 상관없는데 (ToAlert안쓰고 메시지로 처리가능하지만, 배열인경우 에러발동시켜야 중단하고 alert로 넘어갈 수 있다.)
	@GetMapping("admin/carapplication/approval")
	@ResponseBody
	public void carApplicationApproval(@RequestParam List<String> applicationidx) {
		String resStr = "";
		// 1. 전달받은 파라미터 배열을 하나씩 열어서 기존에 있는 차량정보인지 확인후 QR생성하고 처리 승인되게 한다.
		for (int i = 0; i < applicationidx.size() ; i++) {
			System.out.println(applicationidx.get(i)+"번째 처리중");
			// 2. CarApplication가 미처리건인지 확인한다.QR코드 생성하고 차량 DB에 넣어준다.
			// applicationidx 로 신청정보 가져와 대기중인 CarApplication 를 완성시킨다.
			// 만약 이미 처리된건이라면 update문이 0을 반환하여 null이 반환되면서 해당 if문은 넘어가게 된다.
			CarApplication carApplication = carService.selectCarApplication(applicationidx.get(i));
			
			if(carApplication != null) {
				// 유효한 신청이라면 QR코드를 생성해준다.
				Car car = new Car();
				car.setGenerationIdx(carApplication.getGenerationIdx());
				car.setApartmentIdx(carApplication.getApartmentIdx());
				car.setCarNumber(carApplication.getAplctCarNumber());
				
				resStr = carService.insertAndQRWrite(car);
				
			} 
			
			//3. QR코드가 생성이 되었다면 신청을 승인 처리해준다. 생성되지않았다면 승인처리 하지않고 에러 발동시킨다.
			if(resStr.equals("success")) {
				carService.updateCarApplicationApproval(applicationidx.get(i));
				myAlarmService.insertPvAlarm("'" + carApplication.getAplctCarNumber() + "' " +AlarmCode.ADD_CAR, carApplication.getGenerationIdx());

			} else {
				throw new ToAlertException(ErrorCode.IAC01);
			}
		}
	}
	
	// 비동기, 차량 삭제 (신청 반려). 비동기통신 1건이면 상관없는데 (ToAlert안쓰고 메시지로 처리가능하지만, 배열인경우 에러발동시켜야 중단하고 alert로 넘어갈 수 있다.)
	@GetMapping("admin/carapplication/reject")
	@ResponseBody
	public void carApplicationDelete(@RequestParam List<String> applicationidx) {
		// 1. 반려하면 처리상태 바꿔준다
		for (int i = 0; i < applicationidx.size() ; i++) {
			int res = carService.updateCarApplicationReject(applicationidx.get(i));
			// 반려 되었다면 완료, 아니면 실패
			if(res == 0) {
				throw new ToAlertException(ErrorCode.DAC01);
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
	public String carRead(@RequestParam String generationidx, @RequestParam String caridx, HttpSession session, Model model) {
		
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
			// 주자관련 session 넣어서 보내준다
			session.setAttribute("apartmentIdx", carService.selectCar(carIdx).getApartmentIdx());
			model.addAttribute("alertMsg", msg);
		} else { 
			model.addAttribute("alertMsg", "실패하였습니다. 등록정보를 확인하세요.");
		}
		
		model.addAttribute("url", "/myapt/parking");
		
		return "common/result";
	}


}
