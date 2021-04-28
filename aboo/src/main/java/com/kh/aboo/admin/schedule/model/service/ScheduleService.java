package com.kh.aboo.admin.schedule.model.service;

import java.util.List;
import java.util.Map;

import com.kh.aboo.admin.schedule.model.vo.Schedule;

public interface ScheduleService {
	
	int insertSchedule(Schedule schedule);
	
	Map<String,Object> selectScheduleList(int currentPage, String apartmentIdx, String standard, String keyword);
	
	String selectAptNameByIdx(String apartmentIdx);
	
	int updateSchedule(Schedule schedule);
	
	int deleteSchedule(String scheduleIdx);
	
	List<Schedule> selectScheduleByMonth(String apartmentIdx);
	
	List<Schedule> selectScheduleListForCalendar(String apartmentIdx);



}
