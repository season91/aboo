package com.kh.aboo.board.used.model.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.aboo.board.used.model.repository.UsedRepository;
import com.kh.aboo.board.used.model.service.UsedService;
import com.kh.aboo.board.used.model.vo.UsedBrd;
import com.kh.aboo.common.util.paging.Paging;


@Service
public class UsedServiceImpl implements UsedService {

	private final UsedRepository usedRepository;

	public UsedServiceImpl(UsedRepository usedRepository) {
		this.usedRepository = usedRepository;
	}

	@Override
	public Map<String, Object> selectUsedBrdList(int currentPage, String apartmentIdx) {
		Paging paging = Paging.builder().currentPage(currentPage).blockCnt(5).cntPerPage(5).type("board")
				.total(usedRepository.selectUsedBrdCnt()).build();

		Map<String, Object> commandMap = new HashMap<String, Object>();
		Map<String, Object> usedMap = new HashMap<String, Object>();

		usedMap.put("paging", paging);
		usedMap.put("apartmentIdx",apartmentIdx);
		
		commandMap.put("paging", paging);
		commandMap.put("usedBrdList", usedRepository.selectUsedBrdList(usedMap));
		
		return commandMap;
	}

	@Override
	public UsedBrd selectUsedDetail(String usedIdx) {
		return usedRepository.selectUsedDetail(usedIdx);
	}

	@Override
	public int updateUsedPrivate(String usedIdx) {
		return usedRepository.updateUsedPrivate(usedIdx);
	}
	
	@Override
	public int updateUsedDelete(String usedIdx) {
		return usedRepository.updateUsedDelete(usedIdx);
	}
	
	@Override
	public UsedBrd selectUsedIdx(String usedIdx) {
		return usedRepository.selectUsedIdx(usedIdx);
	}
}
