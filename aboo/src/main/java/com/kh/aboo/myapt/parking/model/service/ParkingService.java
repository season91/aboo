package com.kh.aboo.myapt.parking.model.service;

import java.util.List;
import java.util.Map;

import com.kh.aboo.admin.car.model.vo.CarApplication;

public interface ParkingService {

	// 주차현황 확인
	Map<String, Object> possibleParking(String apartmentIdx);
	
	// [차량등록신청]
	// 1. 등록하기
	int insertCarApplication(CarApplication carApplication);
	
	// 2. 등록내역 있는지 확인하기
	List<CarApplication> selectCarApplicationByGenerationIdx(String generationIdx);
}
