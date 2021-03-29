package com.kh.aboo.user.manager.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.repository.AdminRepository;
import com.kh.aboo.user.manager.model.vo.Admin;

@Component
public class AdminValidator implements Validator{

	
	private final AdminRepository adminRepository;
	
	public AdminValidator(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return Admin.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Pattern pattern = Pattern.compile("^(?!.*[ㄱ-힣])(?=.*\\W)(?=.*\\d)(?=.*[a-zA-Z])(?=.{8,})");
		Admin admin = (Admin) target;
		
		
		if (!pattern.matcher(admin.getPassword()).find()) {
			errors.rejectValue("password","error.password","비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상인 문자열입니다.");
		}		
	}

}
