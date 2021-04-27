package com.kh.aboo.common.code;

public enum ConfigCode {

	DOMAIN("http://localhost:9393"),
	QR_DOMAIN("http://localhost:9393"),
	// EMAIL("choayoung91@naver.com"),
	EMAIL("suny10312@naver.com"),
	// 파일저장을 내부에 해버리면 배포할때 다 날라간다, 꼭 외부 파일경로 지정해서 업로드해주자!
	//UPLOAD_PATH("C:\\CODE\\kh-aboo\\aboo\\upload\\");
	// UPLOAD_PATH("C:\\CODE\\kh-aboo\\aboo\\upload\\"); //아영 맥
	// 아영 QR코드 관련 경로
	//QRCODE_PATH("/resources/ckstorage/"),
	//QRCODE_PULLPATH("C:\\CODE\\kh-aboo-dev\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\aboo\\resources\\ckstorage\\"),
	UPLOAD_PATH("C:\\final\\abooWorkspace\\aboo\\resources\\upload\\"), //선영 윈도우
	//QRCODE_PATH("C:\\final\\abooWorkspace\\aboo\\resources\\qrcode\\"); //선영 윈도우 qr
	QRCODE_PATH("C:\\CODE\\kh-aboo-dev\\aboo\\resources\\qrcode\\"); //아영 윈도우, 저장할때 쓰는 경로
	
	public String desc;

	private ConfigCode(String desc) {
		// TODO Auto-generated constructor stub
		this.desc = desc;
	}

	public String toString() {
		return desc;
	}

}
