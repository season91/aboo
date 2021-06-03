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
public class CarRepositort {
	
	@Autowired
	CarRepository carRepository;
	
	@Test
	public Car insertCar() {
		Car car = new Car();
		car.setApartmentIdx("100000");
		car.setGenerationIdx("100362");
		car.setCarNumber("123허4568");
		
		return car;
	};

	@Test
	public void selectCarByCarNumberAndGenerationIdx() {
		String carNumber = "123허4565";
		String generationIdx = "100297";
		Map<String, String> commandMap = new HashMap<String, String>();
		commandMap.put("carNumber", carNumber);
		commandMap.put("generationIdx", generationIdx);
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
	}
	

}
