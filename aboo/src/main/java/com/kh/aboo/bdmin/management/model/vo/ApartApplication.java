package com.kh.aboo.bdmin.management.model.vo;

public class ApartApplication {

	private String applicationIdx;
	private String aApartmentName;
	private String aApartmentAddress;
	private String aSeparator;
	private String aGenerationCnt;
	private String aParking;
	private String aName;
	private String aTell;
	private String aEmail;
	private int isProcess;

	public String getaParking() {
		return aParking;
	}

	public void setaParking(String aParking) {
		this.aParking = aParking;
	}

	public String getApplicationIdx() {
		return applicationIdx;
	}

	public void setApplicationIdx(String applicationIdx) {
		this.applicationIdx = applicationIdx;
	}

	public String getaApartmentName() {
		return aApartmentName;
	}

	public void setaApartmentName(String aApartmentName) {
		this.aApartmentName = aApartmentName;
	}

	public String getaApartmentAddress() {
		return aApartmentAddress;
	}

	public void setaApartmentAddress(String aApartmentAddress) {
		this.aApartmentAddress = aApartmentAddress;
	}

	public String getaGenerationCnt() {
		return aGenerationCnt;
	}

	public void setaGenerationCnt(String aGenerationCnt) {
		this.aGenerationCnt = aGenerationCnt;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getaTell() {
		return aTell;
	}

	public void setaTell(String aTell) {
		this.aTell = aTell;
	}

	public String getaEmail() {
		return aEmail;
	}

	public void setaEmail(String aEmail) {
		this.aEmail = aEmail;
	}

	public int getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(int isProcess) {
		this.isProcess = isProcess;
	}

	public String getaSeparator() {
		return aSeparator;
	}

	public void setaSeparator(String aSeparator) {
		this.aSeparator = aSeparator;
	}

	@Override
	public String toString() {
		return "ApartApplication [applicationIdx=" + applicationIdx + ", aApartmentName=" + aApartmentName
				+ ", aApartmentAddress=" + aApartmentAddress + ", aSeparator=" + aSeparator + ", aGenerationCnt="
				+ aGenerationCnt + ", aParking=" + aParking + ", aName=" + aName + ", aTell=" + aTell + ", aEmail="
				+ aEmail + ", isProcess=" + isProcess + "]";
	}

}
