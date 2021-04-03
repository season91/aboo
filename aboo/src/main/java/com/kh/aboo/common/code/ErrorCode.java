package com.kh.aboo.common.code;

public enum ErrorCode {
	
	SM01("회원 정보를 조회하는 도중 에러가 발생하였습니다.","/login"),
	IM01("회원 정보를 입력하는 도중 에러가 발생하였습니다."),
	UM01("회원정보 수정 중 에러가 발생하였습니다."),
	DM01("회원정보 삭제 중 에러가 발생하였습니다."),
	FILE01("파일 업로드 중 에러가 발생하였습니다."),
	IB01("게시글 작성 중 에러가 발생하였습니다"),
	IF01("파일 업로드 중 에러가 발생하였습니다."),
	AUTH01("해당 페이지에 접근하실 수 없습니다.","/login"),
	AUTH02("이미 인증이 만료된 링크입니다."),
	AUTH06("해당 페이지는 로그인 후 접근 가능합니다","/login"),
	AUTH07("해당 페이지는 로그인 후 접근 가능합니다","/admin/login"),
	MAIL01("메일 발송 중 에러가 발생했습니다."),
	API01("API통신 도중 에러가 발생하였습니다."),
	AUTH04("해당 페이지는 직원만 접근가능합니다.", "/bdmin/login"),
	AUTH05("해당 페이지는 관리자만 접근가능합니다.", "/admin/login"),
	ER01("EXCEL 업로드 중 에러가 발생했습니다.","/admin/mgmtfee"),
	UMGMT01("관리비 수정중 에러가 발생했습니다","/admin/mgmtfee"),
	AUTH03("해당 아파트는 관리대상이 아닙니다.","/admin/index"),
	UMGMT02("관리비 삭제 중 에러가 발생했습니다","/admin/mgmtfee"),
	SMGMT01("관리비 내역 조회중 에러가 발생했습니다.","/admin/mgmtfee"),
	SMGMT02("이미 등록된 고지월입니다. 다시 확인해주세요","/admin/mgmtfee"),
	DMGMT01("관리비 삭제 중 에러가 발생하였습니다.","/admin/mgmtfee"),
	IQR01("QR코드 생성중 에러가 발생하였습니다.","/admin/car"),
	IQR02("2건 다 등록되었거나 중복된 차량번호입니다.","/admin/car"),
	IC01("이미 해당 세대에 등록된 차량번호입니다.", "/admin/car"),
	SC01("등록여부를 확인하세요.", "/admin/car"),
	DC01("차량 삭제 중 에러가 발생했습니다.","/admin/car"),
	IAC01("승인 처리 중 에러 발생","/admin/car/application"),
	SAC01("이미 처리된 신청건 입니다.", "/admin/car/application"),
	DAC01("반려 처리 중 에러가 발생하였습니다.","/admin/car/application"),
	UAC01("처리 상태 업데이트 중 에러 발생","/admin/car/application"),
	IDCHECK01("중복아이디 입니다.","/bdmin/management/adminauthority"),
	CD_404("존재하지 않는 경로입니다."),
	AH01("잘못된 인증 번호입니다","/findid"),
	AH02("존재하지 않는 사용자입니다."),
	AAH01("잘못된 인증 번호입니다","/admin/findid");
	
	//result.jsp를 사용해 띄울 안내문구 
	private String errMsg;
	//result.jsp를 사용해 이동시킬 경로
	private String url = "/index";
	
	// index로 이동시킬 경우 에러메시지만 받는다.
	ErrorCode(String errMsg) {
		this.errMsg = errMsg;
	}
	
	//index이외의 지정페이지로 이동시 url도 같이 받는다.
	ErrorCode(String errMsg, String url) {
		this.errMsg = errMsg;
		this.url = url;
	}
	
	public String errMsg() {
		return errMsg;
	}
	
	public String url() {
		return url;
	}

}
