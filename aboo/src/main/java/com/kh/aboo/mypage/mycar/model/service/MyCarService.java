package com.kh.aboo.mypage.mycar.model.service;

import java.util.List;

import com.kh.aboo.admin.car.model.vo.Car;

public interface MyCarService {

	// [등록 차량 조회]
	// 1. 등록된 차량 정보 조회. 최대2개가 나올 수 있어 List로 받는다.
	List<Car>  selectCarByGenerationIdx(String generationIdx);

}
