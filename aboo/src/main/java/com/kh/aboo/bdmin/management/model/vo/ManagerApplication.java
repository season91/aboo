package com.kh.aboo.bdmin.management.model.vo;

import java.sql.Date;

public class ManagerApplication {

	private String managerApplicationIdx;
	private String apartmentName;
	private String apartmentAddress;
	private String id;
	private String password;
	private Date approvalDate;
	private String name;
	private String email;
	private String tell;
	private Date birth;
	private String isApproval;

	public ManagerApplication() {
	}

	public ManagerApplication(String managerApplicationIdx, String apartmentName, String apartmentAddress, String id,
			String password, Date approvalDate, String name, String email, String tell, Date birth, String isApproval) {
		super();
		this.managerApplicationIdx = managerApplicationIdx;
		this.apartmentName = apartmentName;
		this.apartmentAddress = apartmentAddress;
		this.id = id;
		this.password = password;
		this.approvalDate = approvalDate;
		this.name = name;
		this.email = email;
		this.tell = tell;
		this.birth = birth;
		this.isApproval = isApproval;
	}

	public String getManagerApplicationIdx() {
		return managerApplicationIdx;
	}

	public void setManagerApplicationIdx(String managerApplicationIdx) {
		this.managerApplicationIdx = managerApplicationIdx;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getApartmentAddress() {
		return apartmentAddress;
	}

	public void setApartmentAddress(String apartmentAddress) {
		this.apartmentAddress = apartmentAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getIsApproval() {
		return isApproval;
	}

	public void setIsApproval(String isApproval) {
		this.isApproval = isApproval;
	}

	@Override
	public String toString() {
		return "ManagerApplication [managerApplicationIdx=" + managerApplicationIdx + ", apartmentName=" + apartmentName
				+ ", apartmentAddress=" + apartmentAddress + ", id=" + id + ", password=" + password + ", approvalDate="
				+ approvalDate + ", name=" + name + ", email=" + email + ", tell=" + tell + ", birth=" + birth
				+ ", isApproval=" + isApproval + "]";
	}

}
