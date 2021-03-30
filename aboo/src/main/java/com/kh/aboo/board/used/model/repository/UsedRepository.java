package com.kh.aboo.board.used.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.aboo.board.used.model.vo.UsedBrd;
import com.kh.aboo.common.util.file.FileVo;

@Mapper
public interface UsedRepository {

	List<UsedBrd> selectUsedBrdList(Map<String, Object> usedMap);

	@Select("select count(*) from TB_USED_BRD")
	int selectUsedBrdCnt();

	@Select("select * from TB_USED_BRD where USED_IDX = #{usedIdx} and IS_DEL = 0")
	UsedBrd selectUsedDetail(String usedIdx);

	
	@Update("update TB_USED_BRD set IS_PRIVATE = 1 where USED_IDX = #{usedIdx}")
	int updateUsedPrivate(String usedIdx);
	
	@Update("update TB_USED_BRD set IS_DEL = 1 where USED_IDX = #{usedIdx}")
	int updateUsedDelete(String usedIdx);
	
	@Select("select * from TB_USED_BRD where USED_IDX = #{usedIdx}")
	UsedBrd selectUsedIdx(String usedIdx);

	//게시글 업로드
	@Insert("insert into TB_USED_BRD(USED_IDX,APARTMENT_IDX,USED_TITLE,USED_CONTENT,USED_WRITER,GENERATION_IDX)"
			+ " values(SC_USED_IDX.nextval, #{apartmentIdx},#{usedTitle},#{usedContent},#{usedWriter},#{generationIdx})")
	int insertUsedBrd(UsedBrd usedBrd);

	//게시글 사진 업로드
	int insertUsedBrdFile(FileVo file);
	
	@Select("select * from tb_file where type_idx = #{usedIdx}")
	List<FileVo> selectFileWithusedIdx(String usedIdx);


}
