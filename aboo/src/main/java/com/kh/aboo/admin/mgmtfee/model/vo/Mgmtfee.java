package com.kh.aboo.admin.mgmtfee.model.vo;

import java.sql.Date;

public class Mgmtfee {
	private String mgmtfeeIdx;
	private String apartmentIdx; //아파트번호
	private String generationIdx; //세대관리번호
	private String gnrlMgmtFee; //일반관리비
	private String cleanFee; // 청소비
	private String elvtrMnfee; //승강기 유지비
	private String genElctr; //세대전기료
	private String comonElctr; //공동전기료
	private String genWater; //세대수도료
	private String sewer; //하수도료
	private String expenses; //경비비
	private String genReduction; //세대감면액
	private String periodPayment; //납기내 금액
	private Date dueDate; //납기일
	private Date mgmtStartDate; // 관리시작일
	private Date mgmtEndDate; // 관리종료일
	private Date mgmtWriteDate; //관리비 작성일
	private int isPayment; //결제여부
	private int isOverdue; //연체여부
	public void setMgmtfeeIdx(String mgmtfeeIdx) {
		this.mgmtfeeIdx = mgmtfeeIdx;
	}

	public void setApartmentIdx(String apartmentIdx) {
		this.apartmentIdx = apartmentIdx;
	}

	public void setGenerationIdx(String generationIdx) {
		this.generationIdx = generationIdx;
	}

	public void setGnrlMgmtFee(String gnrlMgmtFee) {
		this.gnrlMgmtFee = gnrlMgmtFee;
	}

	public void setCleanFee(String cleanFee) {
		this.cleanFee = cleanFee;
	}

	public void setElvtrMnfee(String elvtrMnfee) {
		this.elvtrMnfee = elvtrMnfee;
	}

	public void setGenElctr(String genElctr) {
		this.genElctr = genElctr;
	}

	public void setComonElctr(String comonElctr) {
		this.comonElctr = comonElctr;
	}

	public void setGenWater(String genWater) {
		this.genWater = genWater;
	}

	public void setSewer(String sewer) {
		this.sewer = sewer;
	}

	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}

	public void setGenReduction(String genReduction) {
		this.genReduction = genReduction;
	}

	public void setPeriodPayment(String periodPayment) {
		this.periodPayment = periodPayment;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setMgmtStartDate(Date mgmtStartDate) {
		this.mgmtStartDate = mgmtStartDate;
	}

	public void setMgmtEndDate(Date mgmtEndDate) {
		this.mgmtEndDate = mgmtEndDate;
	}

	public void setMgmtWriteDate(Date mgmtWriteDate) {
		this.mgmtWriteDate = mgmtWriteDate;
	}

	public void setIsPayment(int isPayment) {
		this.isPayment = isPayment;
	}

	public void setIsOverdue(int isOverdue) {
		this.isOverdue = isOverdue;
	}

	public String getApartmentIdx() {
		return apartmentIdx;
	}

	public String getPeriodPayment() {
		return periodPayment;
	}
	public String getMgmtfeeIdx() {
		return mgmtfeeIdx;
	}
	public String getGenerationIdx() {
		return generationIdx;
	}
	public String getGnrlMgmtFee() {
		return gnrlMgmtFee;
	}
	public String getCleanFee() {
		return cleanFee;
	}
	public String getElvtrMnfee() {
		return elvtrMnfee;
	}
	public String getGenElctr() {
		return genElctr;
	}
	public String getComonElctr() {
		return comonElctr;
	}
	public String getGenWater() {
		return genWater;
	}
	public String getSewer() {
		return sewer;
	}
	public String getExpenses() {
		return expenses;
	}
	public String getGenReduction() {
		return genReduction;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public Date getMgmtStartDate() {
		return mgmtStartDate;
	}
	public Date getMgmtEndDate() {
		return mgmtEndDate;
	}
	public Date getMgmtWriteDate() {
		return mgmtWriteDate;
	}
	public int getIsPayment() {
		return isPayment;
	}
	public int getIsOverdue() {
		return isOverdue;
	}

	@Override
	public String toString() {
		return "Mgmtfee [mgmtfeeIdx=" + mgmtfeeIdx + ", apartmentIdx=" + apartmentIdx + ", generationIdx="
				+ generationIdx + ", gnrlMgmtFee=" + gnrlMgmtFee + ", cleanFee="
				+ cleanFee + ", elvtrMnfee=" + elvtrMnfee + ", genElctr=" + genElctr + ", comonElctr=" + comonElctr
				+ ", genWater=" + genWater + ", sewer=" + sewer + ", expenses=" + expenses + ", genReduction="
				+ genReduction + ", periodPayment=" + periodPayment + ", dueDate=" + dueDate + ", mgmtStartDate="
				+ mgmtStartDate + ", mgmtEndDate=" + mgmtEndDate + ", mgmtWriteDate=" + mgmtWriteDate + ", isPayment="
				+ isPayment + ", isOverdue=" + isOverdue + "]";
	}
}