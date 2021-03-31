package com.kh.aboo.admin.schedule.model.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.kh.aboo.admin.schedule.model.vo.Schedule;

@Mapper
public interface ScheduleRepository {
	
	@Insert("insert into tb_schedule (schedule_idx, apartment_idx, schedule_con, schedule_sdate, schedule_edate)"
			+ "values(sc_schedule_idx.nextval,#{apartmentIdx},#{scheduleCon},#{scheduleSdate},#{scheduleEdate})")
	int insertSchedule(Schedule schedule);

}
