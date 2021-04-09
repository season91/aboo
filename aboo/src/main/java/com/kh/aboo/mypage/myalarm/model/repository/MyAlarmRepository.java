package com.kh.aboo.mypage.myalarm.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.mypage.myalarm.model.vo.MyAlarm;
import com.kh.aboo.user.generation.model.vo.Generation;

@Mapper
public interface MyAlarmRepository {
	
	//개인관련 알람
	@Insert("insert into tb_issue(issue_idx,generation_idx,issue_content,apartment_idx) "
			+ "values(sc_issue_idx.nextval,#{generationIdx},#{issueContent},0)")
	int insertPvAlarm(@Param("issueContent") String issueContent,@Param("generationIdx") String generationIdx);

	//아파트관련 알람
	@Insert("insert into tb_issue(issue_idx,generation_idx,issue_content,apartment_idx) "
			+ "values(sc_issue_idx.nextval,0,#{issueContent},#{apartmentIdx})")
	int insertAptAlarm(@Param("issueContent") String issueContent,@Param("apartmentIdx") String apartmentIdx);
	
	//알람목록 불러오기
	@Select("select * from tb_issue where generation_idx = #{generationIdx} or apartment_idx = #{apartmentIdx} order by issue_date desc ")
	List<MyAlarm> selectIssue(@Param("generationIdx") String generationIdx,@Param("apartmentIdx") String apartmentIdx);
	
	//generation 불러오기
	@Select("select * from tb_generation where generation_idx = #{generationIdx} and is_del = 0")
	Generation selectGenerationByIdx(String generationIdx);
	
	//아파트idx로 불러오기
	@Select("select * from tb_generation where apartment_idx=#{apartmentIdx} and is_del = 0")
	List<Generation> selectGenerationByApt(String apartmentIdx);
}
