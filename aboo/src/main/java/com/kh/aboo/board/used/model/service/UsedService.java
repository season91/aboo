package com.kh.aboo.board.used.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.used.model.vo.UsedBrd;

public interface UsedService {

	
	Map<String,Object> selectUsedBrdList(int currentPage, String apartmentIdx);
	
	
	Map<String, Object> selectUsedDetail(String usedIdx);
	
	int updateUsedPrivate(String usedIdx);

	int updateUsedDelete(String usedIdx);
	
	UsedBrd selectUsedIdx(String usedIdx);
	
	//게시글 업로드
	void insertUsedBrd (UsedBrd usedBrd, List<MultipartFile> files );

	
}
