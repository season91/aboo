package com.kh.aboo.mypage.myalarm.model.service;

import java.util.List;

import org.springframework.ui.Model;

import com.kh.aboo.mypage.myalarm.model.vo.MyAlarm;

public interface MyAlarmService {
	
	void insertAptAlarm(String issueContent,String apartmentIdx);
	
	void insertPvAlarm(String issueContent,String generationIdx);
	
	List<MyAlarm> selectIssue(String generationIdx,String apartmentIdx);

}
