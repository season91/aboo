package com.kh.aboo.admin.index.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.aboo.admin.index.model.repository.IndexManagementRepository;
import com.kh.aboo.admin.index.model.service.IndexManagementService;

@Service
public class IndexManagementServiceImpl implements IndexManagementService{

	private final IndexManagementRepository indexManagementRepository;
	
	public IndexManagementServiceImpl(IndexManagementRepository indexManagementRepository) {
		this.indexManagementRepository = indexManagementRepository;
	}

	@Override
	public List<Integer> selectMgmtfeeMonthFee(String apartmentIdx) {
		List<String> res =  indexManagementRepository.selectMgmtfeeMonthFee(apartmentIdx);
		List<Integer> intRes = new ArrayList<Integer>();
		for (int i = 0; i < res.size(); i++) {
			String str = res.get(i).substring(0, res.get(i).length()-4); // 만원단위니까 뒤에 4개 잘라주기.
			int temp = Integer.parseInt(str);
			intRes.add(temp);
		}
		
		System.out.println(intRes);
		return intRes;
	}
	
}
