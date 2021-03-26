package com.kh.aboo.board.info.model.repository;

import java.util.List;
<<<<<<< Updated upstream
import java.util.Map;
=======
>>>>>>> Stashed changes

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
	
<<<<<<< Updated upstream
	Map<String,Object> selectInfoBoardList(Paging paging);
=======
	List<InfoBoard> selectInfoBoardList(Paging paging);
>>>>>>> Stashed changes
	
	@Select("select count(*) from tb_inf_qst_brd")
	int selectInfoContentCnt();
	
	@Select("select * from tb_inf_qst_brd where b_idx = #{bIdx}")
<<<<<<< Updated upstream
	InfoBoard selectInfoBoardDetail(String bIdx);
	
	@Select("select * from tb_file where type_idx = #{bIdx}")
	List<FileVo> selectFileWithBIdx(String bIdx);
=======
	InfoBoard selectBoardDetail(String bdIdx);
	
	@Select("select * from tb_file where type_idx = #{bIdx}")
	List<FileVo> selectFileWithBdIdx(String bdIdx);
>>>>>>> Stashed changes
}
