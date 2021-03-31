package com.kh.aboo.admin.schedule.model.service.impl;

import org.springframework.stereotype.Service;

import com.kh.aboo.admin.schedule.model.repository.ScheduleRepository;
import com.kh.aboo.admin.schedule.model.service.ScheduleService;
import com.kh.aboo.admin.schedule.model.vo.Schedule;

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
	
	

}
