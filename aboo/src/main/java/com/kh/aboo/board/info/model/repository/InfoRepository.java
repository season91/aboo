package com.kh.aboo.board.info.model.repository;

import org.apache.ibatis.annotations.Insert;

import com.kh.aboo.board.info.model.vo.InfoBoard;

public interface InfoRepository {
	
	//정보게시판 게시글 업로드
	@Insert("INSERT INTO TB_INF_QST_BRD(B_IDX,APARTMENT_IDX,B_CATEGORY,B_TITLE,B_CONTENT,B_WIRTER) "
			+ "values(SC_INFO_IDX.NEXTVAL,#{bIdx},#{apartmentIdx},#{bCategory},#{bTitle},#{bContent},#{bWriter})")
	int insertInfoBoard(InfoBoard infoBoard);
}
