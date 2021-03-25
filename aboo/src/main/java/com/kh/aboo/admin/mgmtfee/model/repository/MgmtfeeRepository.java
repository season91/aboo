package com.kh.aboo.admin.mgmtfee.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.user.generation.model.vo.Generation;

@Mapper
public interface MgmtfeeRepository {

	// 관리비 DB 넣기 위한 세대관리번호 
	@Select("select * from TB_GENERATION where building = #{building} and num = #{num} and apartment_idx = #{apartmentIdx}")
	Generation selectGenerationIdx(Generation generationInfo);
	
	// 관리비 DB에 넣기
	int insertMgmtfee(Mgmtfee mgmtfee);
	
	// 아파트번호로 세대정보 받아오기
	@Select("select building from tb_generation where apartment_idx = #{apartmentIdx} order by building asc")
	List<String> selectBuildingByApartmentIdx(String apartmentIdx);
	
	@Select("select num from tb_generation where apartment_idx = #{apartmentIdx} order by building, num  asc")
	List<String> selectNumByApartmentIdx(String apartmentIdx);
	
	//페이징을 위한 개수 확인
	@Select("select count(*) from tb_mgmtfee where apartment_idx = #{apartmentIdx}")
	int selectContentCnt(String apartmentIdx);
	
	// 페이징 화면 구현을 위한 list
	List<Mgmtfee> selectMgmtfeeList(Map<String,Object> generationMap);
	
	// 페이징에 세대정보 넣어야해서 세대번호기준 세대정보 가져온다
	@Select("select * from tb_generation where generation_idx = #{generationIdx}")
	Generation selectGenerationByGenerationIdx(String generationIdx);

}
