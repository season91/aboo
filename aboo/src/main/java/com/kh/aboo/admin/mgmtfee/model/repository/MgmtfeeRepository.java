package com.kh.aboo.admin.mgmtfee.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.user.generation.model.vo.Generation;

@Mapper
public interface MgmtfeeRepository {

	// 관리비 DB 넣기 위한 세대관리번호 
	@Select("select * from TB_GENERATION where id = #{generationInfo}")
	public Generation selectGenerationIdx(String generationInfo);
	
	// 관리비 DB에 넣기
	public int insertMgmtfee(Mgmtfee mgmtfee);
	
	// 엑셀 양식 다운로드를 위한 세대전체정보
	@Select("select * from tb_generation where apartment_idx = #{apartmentIdx}")
	public List<Generation> selectGeneration();

}
