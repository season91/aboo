package com.kh.aboo.bdmin.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.aboo.bdmin.model.repository.BdminRepository;
import com.kh.aboo.bdmin.model.service.BdminService;
import com.kh.aboo.bdmin.model.vo.ApartApplication;
import com.kh.aboo.bdmin.model.vo.Bdmin;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.user.apartment.model.vo.Apartment;

@Service
public class BdminServiceImpl implements BdminService{

	private final BdminRepository bdminRepository;
	
	public BdminServiceImpl(BdminRepository bdminRepository) {
		this.bdminRepository = bdminRepository;
	}

	@Override
	public Bdmin selectBdminForAuth(Bdmin bdmin) {
		Bdmin bdminInfo = bdminRepository.selectBdminForAuth(bdmin);
		if(bdminInfo == null || !bdmin.getPassword().equals(bdminInfo.getPassword())) {
			return null;
		}
		return bdminInfo;
	}
	
	public Map<String, Object> searchMap(String standard, String keyword){
		Map<String, Object> searchMap = new HashMap<String, Object>();
		// 전달받은 검색기준으로 검색타입, 검색키워드를 넣어 동적쿼리 사용한다.
		
		switch(standard) {
		case "basic":
			searchMap.put("searchType", "basic");
			break;
		case "apartmentName":
			// 쿼리검색용
			searchMap.put("searchType", "apartmentName");
			
			// view용
			searchMap.put("keyword", keyword);
			break;
		
		}
		
		return searchMap;
	}

	@Override
	public Map<String, Object> selectApartList(int currentPage, String standard, String keyword) {
		Map<String, Object> searchMap = searchMap(standard, keyword);
		// 페이징처리
		Paging paging = Paging.builder()
				.currentPage(currentPage)
				.blockCnt(5)
				.cntPerPage(10)
				.type("apartment")
				.total(bdminRepository.selectApartCnt(searchMap))
				.build();
		System.out.println(paging.toString());
		searchMap.put("paging", paging);
		List<Apartment> apartList = bdminRepository.selectApartList(searchMap);
		searchMap.put("apartList", apartList);
		
		// TODO Auto-generated method stub
		return searchMap;
	}

	@Override
	public Map<String, Object> selectApartApplicationList(int page, String standard, String keyword) {
		Map<String, Object> searchMap = searchMap(standard, keyword);
		// 페이징처리
		Paging paging = Paging.builder()
				.currentPage(page)
				.blockCnt(5)
				.cntPerPage(10)
				.type("apartment")
				.total(bdminRepository.selectApartApplicationCnt(searchMap))
				.build();
		System.out.println(paging.toString());
		searchMap.put("paging", paging);
		List<Apartment> apartApplicationList = bdminRepository.selectApartApplicationList(searchMap);
		searchMap.put("apartApplicationList", apartApplicationList);
		
		return searchMap;
	}

	@Override
	public Apartment selectApartment(String apartmentIdx) {
		return bdminRepository.selectApartment(apartmentIdx);
	}

	@Override
	public int updateApartment(Apartment apartment) {
		return bdminRepository.updateApartment(apartment);
	}

	@Override
	public ApartApplication selectApartApplication(String applicationIdx) {
		return bdminRepository.selectApartApplication(applicationIdx);
	}



	@Override
	public int insertApartApplication(ApartApplication apartApplication) {
		return bdminRepository.insertApartApplication(apartApplication);
	}

	@Override
	public void procedureApartApplicationUpdate(ApartApplication apartApplication) {
		bdminRepository.procedureApartApplicationUpdate(apartApplication);
		
	}

	
	
}
