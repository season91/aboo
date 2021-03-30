package com.kh.aboo.board.used.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.aboo.board.used.model.vo.UsedBrd;

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
}
