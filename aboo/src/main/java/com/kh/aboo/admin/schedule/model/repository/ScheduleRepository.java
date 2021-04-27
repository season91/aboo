package com.kh.aboo.admin.schedule.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.aboo.admin.schedule.model.vo.Schedule;
import com.kh.aboo.board.info.model.vo.InfoBoard;

@Mapper
public interface ScheduleRepository {
	
	//일정추가하기
	@Insert("insert into tb_schedule (schedule_idx, apartment_idx, schedule_con, schedule_sdate, schedule_edate)"
			+ "values(sc_schedule_idx.nextval,#{apartmentIdx},#{scheduleCon},#{scheduleSdate},#{scheduleEdate})")
	int insertSchedule(Schedule schedule);
	
	//페이징 처리된 스케쥴 목록
	List<Schedule> selectScheduleList(Map<String,Object> searchMap);
	
	//달력에 보여질 일정목록
	@Select("select * from tb_schedule where is_leave= 0 and apartment_idx = #{apartmentIdx}")
	List<Schedule> selectScheduleListForCalendar(String apartmentIdx);
	
	//일정 갯수 가져오기
	int selectScheduleCnt(Map<String,Object> searchMap);
	
	//아파트명 불러오기
	@Select("select apartment_name from tb_apartment where apartment_idx = #{apartmentIdx}")
	String selectAptNameByIdx(String apartmentIdx);
	
	//일정 수정
	@Update("update tb_schedule set schedule_con = #{scheduleCon},schedule_sdate = #{scheduleSdate},schedule_edate = #{scheduleEdate} where schedule_idx=#{scheduleIdx}")
	int updateSchedule(Schedule schedule);
	
	//일정 삭제
	@Update("update tb_schedule set is_leave = 1 where schedule_idx = #{scheduleIdx}")
	int deleteSchedule(String scheduleIdx);
	
	//admin index에 보여질 월별 일정내역
	@Select("select * from tb_schedule where extract(month from sysdate) = extract(month from schedule_sdate) and is_leave = 0 and apartment_idx = #{apartmentIdx}")
	List<Schedule> selectScheduleByMonth(String apartmentIdx);

}
