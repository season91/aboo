package com.kh.aboo.admin.schedule.model.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.aboo.admin.schedule.model.repository.ScheduleRepository;
import com.kh.aboo.admin.schedule.model.service.ScheduleService;
import com.kh.aboo.admin.schedule.model.vo.Schedule;
import com.kh.aboo.common.util.paging.Paging;

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
	public Map<String, Object> selectScheduleList(int currentPage, String apartmentIdx) {

		Paging paging = Paging.builder()
				.currentPage(currentPage)
				.blockCnt(5)
				.cntPerPage(10)
				.type("schedule")
				.total(scheduleRepository.selectScheduleCnt(apartmentIdx))
				.build();
		System.out.println(paging.toString());
	
		Map<String,Object> commandMap = new HashMap<String, Object>();
		commandMap.put("paging", paging);
		commandMap.put("schedule", scheduleRepository.selectScheduleList(paging.getQueryStart(), paging.getQueryEnd(),apartmentIdx));
					
		return commandMap;
	}

	@Override
	public String selectAptNameByIdx(String apartmentIdx) {
		
		String aptName = scheduleRepository.selectAptNameByIdx(apartmentIdx);
		
		return aptName;
	}
	
	

}
