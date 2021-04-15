package com.kh.aboo.admin.car.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.aboo.admin.car.model.repository.CarRepository;
import com.kh.aboo.admin.car.model.service.CarService;
import com.kh.aboo.admin.car.model.vo.Car;
import com.kh.aboo.admin.car.model.vo.CarApplication;
import com.kh.aboo.common.code.ConfigCode;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.common.util.qrcode.QRCodeUtil;
import com.kh.aboo.user.generation.model.vo.Generation;

@Service
public class CarServiceImpl implements CarService{
	
	private final CarRepository carRepository;

	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public Car selectCar(String carIdx) {
		// TODO Auto-generated method stub
		return carRepository.selectCar(carIdx);
	}
	
	
	@Override
	public Generation selectGenerationByBuildingAndNum(Generation generation) {
		if(carRepository.selectGenerationByBuildingAndNum(generation) == null) {
			throw new ToAlertException(ErrorCode.SC02);
		}
		return carRepository.selectGenerationByBuildingAndNum(generation);
	}

	@Override
	public String insertAndQRWrite(Car car) {
		String resStr = "";
		// QR코드 생성한 후 baseURL 경로에 저장한다.
		// 1. DB에 저장한다 (시퀀스번호때문에 어쩔수없다.)
		int resCnt = carRepository.selectCarCnt(car.getGenerationIdx());
		// 2건이상 등록된 세대라면 등록하지 않는다. 차량번호가 중복되면 등록하지 않는다.
		Car vehicleCheck = carRepository.selectCarByGenerationIdxAndCarNumber(car);
		System.out.println("등록건수" + resCnt);
		int insertRes = 0;
		if(resCnt < 2 && vehicleCheck == null) {
			insertRes = carRepository.insertCar(car);
		} else {
			throw new ToAlertException(ErrorCode.IQR02);
		}

		// insert한 차량정보 가져오기. 차량번호와 세대번호로
		Car newCar = carRepository.selectCarByGenerationIdxAndCarNumber(car);
		// 조회결과가 없다면 QR생성하지 않는다. 예외처리해준다.
		if(insertRes == 0) {
			throw new ToAlertException(ErrorCode.IQR01);
		}
		// 2. QR코드 생성한다.
		// url 링크는 localhost:9393/admin/carread?generationidx=값&vehicleidx=값
		// 파일명은 c시퀀스번호로 한다.
		String domain = ConfigCode.QR_DOMAIN.desc +"/admin/carread?generationidx="+newCar.getGenerationIdx()+"&caridx="+newCar.getCarIdx();
		QRCodeUtil qr = new QRCodeUtil();
		qr.makeQR(domain, "c"+newCar.getCarIdx());
		
		return "등록되었습니다.";
	}
	
	public Map<String, Object> searchMap(String apartmentIdx, String standard, String keyword){
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("apartmentIdx", apartmentIdx);
		searchMap.put("searchType", standard);
		searchMap.put("keyword", keyword);
		switch (standard) {
		case "generationInfo":
			// 세대정보로 검색, 101-101 이런식으로 입력이되서 동수 호수로 분리하고 세대관리번호 가져와서 넣어준다.
			Generation generation = new Generation();
			String[] generationInfo = keyword.split("-");
			generation.setApartmentIdx(apartmentIdx);
			generation.setBuilding(generationInfo[0]);
			generation.setNum(generationInfo[1]);
			System.out.println(generation);
			
			// 조회된 세대관리번호를 map에 담아준다.
			String generationIdx = selectGenerationByBuildingAndNum(generation).getGenerationIdx();
			searchMap.put("searchType", "generationInfo");
			searchMap.put("generationInfo", generationIdx);
			break;
		}
		
		return searchMap;
	}
	

