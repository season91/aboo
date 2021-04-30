package com.kh.aboo.board.used.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.used.model.vo.UsedBrd;
import com.kh.aboo.board.used.model.vo.UsedCmt;
import com.kh.aboo.common.util.file.FileVo;

public interface UsedService {

	Map<String, Object> selectUsedBrdList(int currentPage, String apartmentIdx, String kind, String keyword);

	Map<String, Object> selectUsedDetail(String usedIdx);

	int updateUsedPrivate(String usedIdx);

	void updateUsedDelete(String usedIdx);

	UsedBrd selectUsedIdx(String usedIdx);

	// 게시글 업로드
	void insertUsedBrd(UsedBrd usedBrd, List<MultipartFile> files);

	void updateUsedBrdFileModify(UsedBrd usedBrd, List<MultipartFile> files);

	int insertUsedBrdCmtUpload(UsedCmt usedCmt);

	List<UsedCmt> selectUsedBrdCmt(String usedIdx);

	int selectUsedBrdCmtCnt(String usedIdx);

	int updateUsedBrdCmt(UsedCmt usedCmt);

	int updateUsedBrdCmtDelete(String usedCmtIdx);

	int updateUsedBrdCmtPrivate(String usedCmtIdx);
	
	int selectUsedBrdTodayCnt(String apartmentIdx);
	
	int selectInfoBrdTodayCnt(String apartmentIdx);

	int selectIntBrdTodayCnt(String apartmentIdx);


}
