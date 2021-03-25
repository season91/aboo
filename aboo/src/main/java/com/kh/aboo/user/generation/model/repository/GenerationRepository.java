package com.kh.aboo.user.generation.model.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.user.generation.model.vo.Generation;

@Mapper
public interface GenerationRepository {	
	
	@Select("select * from TB_GENERATION where ID = #{Id} and IS_DEL = 0")
	public Generation selectGenerationForAuth(String Id);
	
	@Insert("insert into TB_GENERATION(GENERATION_IDX,APARTMENT_IDX,ID,PASSWORD,BUILDING,NUM) values(SC_GENERATION_IDX.nextval,#{apartmentIdx},#{id},#{password},#{building},#{num})")
	public int insertGeneration(Generation generation);
	
	//메일보내기 전에 있는 세대인지 체크
	@Select("select * from TB_GENERATION where BUILDING = #{building} and NUM = #{num} and EMAIL = #{email}")
	public Generation selectFindId(Generation generation);
	
	//메일보내기 전에 있는 세대인지 체크
	@Select("select * from TB_GENERATION where id = #{id} and email = #{email}")
	public Generation selectFindPassword(Generation generation);

}
