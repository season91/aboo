package com.kh.aboo.myapt.parking.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.aboo.admin.car.model.vo.Car;
import com.kh.aboo.admin.car.model.vo.CarApplication;
import com.kh.aboo.myapt.parking.model.repository.ParkingRepository;
import com.kh.aboo.myapt.parking.model.service.ParkingService;

@Service
public class ParkingServiceImpl implements ParkingService{
	
	private final ParkingRepository parkingRepository;
	
	public ParkingServiceImpl(ParkingRepository parkingRepository) {
		this.parkingRepository = parkingRepository;
	}

	@Override
	public Map<String, Object> possibleParking(String apartmentIdx) {
		Map<String, Object> parkingMap = new HashMap<String, Object>();
		// 현재 주차가능대수 조회
		int totalParking = parkingRepository.selectApartmentParking(apartmentIdx);
		parkingMap.put("totalParking", totalParking);
		
		int InCarCnt = parkingRepository.selectIsInCarCnt();
		int possibleParking = totalParking - InCarCnt;
		parkingMap.put("possibleParking", possibleParking);
		
		return parkingMap;
	}

	@Override
	public int insertCarApplication(CarApplication carApplication) {
		// TODO Auto-generated method stub
		return parkingRepository.insertCarApplication(carApplication);
	}

	@Override
	public List<CarApplication> selectCarApplicationByGenerationIdx(String generationIdx) {
		// TODO Auto-generated method stub
		return parkingRepository.selectCarApplicationByGenerationIdx(generationIdx);
	}

	@Override
	public Car selectCarByApplicationInfo(CarApplication carApplication) {
		// TODO Auto-generated method stub
		return parkingRepository.selectCarByApplicationInfo(carApplication);
	}

	@Override
	public CarApplication selectCarApplication(CarApplication carApplication) {
		// TODO Auto-generated method stub
		return parkingRepository.selectCarApplication(carApplication);
	}

}
