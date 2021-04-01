package com.kh.aboo.mypage.myalarm.model.service.impl;

import org.springframework.stereotype.Service;

import com.kh.aboo.mypage.myalarm.model.repository.MyAlarmRepository;
import com.kh.aboo.mypage.myalarm.model.service.MyAlarmService;


@Service
public class MyAlarmServiceImpl implements MyAlarmService{
	
	private final MyAlarmRepository myAlarmRepository;
	
	public MyAlarmServiceImpl(MyAlarmRepository myAlarmRepository){
		this.myAlarmRepository = myAlarmRepository;
	}
	

}
