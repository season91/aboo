package com.kh.aboo.myapt.parking.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kh.aboo.admin.car.model.vo.CarApplication;

@Component
public class CarApplicationValidator implements Validator{

	//어떤 조건의 컨트롤러 메서드 파라미터를 검증할 것인지 작성
	@Override
	public boolean supports(Class<?> clazz) {
		// Class<?> clazz : 컨트롤러 파라미터의 class 객체
		return CarApplication.class.equals(clazz);
	}

	//검증할 코드 작성
	@Override
	public void validate(Object target, Errors errors) {
		// Object target : 컨트롤러 메서드의 파라미터
		// Errors errors : 검증에 실패할 경우, 내용을 저장할 Error 객체. 컨트롤러 메서드의 파라미터로 전달된다.

		Pattern pattern = Pattern.compile("^\\d{2,3}[가-힣]\\d{4}"); //차량 정규표현식.
		CarApplication carApplicationInfo = (CarApplication) target;

		// 1. 차량번호 양식이 22마2222 , 222마2222 가 맞는지 확인
		if(!pattern.matcher(carApplicationInfo.getAplctCarNumber()).find()) {
			errors.rejectValue("aplctCarNumber", "error.aplctCarNumber", "차량번호를 다시 확인하세요.");
		} 
	}

}
