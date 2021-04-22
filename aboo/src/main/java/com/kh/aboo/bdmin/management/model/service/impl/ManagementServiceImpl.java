package com.kh.aboo.bdmin.management.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.aboo.bdmin.management.model.repository.ManagementRepository;
import com.kh.aboo.bdmin.management.model.service.ManagementService;
import com.kh.aboo.bdmin.management.model.vo.ApartApplication;
import com.kh.aboo.bdmin.management.model.vo.Bdmin;
import com.kh.aboo.bdmin.management.model.vo.ManagerApplication;
import com.kh.aboo.common.code.ConfigCode;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.mail.MailSender;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.user.apartment.model.vo.Apartment;
import com.kh.aboo.user.manager.model.vo.Admin;

@Service
public class ManagementServiceImpl implements ManagementService{

	private final ManagementRepository managementRepository;
	
	@Autowired
	private MailSender mail;

	@Autowired
	private RestTemplate http;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public ManagementServiceImpl(ManagementRepository managementRepository) {
		this.managementRepository = managementRepository;
	}

	@Override
	public Bdmin selectBdminForAuth(Bdmin bdmin) {
		Bdmin bdminInfo = managementRepository.selectBdminForAuth(bdmin);
		if(bdminInfo == null || !bdmin.getPassword().equals(bdminInfo.getPassword())) {
			return null;
		}
		return bdminInfo;
	}

	@Override
	public Map<String, Object> selectApartList(int currentPage, Map<String, Object> searchMap) {
		// 페이징처리
		Paging paging = Paging.builder()
				.currentPage(currentPage)
				.blockCnt(5)
				.cntPerPage(10)
				.type("apartment")
				.total(managementRepository.selectApartCnt(searchMap))
				.build();
		System.out.println(paging.toString());
		searchMap.put("paging", paging);
		List<Apartment> apartList = managementRepository.selectApartList(searchMap);
		searchMap.put("apartList", apartList);
		
		// TODO Auto-generated method stub
		return searchMap;
	}

	@Override
	public Map<String, Object> selectApartApplicationList(int page, Map<String, Object> searchMap) {
		// 페이징처리
		Paging paging = Paging.builder()
				.currentPage(page)
				.blockCnt(5)
				.cntPerPage(10)
				.type("apartment")
				.total(managementRepository.selectApartApplicationCnt(searchMap))
				.build();
		System.out.println(paging.toString());
		searchMap.put("paging", paging);
		List<Apartment> apartApplicationList = managementRepository.selectApartApplicationList(searchMap);
		searchMap.put("apartApplicationList", apartApplicationList);
		
		return searchMap;
	}

	@Override
	public Apartment selectApartment(String apartmentIdx) {
		return managementRepository.selectApartment(apartmentIdx);
	}

	@Override
	public int updateApartment(Apartment apartment) {
		return managementRepository.updateApartment(apartment);
	}

	@Override
	public ApartApplication selectApartApplication(String applicationIdx) {
		return managementRepository.selectApartApplication(applicationIdx);
	}



	@Override
	public int insertApartApplication(ApartApplication apartApplication) {
		return managementRepository.insertApartApplication(apartApplication);
	}

	@Override
	public void procedureApartApplicationUpdate(ApartApplication apartApplication) {
		managementRepository.procedureApartApplicationUpdate(apartApplication);
	}
	

	@Override
	public Map<String, Object> selectAdminList(int page, Map<String, Object> searchMap) {
		Paging paging = Paging.builder()
				.currentPage(page)
				.blockCnt(5)
				.cntPerPage(10)
				.type("adminauthority")
				.total(managementRepository.selectAdminCnt(searchMap))
				.build();
		System.out.println(paging.toString());
		System.out.println("검색조건 " + searchMap);
		searchMap.put("paging", paging);

		List<Admin> adminList = managementRepository.selectAdminList(searchMap);
		searchMap.put("adminList", adminList);
		
		// 페이징정보에 뿌려줄 아파트 이름 가져온다.
		List<String> apartmentNameList = new ArrayList<String>();
		for (int i = 0; i < adminList.size(); i++) {
			String apartmentIdx = adminList.get(i).getApartmentIdx();
			apartmentNameList.add(managementRepository.selectApartmentNameByApartmentIdx(apartmentIdx));
		}
		searchMap.put("apartmentNameList", apartmentNameList);
		
		//테스트
		searchMap.put("apartmentList", managementRepository.selectApartmentList());
		System.out.println(searchMap);
		return searchMap;
	}

