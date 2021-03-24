package com.kh.aboo.user.generation.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.user.generation.model.vo.Generation;

@Mapper
public interface GenerationRepository {	
	
	@Select("select * from TB_GENERATION where ID = #{Id} and IS_DEL = 0")
	public Generation selectGenerationForAuth(String Id);

}

