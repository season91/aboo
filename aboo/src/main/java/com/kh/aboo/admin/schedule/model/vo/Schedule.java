package com.kh.aboo.admin.schedule.model.vo;

import java.sql.Date;

public class Schedule {
	
	private String scheduleIdx;
	private String apartmentIdx;
	private String scheduleCon;
	private Date scheduleSdate;
	private Date scheduleEdate;
	private int isLeave;
	
	public Schedule() {
		
	}

	public Schedule(String scheduleIdx, String apartmentIdx, String scheduleCon, Date scheduleSdate, Date scheduleEdate,
			int isLeave) {
		super();
		this.scheduleIdx = scheduleIdx;
		this.apartmentIdx = apartmentIdx;
		this.scheduleCon = scheduleCon;
		this.scheduleSdate = scheduleSdate;
		this.scheduleEdate = scheduleEdate;
		this.isLeave = isLeave;
	}

	public String getScheduleIdx() {
		return scheduleIdx;
	}

	public void setScheduleIdx(String scheduleIdx) {
		this.scheduleIdx = scheduleIdx;
	}

	public String getApartmentIdx() {
		return apartmentIdx;
	}

	public void setApartmentIdx(String apartmentIdx) {
		this.apartmentIdx = apartmentIdx;
	}

	public String getScheduleCon() {
		return scheduleCon;
	}

	public void setScheduleCon(String scheduleCon) {
		this.scheduleCon = scheduleCon;
	}

	public Date getScheduleSdate() {
		return scheduleSdate;
	}

	public void setScheduleSdate(Date scheduleSdate) {
		this.scheduleSdate = scheduleSdate;
	}

	public Date getScheduleEdate() {
		return scheduleEdate;
	}

	public void setScheduleEdate(Date scheduleEdate) {
		this.scheduleEdate = scheduleEdate;
	}

	public int getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(int isLeave) {
		this.isLeave = isLeave;
	}

	@Override
	public String toString() {
		return "Schedule [scheduleIdx=" + scheduleIdx + ", apartmentIdx=" + apartmentIdx + ", scheduleCon="
				+ scheduleCon + ", scheduleSdate=" + scheduleSdate + ", scheduleEdate=" + scheduleEdate + ", isLeave="
				+ isLeave + "]";
	}

}
