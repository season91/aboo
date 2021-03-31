package com.kh.aboo.admin.car.model.vo;

import java.sql.Date;

public class CarApplication {
	
	private String applicationIdx;
	private String apartmentIdx;
	private String generationIdx;
	private String aplctCarNumber;
	private int isProcess;
	private Date regDate;

	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getApplicationIdx() {
		return applicationIdx;
	}
	public void setApplicationIdx(String applicationIdx) {
		this.applicationIdx = applicationIdx;
	}
	public String getApartmentIdx() {
		return apartmentIdx;
	}
	public void setApartmentIdx(String apartmentIdx) {
		this.apartmentIdx = apartmentIdx;
	}
	public String getGenerationIdx() {
		return generationIdx;
	}
	public void setGenerationIdx(String generationIdx) {
		this.generationIdx = generationIdx;
	}
	public String getAplctCarNumber() {
		return aplctCarNumber;
	}
	public void setAplctCarNumber(String aplctCarNumber) {
		this.aplctCarNumber = aplctCarNumber;
	}
	public int getIsProcess() {
		return isProcess;
	}
	public void setIsProcess(int isProcess) {
		this.isProcess = isProcess;
	}
	@Override
	public String toString() {
		return "CarApplication [applicationIdx=" + applicationIdx + ", apartmentIdx=" + apartmentIdx
				+ ", generationIdx=" + generationIdx + ", aplctCarNumber=" + aplctCarNumber + ", isProcess=" + isProcess
				+ ", regDate=" + regDate + "]";
	}
	
	
}
