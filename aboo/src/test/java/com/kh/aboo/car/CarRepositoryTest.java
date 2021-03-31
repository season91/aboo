package com.kh.aboo.car;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.aboo.admin.car.model.repository.CarRepository;
import com.kh.aboo.admin.car.model.vo.Car;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class CarRepositoryTest {
	
	@Autowired
	CarRepository carRepository;
	
	@Test
	public void insertCar() {
		Car car = new Car();
		car.setGenerationIdx("100297");
		car.setCarNumber("123허4568");
		car.setCarQR("/resources/vehicle/");
		System.out.println(carRepository.insertCar(car));
		
	};

	@Test
	public void selectCarByCarNumberAndGenerationIdx() {
		String carNumber = "123허4565";
		String generationIdx = "100297";
		Map<String, String> commandMap = new HashMap<String, String>();
		commandMap.put("carNumber", carNumber);
		commandMap.put("generationIdx", generationIdx);
		System.out.println(carRepository.selectCarByCarNumberAndGenerationIdx(commandMap));

	}

	// 차량 상태 업데이트 프로시저 확인
	@Test
	public void procedureIsInCarUpdate() {
		String carIdx = "100032";
		carRepository.procedureIsInCarUpdate(carIdx);
		
	}

	@Test
	public void selectCarApplicationList() {
		Map<String, Object> commandMap = new HashMap<String, Object>();
		commandMap.put("apartmentIdx", "100000");
		// 총개수 가져오나?
		System.out.println(carRepository.selectApplicationContentCnt(commandMap));
		// 리스트 가져오나?
		System.out.println(carRepository.selectCarApplicationList(commandMap));
	}
	

}
