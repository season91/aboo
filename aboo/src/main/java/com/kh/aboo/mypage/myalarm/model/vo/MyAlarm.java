package com.kh.aboo.mypage.myalarm.model.vo;

public class MyAlarm {

	private String issueIdx;
	private String generationIdx;
	private String issueContent;
	private String issueDate;
	
	public MyAlarm() {
		
	}

	public MyAlarm(String issueIdx, String generationIdx, String issueContent, String issueDate) {
		super();
		this.issueIdx = issueIdx;
		this.generationIdx = generationIdx;
		this.issueContent = issueContent;
		this.issueDate = issueDate;
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

	@Override
	public String toString() {
		return "MyAlarm [issueIdx=" + issueIdx + ", generationIdx=" + generationIdx + ", issueContent=" + issueContent + ", issueDate=" + issueDate 
				+ "]";
	}
	
	
}
