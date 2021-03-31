package com.kh.aboo.mypage.mymgmtfee.model.vo;

import java.sql.Date;

public class MgmtfeePayment {
	private String paymentIdx;
	private String mgmtfeeIdx;
	private String paymentMethod;
	private String paymentAmount;
	private Date paymentDate;
	private String paymentState;

	public MgmtfeePayment() {

	}

	public MgmtfeePayment(String paymentIdx, String mgmtfeeIdx, String paymentMethod, String paymentAmount,
			Date paymentDate, String paymentState) {
		super();
		this.paymentIdx = paymentIdx;
		this.mgmtfeeIdx = mgmtfeeIdx;
		this.paymentMethod = paymentMethod;
		this.paymentAmount = paymentAmount;
		this.paymentDate = paymentDate;
		this.paymentState = paymentState;
	}

	public String getPaymentIdx() {
		return paymentIdx;
	}

	public void setPaymentIdx(String paymentIdx) {
		this.paymentIdx = paymentIdx;
	}

	public String getMgmtfeeIdx() {
		return mgmtfeeIdx;
	}

	public void setMgmtfeeIdx(String mgmtfeeIdx) {
		this.mgmtfeeIdx = mgmtfeeIdx;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentState() {
		return paymentState;
	}

	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}

	@Override
	public String toString() {
		return "MgmtfeePayment [paymentIdx=" + paymentIdx + ", mgmtfeeIdx=" + mgmtfeeIdx + ", paymentMethod="
				+ paymentMethod + ", paymentAmount=" + paymentAmount + ", paymentDate=" + paymentDate
				+ ", paymentState=" + paymentState + "]";
	}

}
