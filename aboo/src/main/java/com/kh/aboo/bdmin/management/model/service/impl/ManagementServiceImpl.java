package com.kh.aboo.bdmin.management.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.aboo.bdmin.management.model.repository.ManagementRepository;
import com.kh.aboo.bdmin.management.model.service.ManagementService;
import com.kh.aboo.bdmin.management.model.vo.ApartApplication;
import com.kh.aboo.bdmin.management.model.vo.Bdmin;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.user.apartment.model.vo.Apartment;
import com.kh.aboo.user.manager.model.vo.Admin;

@Service
public class ManagementServiceImpl implements ManagementService{

	private final ManagementRepository managementRepository;
	
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
	public int insertAdmin(Admin admin) {
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


	
}
