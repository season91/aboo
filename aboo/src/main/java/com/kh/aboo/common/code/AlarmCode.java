package com.kh.aboo.common.code;

public enum AlarmCode {
	
	ADD_SCHEDULE("일정이 추가되었습니다."),
	ADD_VOTE("투표가 추가되었습니다."),
	ADD_MGMTFEE("관리비가 고지서가 추가되었습니다."),
	PAY_MGMTFEE("관리비가 결제되었습니다."),
	ADD_CAR("차량이 등록되었습니다.");
	
	
	private String alarmCode;
	
	AlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}
	
	//alarmCode getter
	public String toString() {
		return alarmCode;
	}

}
