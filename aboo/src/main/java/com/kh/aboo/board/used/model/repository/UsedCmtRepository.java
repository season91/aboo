package com.kh.aboo.board.used.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.aboo.board.used.model.vo.UsedCmt;

@Mapper
public interface UsedCmtRepository {


	@Insert("insert into TB_USED_COMMENTS(USED_CMT_IDX, USED_IDX, USED_CMT_WRITER, USED_CMT_CONTENT, GENERATION_IDX) values(SC_USED_CMT_IDX.nextval, #{usedIdx}, #{usedCmtWriter}, #{usedCmtContent}, #{generationIdx})")
	int insertUsedBrdCmtUpload(UsedCmt usedCmt);
	
	@Select("select * from TB_USED_COMMENTS where USED_IDX = #{usedIdx} and IS_DLE = 0")
	List<UsedCmt> selectUsedBrdCmt(String usedIdx);

	@Select("select count(*) from TB_USED_COMMENTS where USED_IDX = #{usedIdx} and IS_DLE = 0 ")
	int selectUsedBrdCmtCnt(String usedIdx);
	
	
	@Update("update TB_USED_COMMENTS set USED_CMT_CONTENT = #{usedCmtContent} where USED_CMT_IDX = #{usedCmtIdx}")
	int updateUsedBrdCmt(UsedCmt usedCmt);
	
	@Update("update TB_USED_COMMENTS set IS_DLE = 1 where USED_CMT_IDX = #{usedCmtIdx}")
	int updateUsedBrdCmtDelete(String usedCmtIdx);
	
	@Update("update TB_USED_COMMENTS set IS_PRIVATE = 1 where USED_CMT_IDX = #{usedCmtIdx}")
	int updateUsedBrdCmtPrivate(String usedCmtIdx);
}
