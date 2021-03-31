package com.kh.aboo.admin.car.model.service;

import java.util.List;
import java.util.Map;

import com.kh.aboo.admin.car.model.vo.Car;
import com.kh.aboo.admin.car.model.vo.CarApplication;
import com.kh.aboo.user.generation.model.vo.Generation;

public interface CarService {
	
	// test 입주자확인용
	Car selectCar(String carIdx);
	
	// [관리자 차량확인 페이징]
	// 1. 페이징 화면구현을 위한 List
	Map<String, Object> selectCarList(int currentPage, Map<String, Object> searchMap);
	
	// 2. 차량등록신청 페이징 List
	Map<String, Object> selectCarApplicationList(int currentPage, Map<String, Object> applicationMap);
	
	// [차량 신청 승인]
	// 1. 승인시 isprocess update 해준다.
	int updateCarApplicationApproval(String applicationIdx);
	
	// 2. 반려시 isprocess 2로 update 해준다
	int updateCarApplicationReject(String applicationIdx);
	
	// [차량 등록]
	// 1. 등록을 위한 세대정보 가져오기
	Generation selectGenerationByBuildingAndNum(Generation generation);
	
	// 2. QR코드 생성 & DB 등록한다.
	String insertAndQRWrite(Car car);
	
	// 3. 차량등록 신청건 등록을 위한 조회
	CarApplication selectCarApplication(String applicationIdx);

	// [차량번호 수정]
	int updateCar(Car car);
	
	// [차량 삭제]
	int deleteCar(String carIdx);
	
	// [차량 입출차 정보 변경]
	// 1. 유효한 정보인지 확인한다.
	List<String> selectCarNumberByGenerationIdx(String generationIdx);
	
	String updateIsInCar(String carIdx);
}
