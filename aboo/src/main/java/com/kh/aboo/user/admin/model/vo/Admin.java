package com.kh.aboo.user.admin.model.vo;

public class Admin {

	private String adminIdx;
	private String id;
	private String password;
	private String isDel;

	public Admin() {

	}

	public Admin(String adminIdx, String id, String password, String isDel) {
		super();
		this.adminIdx = adminIdx;
		this.id = id;
		this.password = password;
		this.isDel = isDel;
	}

	public String getAdminIdx() {
		return adminIdx;
	}

	public void setAdminIdx(String adminIdx) {
		this.adminIdx = adminIdx;
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

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "Admin [adminIdx=" + adminIdx + ", id=" + id + ", password=" + password + ", isDel=" + isDel + "]";
	}

}
