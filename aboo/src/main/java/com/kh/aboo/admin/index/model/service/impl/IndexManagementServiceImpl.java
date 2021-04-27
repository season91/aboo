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
		List<String> totalPay =  indexManagementRepository.selectMgmtfeeMonthFee(apartmentIdx);
		List<Integer> month = indexManagementRepository.selectMgmtfeeMonth(apartmentIdx);
		List<Integer> intRes = new ArrayList<Integer>();

		// 월의 index에 값을 넣어준다. (3월 -> 3-1 index에 값 넣어주기)
		// 만약 첫월이 1월이 아니라면 시작전까지 0원으로 맞춰주기.
		if(month.get(0) > 1) {
			int cnt = month.get(0)-1;
			for (int i = 0; i < cnt; i++) {
				intRes.add(i,0);
			}
		}
		
		for (int i = 0; i < totalPay.size(); i++) {
			String str = totalPay.get(i).substring(0, totalPay.get(i).length()-4); // 만원단위니까 뒤에 4개 잘라주기.
			int temp = Integer.parseInt(str);
			intRes.add(month.get(i)-1, temp);
			System.out.println(totalPay.get(i));
			System.out.println(temp);
		}

		return intRes;
	}
	
}
