package com.kh.aboo.mypage.mymgmtfee.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.user.generation.model.vo.Generation;

@Mapper
public interface MyMgmtfeeRepository {
	
	// 페이징용
	@Select("select * from tb_mgmtfee where generation_idx = #{generationIdx}")
	Mgmtfee selectMgmtfeeByGenerationIdx(String generationIdx);
	
	@Select("select count(*) from tb_mgmtfee where generation_idx = #{generationIdx}")
	int selectContentCnt(String generationIdx);

	List<Mgmtfee> selectMyMgmtfeeList(Map<String, Object> generationMap);
	
	// 관리비 상세 조회 용
	@Select("select * from tb_mgmtfee where mgmtfee_idx =  #{mgmtfeeIdx}")
	Mgmtfee selectMyMgmtfeeByMgmtfeeIdx(String mgmtfeeIdx);
	
	@Select("select extract(year from mgmt_start_date) year, extract(month from mgmt_start_date) month from tb_mgmtfee where mgmtfee_idx=#{mgmtfeeIdx}")
	Map<String,Object> selectMyMgmtfeeDate(String mgmtfeeIdx);
	

}
