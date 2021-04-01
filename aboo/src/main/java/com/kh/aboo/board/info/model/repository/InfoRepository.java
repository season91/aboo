package com.kh.aboo.board.info.model.repository;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.aboo.board.info.model.vo.InfoBoard;
import com.kh.aboo.board.info.model.vo.InfoCmt;
import com.kh.aboo.common.util.file.FileVo;
import com.kh.aboo.common.util.paging.Paging;

@Mapper
public interface InfoRepository {
	
	//정보게시판 게시글 업로드
	@Insert("INSERT INTO TB_INF_QST_BRD(B_IDX,APARTMENT_IDX,B_CATEGORY,B_TITLE,B_CONTENT,B_WRITER,GENERATION_IDX) "
			+ "values(SC_INFO_IDX.NEXTVAL,#{apartmentIdx},#{bCategory},#{bTitle},#{bContent},#{bWriter},#{generationIdx})")
	int insertInfoBoard(InfoBoard infoBoard);
	int insertFile(FileVo file);
	
	//페이징처리 된 정보게시판 글목록 불러오기
	List<InfoBoard> selectInfoBoardList(@Param(value = "queryStart") int queryStart, @Param(value = "queryEnd") int queryEnd,@Param(value = "apartmentIdx")  String apartmentIdx);
	
	//정보게시판 글 갯수 불러오기
	@Select("select count(*) from tb_inf_qst_brd where apartment_idx = #{apartmentIdx} and b_isdel = 0")
	int selectInfoContentCnt(String apartmentIdx);
	
	//글번호로 게시글 불러오기
	@Select("select * from tb_inf_qst_brd where b_idx = #{bIdx}")
	InfoBoard selectInfoBoardDetail(String bIdx);
	
	//글번호로 업로드된 파일 불러오기
	@Select("select * from tb_file where type_idx = #{bIdx}")
	List<FileVo> selectFileWithBIdx(String bIdx);
	
	//게시글 수정하기
	@Update("update tb_inf_qst_brd set b_title = #{bTitle}, b_content = #{bContent}, b_category = #{bCategory} where b_idx = #{bIdx}")
	int updateInfoBoard(InfoBoard infoBoard);
	
	//게시글 삭제하기
	@Update("update tb_inf_qst_brd set b_isdel = 1 where b_idx = #{bIdx} and apartment_idx = #{apartmentIdx}")
	int deleteInfoBoard(@Param(value = "bIdx") String bIdx,@Param(value = "apartmentIdx") String apartmentIdx);

	//게시글 비공개처리
	@Update("update tb_inf_qst_brd set b_isprivate = 1 where b_idx = #{bIdx}")
	int updateInfoPrivate(String bIdx);
	
	////////////////////////////
	//정보게시판 댓글관련
	
	//댓글 업로드하기
	@Insert("insert into tb_inf_qst_brd_comment(c_idx,b_idx,c_content,c_writer,generation_idx)"
			+ "values(sc_info_cmt_idx.nextval,#{bIdx},#{cContent},#{cWriter},#{generationIdx})")
	int insertInfoCmt(InfoCmt infoCmt);
	
	//댓글리스트 뽑아오기
	@Select("select * from tb_inf_qst_brd_comment where b_idx = #{bIdx} and c_isdel = 0")
	List<InfoCmt> selectInfoCmtList(String bIdx); 
	
	//댓글 갯수 뽑아오기
	@Select("select count(*) from tb_inf_qst_brd_comment where b_idx = #{bIdx} and c_isdel = 0")
	int selectInfoCmtcnt(String bIdx);
	
	//댓글 수정하기
	@Update("update tb_inf_qst_brd_comment set c_content = #{cContent} where c_idx = #{cIdx} and b_idx = #{bIdx}")
	int updateInfoCmt(InfoCmt infoCmt);
	
	//댓글 삭제하기
	@Update("update tb_inf_qst_brd_comment set c_isdel = 1 where c_idx = #{cIdx}")
	int deleteInfoCmt(@Param(value = "cIdx")String cIdx);
	
	//댓글 비공개처리
	@Update("update tb_inf_qst_brd_comment set c_isprivate = 1 where c_idx = #{cIdx}")
	int UpdateInfoCmtprivate(@Param(value = "cIdx")String cIdx);
	
	

}
