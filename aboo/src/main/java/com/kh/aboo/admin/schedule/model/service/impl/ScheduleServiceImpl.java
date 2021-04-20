package com.kh.aboo.admin.schedule.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.aboo.admin.schedule.model.repository.ScheduleRepository;
import com.kh.aboo.admin.schedule.model.service.ScheduleService;
import com.kh.aboo.admin.schedule.model.vo.Schedule;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.user.generation.model.vo.Generation;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	
	private final ScheduleRepository scheduleRepository;
	
	public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	@Override
	public int insertSchedule(Schedule schedule) {
		
		
		
		return scheduleRepository.insertSchedule(schedule);
	}


	@Override
	public Map<String, Object> selectScheduleList(int currentPage,String apartmentIdx, String standard, String keyword) {

		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		searchMap.put("apartmentIdx", apartmentIdx);
		searchMap.put("searchType", standard);
		searchMap.put("keyword", keyword);
		
		Paging paging = Paging.builder()
				.currentPage(currentPage)
				.blockCnt(5)
				.cntPerPage(10)
				.type("schedule")
				.total(scheduleRepository.selectScheduleCnt(searchMap))
				.build();
		System.out.println(paging.toString());

		searchMap.put("paging", paging);
		searchMap.put("schedule", scheduleRepository.selectScheduleList(searchMap));
					
		return searchMap;
	}

	@Override
	public String selectAptNameByIdx(String apartmentIdx) {
		
		String aptName = scheduleRepository.selectAptNameByIdx(apartmentIdx);
		
		return aptName;
	}

	@Override
	public int updateSchedule(Schedule schedule) {

		return scheduleRepository.updateSchedule(schedule);
	}

	@Override
	public int deleteSchedule(String scheduleIdx) {
		
		return scheduleRepository.deleteSchedule(scheduleIdx);
	}

	@Override
	public List<Schedule> selectScheduleByMonth() {
		
		return scheduleRepository.selectScheduleByMonth();
	}

	@Override
	public List<Schedule> selectScheduleListForCalendar(String apartmentIdx) {
		
		return scheduleRepository.selectScheduleListForCalendar(apartmentIdx);
	}
	
	

}
