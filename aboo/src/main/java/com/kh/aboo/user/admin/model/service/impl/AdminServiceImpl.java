package com.kh.aboo.user.admin.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.aboo.user.admin.model.repository.AdminRepository;
import com.kh.aboo.user.admin.model.service.AdminService;
import com.kh.aboo.user.admin.model.vo.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private PasswordEncoder encoder;

	private final AdminRepository adminRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public Admin selectGenerationForAuth(Admin admin) {
		
		Admin authInfo = adminRepository.selectGenerationForAuth(admin.getId());
		if (authInfo == null || !encoder.matches(admin.getPassword(), authInfo.getPassword())) {
			return null;
		}

		return authInfo;
	}

}