	@Override
	public int insertAdmin(Admin admin, String apartmentInfo) {
		String[] apartmentIndx = apartmentInfo.split("/");
		// [0]은 아파트이름이고 [1]아파트 idx이다.
		admin.setApartmentIdx(apartmentIndx[1]);
		admin.setPassword(encoder.encode(admin.getPassword()));
		
		// 중복아이디인지 확인
		if(managementRepository.selectIdCheck(admin) > 0) {
			throw new ToAlertException(ErrorCode.IDCHECK01);
		} 
		return managementRepository.insertAdmin(admin);
	}

	@Override
	public int updateAdminIsDel(String managerIdx) {
		return managementRepository.updateAdminIsDel(managerIdx);
	}

	@Override
	public int insertManagerContact(ManagerApplication managerApplication) {
		return managementRepository.insertManagerContact(managerApplication);
	}
	
	@Override
	public int selectManagerContactId(String id) {
		return managementRepository.selectManagerContactId(id);
	}

	@Override
	public Map<String, Object> selectAdminApplicationList(int page, Map<String, Object> searchMap) {
		Paging paging = Paging.builder()
				.currentPage(page)
				.blockCnt(5)
				.cntPerPage(10)
				.type("adminapllist")
				.total(managementRepository.selectAdminApplicationCnt(searchMap))
				.build();
		System.out.println(paging.toString());
		System.out.println("검색조건 " + searchMap);
		searchMap.put("paging", paging);

		List<ManagerApplication> applicationList = managementRepository.selectAdminApplicationList(searchMap);
		searchMap.put("applicationList", applicationList);
		System.out.println("결과"+searchMap);
		return searchMap;
	}

	@Override
	public Map<String, Object> selectAdminApplication(String managerApplicationIdx) {
		// 상세 신청과 아파트검색을위한 리스트 2개 보낸다
		Map<String, Object> commandMap = new HashMap<String, Object>();
		commandMap.put("application", managementRepository.selectAdminApplication(managerApplicationIdx));
		commandMap.put("apartmentList", managementRepository.selectApartmentList());
		return commandMap;
	}

	@Override
	public String updateAdminApplication(ManagerApplication application, String apartmentInfo) {
		String msg = "";
		// 신청서 승인 후 매니저 추가한다.
		// 승인이든 반려이든 요청온 신청서 값 업데이트
		System.out.println(application);
		managementRepository.updateAdminApplication(application);
		
		// 만약 요청값이 1인 승인이라면 매니저 추가한다.
		if(application.getIsApproval().equals("1")) {
			String[] apartmentIndx = apartmentInfo.split("/");
			// [0]은 아파트이름이고 [1]아파트 idx이다.
			Admin admin = new Admin();
			admin.setApartmentIdx(apartmentIndx[1]);
			admin.setId(application.getId());
			admin.setPassword(encoder.encode(application.getPassword()));
			admin.setName(application.getName());
			admin.setEmail(application.getEmail());
			admin.setBirth(application.getBirth());
			
			managementRepository.insertAdminByApplication(admin);
			
			// 메일로 승인을 알려준다.
			applicationEmail(admin);
			msg = "정상 승인 되었습니다.";
		} else {
			msg = "반려 되었습니다.";
		}
		
		return msg;
	}
	
	public void applicationEmail(Admin admin) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mail-template", "adminapproval"); //views 경로
		body.add("id", admin.getName());
		
		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(ConfigCode.DOMAIN + "/mail")
				.header("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).body(body);

		ResponseEntity<String> response = http.exchange(request, String.class);
		String message = response.getBody();
		mail.send(admin.getEmail(), "❗ [ABOO:아파트를 부탁해] 어드민 계정 신청이 승인되었습니다.", message);
		
	}
	
}
