package com.kh.aboo.admin.mgmtfee.model.vo;

public class MgmtfeeOverdue {
	
	private String overdueIdx;
	private String mgmtfeeIdx;
	private String periodPayment;
	private String overdueFee;
	private int isPayment;
	
	
	
	public MgmtfeeOverdue() {
		super();
	}



	public MgmtfeeOverdue(String overdueIdx, String mgmtfeeIdx, String periodPayment, String overdueFee,
			int isPayment) {
		super();
		this.overdueIdx = overdueIdx;
		this.mgmtfeeIdx = mgmtfeeIdx;
		this.periodPayment = periodPayment;
		this.overdueFee = overdueFee;
		this.isPayment = isPayment;
	}
	
	

	public String getOverdueIdx() {
		return overdueIdx;
	}

	public void setOverdueIdx(String overdueIdx) {
		this.overdueIdx = overdueIdx;
	}

	public String getMgmtfeeIdx() {
		return mgmtfeeIdx;
	}

	public void setMgmtfeeIdx(String mgmtfeeIdx) {
		this.mgmtfeeIdx = mgmtfeeIdx;
	}

	public String getPeriodPayment() {
		return periodPayment;
	}

	public void setPeriodPayment(String periodPayment) {
		this.periodPayment = periodPayment;
	}

	public String getOverdueFee() {
		return overdueFee;
	}

	public void setOverdueFee(String overdueFee) {
		this.overdueFee = overdueFee;
	}

	public int getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(int isPayment) {
		this.isPayment = isPayment;
	}

	@Override
	public String toString() {
		return "MgmtfeeOverDue [overdueIdx=" + overdueIdx + ", mgmtfeeIdx=" + mgmtfeeIdx + ", periodPayment="
				+ periodPayment + ", overdueFee=" + overdueFee + ", isPayment=" + isPayment + "]";
	}
	
	

}
