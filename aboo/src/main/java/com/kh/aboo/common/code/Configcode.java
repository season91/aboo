package com.kh.aboo.common.code;

public enum Configcode {

	DOMAIN("http://localhost:9393"),
	// EMAIL("choayoung91@naver.com"),
	EMAIL("suny10312@naver.com"),
	// 파일저장을 내부에 해버리면 배포할때 다 날라간다, 꼭 외부 파일경로 지정해서 업로드해주자!
	//UPLOAD_PATH("C:\\CODE\\kh-aboo\\aboo\\upload\\");
	// UPLOAD_PATH("C:\\CODE\\kh-aboo\\aboo\\upload\\"); //아영 맥
	 UPLOAD_PATH("C:\\final\\abooWorkspace\\aboo\\resources\\upload\\"); //선영 윈도우
	public String desc;

	private Configcode(String desc) {
		// TODO Auto-generated constructor stub
		this.desc = desc;
	}

	public String toString() {
		return desc;
	}

}
