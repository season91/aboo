package com.kh.aboo.admin.schedule.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.admin.schedule.model.vo.Schedule;
import com.kh.aboo.board.info.model.vo.InfoBoard;

@Mapper
public interface ScheduleRepository {
	
	//일정추가하기
	@Insert("insert into tb_schedule (schedule_idx, apartment_idx, schedule_con, schedule_sdate, schedule_edate)"
			+ "values(sc_schedule_idx.nextval,#{apartmentIdx},#{scheduleCon},#{scheduleSdate},#{scheduleEdate})")
	int insertSchedule(Schedule schedule);
	
	//페이징 처리된 스케쥴 목록
	List<InfoBoard> selectScheduleList(@Param(value = "queryStart") int queryStart, @Param(value = "queryEnd") int queryEnd,@Param(value = "apartmentIdx")  String apartmentIdx);
	
	//일정 갯수 가져오기
	@Select("select count(*) from tb_schedule where apartment_idx = #{apartmentIdx}")
	int selectScheduleCnt(String apartmentIdx);
	
	//아파트명 불러오기
	@Select("select apartment_name from tb_apartment where apartment_idx = #{apartmentIdx}")
	String selectAptNameByIdx(String apartmentIdx);

}
