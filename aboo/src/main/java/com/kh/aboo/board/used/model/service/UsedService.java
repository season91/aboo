package com.kh.aboo.board.used.model.service;

import java.util.Map;

import com.kh.aboo.board.used.model.vo.UsedBrd;

public interface UsedService {

	
	Map<String,Object> selectUsedBrdList(int currentPage, String apartmentIdx);
	
	
	UsedBrd selectUsedDetail(String usedIdx);
	
	int updateUsedPrivate(String usedIdx);

	int updateUsedDelete(String usedIdx);
	
	UsedBrd selectUsedIdx(String usedIdx);
}