	@Override
	public Map<String, Object> selectCarList(int currentPage, String apartmentIdx, String standard, String keyword) {
		Map<String, Object> searchMap = searchMap(apartmentIdx, standard, keyword);
		//페이징처리
		Paging paging = Paging.builder()
				.currentPage(currentPage)
				.blockCnt(5)
				.cntPerPage(10)
				.type("car")
				.total(carRepository.selectContentCnt(searchMap))
				.build();
		System.out.println(paging.toString());
		
		// paing 세대조건 정보 넣을 맵
		searchMap.put("paging", paging);
		
		// 페이징정보 포함해서 페이징에 뿌려줄 리스트 받아온다.
		List<Car> carList = carRepository.selectCarList(searchMap);
		searchMap.put("carList", carList);
		System.out.println("carList"+carList);
		
		// 차량리스트 기준 세대정보 가져오자.
		List<Generation> generationList = new ArrayList<>();
		for (int i = 0; i < carList.size(); i++) {
			String generationIdx = carList.get(i).getGenerationIdx();
			generationList.add(carRepository.selectGenerationByGenerationIdx(generationIdx));
		}

		searchMap.put("generationList", generationList);
		System.out.println("searchMap" + searchMap);
		return searchMap;
	}


	@Override
	public Map<String, Object> selectCarApplicationList(int currentPage, String apartmentIdx, String standard, String keyword) {
		Map<String, Object> applicationMap = searchMap(apartmentIdx, standard, keyword);
		
		Paging paging = Paging.builder()
				.currentPage(currentPage)
				.blockCnt(5)
				.cntPerPage(10)
				.type("car")
				.total(carRepository.selectApplicationContentCnt(applicationMap))
				.build();
		System.out.println(paging.toString());
		
		// paing 세대조건 정보 넣을 맵
		applicationMap.put("paging", paging);
		
		// 페이징정보 포함해서 페이징에 뿌려줄 리스트 받아온다.
		List<CarApplication> carApplicationList = carRepository.selectCarApplicationList(applicationMap);
		applicationMap.put("carApplicationList", carApplicationList);
		System.out.println("carApplicationList"+carApplicationList);
		
		// 차량리스트 기준 세대정보 가져오자.
		List<Generation> generationList = new ArrayList<>();
		for (int i = 0; i < carApplicationList.size(); i++) {
			String generationIdx = carApplicationList.get(i).getGenerationIdx();
			generationList.add(carRepository.selectGenerationByGenerationIdx(generationIdx));
		}

		applicationMap.put("generationList", generationList);
		System.out.println("applicationMap" + applicationMap);
		return applicationMap;
	}


	@Override
	public int deleteCar(String carIdx) {
		// TODO Auto-generated method stub
		return carRepository.deleteCar(carIdx);
	}

	@Override
	public int updateCar(Car car) {
		// 혹시 수정하려는 차량번호가 이미 있는지 중복 확인해보자
		Car vehicleCheck = carRepository.selectCarByGenerationIdxAndCarNumber(car);
		if(vehicleCheck == null) {
			return carRepository.updateCar(car);
		} else {
			throw new ToAlertException(ErrorCode.IC01);
		}
		
	}


	@Override
	public String updateIsInCar(String carIdx) {
		String msg = "";
		// 1. 상태 업데이트 해준다
		carRepository.procedureIsInCarUpdate(carIdx);
		
		// 2. 변경된 상태 확인해서 메시지 정해 보내준다.
		Car car = carRepository.selectCar(carIdx);
		int status = car.getIsInCar();
		if(status == 0) {
			msg = "출차가 되었습니다.";
		} else if(status == 1) {
			msg = "입차가 완료되었습니다.";
		} else { 
			throw new ToAlertException(ErrorCode.SC01);
		}
		return msg;
	}



	@Override
	public List<String> selectCarNumberByGenerationIdx(String generationIdx) {
		// TODO Auto-generated method stub
		return carRepository.selectCarNumberByGenerationIdx(generationIdx);
	}

	@Override
	public int updateCarApplicationApproval(String applicationIdx) {
		// TODO Auto-generated method stub
		return carRepository.updateCarApplicationApproval(applicationIdx);
	}
	
	@Override
	public int updateCarApplicationReject(String applicationIdx) {
		// TODO Auto-generated method stub
		return carRepository.updateCarApplicationReject(applicationIdx);
	}

	@Override
	public CarApplication selectCarApplication(String applicationIdx) {
		// TODO Auto-generated method stub
		return carRepository.selectCarApplication(applicationIdx);
	}


}
