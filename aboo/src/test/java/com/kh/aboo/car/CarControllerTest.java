package com.kh.aboo.car;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.aboo.admin.car.model.service.CarService;
import com.kh.aboo.admin.car.model.vo.Car;
import com.kh.aboo.common.code.Configcode;
import com.kh.aboo.user.generation.model.vo.Generation;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class CarControllerTest {
	@Autowired
	CarService carService;

	
	@Test
	public void carAdd() {
		// 세대정보 가져온다
		Generation generationInfo = new Generation();
		generationInfo.setBuilding("104");
		generationInfo.setNum("405");
		generationInfo.setApartmentIdx("100000");
		
		Generation generation = carService.selectGenerationByBuildingAndNum(generationInfo);
		
		System.out.println(generation);
		
		// 세대정보와 차량번호를 넣어준다.
		Car car = new Car();
		car.setApartmentIdx(generation.getApartmentIdx());
		car.setGenerationIdx(generation.getGenerationIdx());
		car.setCarNumber("104마4545");
		car.setCarQR(Configcode.QRCODE_PATH.desc);
		
		String res = carService.insertAndQRWrite(generation.getGenerationIdx(), car);
		System.out.println(res);
	};
	
	
	@Test
	public void carApplicationApproval() {
		
		// 등록신청 
		
	}
}
