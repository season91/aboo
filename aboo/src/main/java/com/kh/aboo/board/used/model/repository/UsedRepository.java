package com.kh.aboo.board.used.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.used.model.vo.UsedBrd;
import com.kh.aboo.common.util.file.FileVo;

@Mapper
public interface UsedRepository {

	List<UsedBrd> selectUsedBrdList(Map<String, Object> usedMap);

	int selectUsedBrdCnt(Map<String, Object> searchMap);

	@Select("select * from TB_USED_BRD where USED_IDX = #{usedIdx} and IS_DEL = 0")
	UsedBrd selectUsedDetail(String usedIdx);

	@Update("update TB_USED_BRD set IS_PRIVATE = 1 where USED_IDX = #{usedIdx}")
	int updateUsedPrivate(String usedIdx);

	@Update("update TB_USED_BRD set IS_DEL = 1 where USED_IDX = #{usedIdx}")
	int updateUsedDelete(String usedIdx);

	@Update("update TB_FILE set IS_DEL = 1 where TYPE_IDX = #{usedIdx}")
	int updateUsedFileDelete(String usedIdx);

	@Select("select * from TB_USED_BRD where USED_IDX = #{usedIdx}")
	UsedBrd selectUsedIdx(String usedIdx);

	// 게시글 업로드
	@Insert("insert into TB_USED_BRD(USED_IDX,APARTMENT_IDX,USED_TITLE,USED_CONTENT,USED_WRITER,GENERATION_IDX)"
			+ " values('u'||SC_USED_IDX.nextval, #{apartmentIdx},#{usedTitle},#{usedContent},#{usedWriter},#{generationIdx})")
	int insertUsedBrd(UsedBrd usedBrd);

	// 게시글 사진 업로드
	int insertUsedBrdFile(FileVo file);

	@Select("select * from tb_file where type_idx = #{usedIdx}")
	FileVo selectFileWithusedIdx(String usedIdx);

	// 게시물 수정
	@Update("update TB_USED_BRD set USED_TITLE = #{usedTitle}, USED_CONTENT = #{usedContent}, USED_REG_DATE = SYSDATE, IS_TRNSC = #{isTrnsc} where USED_IDX =#{usedIdx} ")
	int updateUsedBrdModify(UsedBrd usedBrd);

	int updateUsedBrdFileModify(Map<String, Object> commandMap);

	
	@Select("select count(*) from TB_USED_BRD where USED_REG_DATE = to_char(sysdate,'YYYYMMDD')")
	int selectUsedBrdTodayCnt();
	
	@Select("select count(*) from TB_INF_QST_BRD where B_WDATE = to_char(sysdate,'YYYYMMDD')")
	int selectInfoBrdTodayCnt();
	
	@Select("select count(*) from TB_INTERIOR_BRD where INT_REG_DATE = to_char(sysdate,'YYYYMMDD')")
	int selectIntBrdTodayCnt();

}
