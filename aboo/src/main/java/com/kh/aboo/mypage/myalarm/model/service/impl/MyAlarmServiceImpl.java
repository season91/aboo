package com.kh.aboo.mypage.myalarm.model.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kh.aboo.mypage.myalarm.model.repository.MyAlarmRepository;
import com.kh.aboo.mypage.myalarm.model.service.MyAlarmService;
import com.kh.aboo.mypage.myalarm.model.vo.MyAlarm;


@Service
public class MyAlarmServiceImpl implements MyAlarmService{
	
	private final MyAlarmRepository myAlarmRepository;
	
	public MyAlarmServiceImpl(MyAlarmRepository myAlarmRepository){
		this.myAlarmRepository = myAlarmRepository;
	}

	@Override
	public void insertAptAlarm(String issueContent, String apartmentIdx) {
		
		int res = myAlarmRepository.insertAptAlarm(issueContent, apartmentIdx);
		
		if(res > 0) {
			System.out.println("알람등록");
		}else {
			System.out.println("등록실패");
		}
	}

	@Override
	public void insertPvAlarm(String issueContent, String generationIdx) {
		
		int res = myAlarmRepository.insertPvAlarm(issueContent, generationIdx);
		
		if(res > 0) {
			System.out.println("알람등록");
		}else {
			System.out.println("등록실패");
		}
		
	}

	@Override
	public List<MyAlarm> selectIssue(String generationIdx, String apartmentIdx) {
	
		return myAlarmRepository.selectIssue(generationIdx, apartmentIdx);
	}
	

}
