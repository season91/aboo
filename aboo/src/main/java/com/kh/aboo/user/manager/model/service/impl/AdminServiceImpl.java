package com.kh.aboo.user.manager.model.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.common.code.Configcode;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.mail.MailSender;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.user.generation.model.repository.GenerationRepository;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.repository.AdminRepository;
import com.kh.aboo.user.manager.model.service.AdminService;
import com.kh.aboo.user.manager.model.vo.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private MailSender mail;

	@Autowired
	private RestTemplate http;

	private final AdminRepository adminRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public void insertAdmin(Admin admin) {
		adminRepository.insertAdmin(admin);
	}

	@Override
	public Admin selectAdminForAuth(Admin admin) {

		Admin authInfo = adminRepository.selectAdminForAuth(admin.getId());
		if (authInfo == null || !encoder.matches(admin.getPassword(), authInfo.getPassword())) {
			return null;
		}

		return authInfo;
	}

	// 선영 어드민 세대 추가
	@Override
	public int insertGeneration(Generation generation, String apartmentIdx) {

		String id = generation.getBuilding() + "d" + generation.getNum() + "h";
		generation.setId(id);
		generation.setPassword(encoder.encode("123"));
		generation.setApartmentIdx(apartmentIdx);
		return adminRepository.insertGeneration(generation);
	}

	@Override
	public Map<String, Object> selectAuthorityList(int currentPage, String apartmentIdx) {
		Paging paging = Paging.builder().currentPage(currentPage).blockCnt(5).cntPerPage(5).type("board")
				.total(adminRepository.selectContentCnt(apartmentIdx)).build();

		Map<String, Object> commandMap = new HashMap<String, Object>();
		Map<String, Object> authorityMap = new HashMap<String, Object>();
		authorityMap.put("paging", paging);
		authorityMap.put("apartmentIdx", apartmentIdx);
		System.out.println("paging" + paging.toString());

		commandMap.put("paging", paging);
		commandMap.put("authorityList", adminRepository.selectAuthorityList(authorityMap));

		return commandMap;
	}

	@Override
	public Admin selectfindId(Admin admin) {
		return adminRepository.selectFindId(admin);
	}

	@Override
	public void authenticationIdMail(Admin admin, String authPathId) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "findid");
		body.add("id", admin.getId());
		body.add("authPath", authPathId);
		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(Configcode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		mail.send(admin.getEmail(), "메일", message);

	}

	@Override
	public Admin selectFindPassword(Admin admin) {
		return adminRepository.selectFindPassword(admin);
	}

	@Override
	public void authenticationPasswordMail(Admin admin, String password) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "findpassword");
		body.add("id", admin.getId());
		body.add("password", password);
		System.out.println("메일 보낼번호 : " + password);

		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(Configcode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		mail.send(admin.getEmail(), "메일", message);

		System.out.println("바뀔번호");
		admin.setPassword(encoder.encode(password));
		adminRepository.updateFindPassword(admin);

	}

	// 어드민 정보 불러오기
	@Override
	public Admin selectAdmin(Admin admin) {
		return adminRepository.selectAdmin(admin);
	}

	// 정보 업데이트
	@Override
	public int updateAdminModify(Admin admin) {
		String password = admin.getPassword();
		admin.setPassword(encoder.encode(password));
		return adminRepository.updateAdminModify(admin);
	}

	// 이메일 인증 이메일 보내기
	@Override
	public void authenticationEmail(Admin admin, String authPathEmail) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "email");
		body.add("id", admin.getId());
		body.add("authPath", authPathEmail);
		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(Configcode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		mail.send(admin.getEmail(), "이메일 인증 메일", message);
	}

	// 이메일 업데이트
	@Override
	public int updateAdminEmail(Admin admin) {
		return adminRepository.updateAdminEmail(admin);
	}

	// 세대 초기화 하기
	@Override
	public void updateResetGeneration(Generation generation) {
		adminRepository.updateDeleteGeneration(generation);
		generation.setPassword(encoder.encode("123"));
		adminRepository.insertGeneration(generation);
	}

	// 세대 삭제 하기
	@Override
	public void updateDeleteGeneration(Generation generation) {
		adminRepository.updateDeleteGeneration(generation);
	}
}
