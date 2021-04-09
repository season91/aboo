package com.kh.aboo.board.info.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.info.model.vo.InfoBoard;
import com.kh.aboo.board.info.model.vo.InfoCmt;

public interface InfoService {
	
	//희원 정보게시판 게시글 업로드
	int insertInfoBoard (InfoBoard infoBoard);
	Map<String,Object> selectInfoBoardList(int currentPage,String apartmentIdx);
	Map<String,Object> selectInfoBoardDetail(String bIdx);
	int updateInfoBoard(InfoBoard infoBoard);
	int deleteInfoBoard(String bIdx, String apartmentIdx);
	int updateInfoPrivate(String bIdx);
	Map<String,Object> selectInfoSearchList(int currentPage,String apartmentIdx,String keyword);
	
	//희원 정보게시판 댓글 업로드
	int insertInfoCmt(InfoCmt infoCmt);
	List<InfoCmt> selectInfoCmtList(String bIdx); 
	int selectInfoCmtcnt(String bIdx);
	int updateInfoCmt(InfoCmt infoCmt);
	int deleteInfoCmt(String cIdx);
	int UpdateInfoCmtprivate(String cIdx);
}
