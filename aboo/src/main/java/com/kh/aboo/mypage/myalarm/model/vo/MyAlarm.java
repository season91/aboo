package com.kh.aboo.mypage.myalarm.model.vo;

public class MyAlarm {

	private String issueIdx;
	private String generationIdx;
	private String issueType;
	private String issueContent;
	private String issueDate;
	private int issueIsChecked;
	
	public MyAlarm() {
		
	}

	public MyAlarm(String issueIdx, String generationIdx, String issueType, String issueContent, String issueDate,
			int issueIsChecked) {
		super();
		this.issueIdx = issueIdx;
		this.generationIdx = generationIdx;
		this.issueType = issueType;
		this.issueContent = issueContent;
		this.issueDate = issueDate;
		this.issueIsChecked = issueIsChecked;
	}

	public String getIssueIdx() {
		return issueIdx;
	}

	public void setIssueIdx(String issueIdx) {
		this.issueIdx = issueIdx;
	}

	public String getGenerationIdx() {
		return generationIdx;
	}

	public void setGenerationIdx(String generationIdx) {
		this.generationIdx = generationIdx;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getIssueContent() {
		return issueContent;
	}

	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public int getIssueIsChecked() {
		return issueIsChecked;
	}

	public void setIssueIsChecked(int issueIsChecked) {
		this.issueIsChecked = issueIsChecked;
	}

	@Override
	public String toString() {
		return "MyAlarm [issueIdx=" + issueIdx + ", generationIdx=" + generationIdx + ", issueType=" + issueType
				+ ", issueContent=" + issueContent + ", issueDate=" + issueDate + ", issueIsChecked=" + issueIsChecked
				+ "]";
	}
	
	
}
