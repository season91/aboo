package com.kh.aboo.board.info.model.repository;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.aboo.board.info.model.vo.InfoBoard;
import com.kh.aboo.common.util.file.FileVo;
import com.kh.aboo.common.util.paging.Paging;

@Mapper
public interface InfoRepository {
	
	//정보게시판 게시글 업로드
	@Insert("INSERT INTO TB_INF_QST_BRD(B_IDX,APARTMENT_IDX,B_CATEGORY,B_TITLE,B_CONTENT,B_WRITER) "
			+ "values(SC_INFO_IDX.NEXTVAL,#{apartmentIdx},#{bCategory},#{bTitle},#{bContent},#{bWriter})")
	int insertInfoBoard(InfoBoard infoBoard);
	int insertFile(FileVo file);
	
	List<InfoBoard> selectInfoBoardList(Paging paging);
	
	@Select("select count(*) from tb_inf_qst_brd")
	int selectInfoContentCnt();
	
	@Select("select * from tb_inf_qst_brd where b_idx = #{bIdx}")
	InfoBoard selectInfoBoardDetail(String bIdx);
	
	@Select("select * from tb_file where type_idx = #{bIdx}")
	List<FileVo> selectFileWithBIdx(String bIdx);
	
	@Select("select * from tb_file where type_idx = #{bdIdx}")
	List<FileVo> selectFileWithBdIdx(String bIdx);
	
	@Update("update tb_inf_qst_brd set b_title = #{bTitle}, b_content = #{bContent}, b_category = #{bCategory} where b_idx = #{bIdx}")
	int UpdateInfoBoard(InfoBoard infoBoard,String bIdx);
}
