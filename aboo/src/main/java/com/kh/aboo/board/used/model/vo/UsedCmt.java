package com.kh.aboo.board.used.model.vo;

public class UsedCmt {

	private String usedCmtIdx;
	private String usedIdx;
	private String usedCmtWriter;
	private String usedCmtContent;
	private String usedCmtRegDate;
	private String isDel;
	private String isPrivate;
	private String generationIdx;
	

	public UsedCmt() {

	}


	public UsedCmt(String usedCmtIdx, String usedIdx, String usedCmtWriter, String usedCmtContent,
			String usedCmtRegDate, String isDel, String isPrivate, String generationIdx) {
		super();
		this.usedCmtIdx = usedCmtIdx;
		this.usedIdx = usedIdx;
		this.usedCmtWriter = usedCmtWriter;
		this.usedCmtContent = usedCmtContent;
		this.usedCmtRegDate = usedCmtRegDate;
		this.isDel = isDel;
		this.isPrivate = isPrivate;
		this.generationIdx = generationIdx;
	}


	public String getUsedCmtIdx() {
		return usedCmtIdx;
	}


	public void setUsedCmtIdx(String usedCmtIdx) {
		this.usedCmtIdx = usedCmtIdx;
	}


	public String getUsedIdx() {
		return usedIdx;
	}


	public void setUsedIdx(String usedIdx) {
		this.usedIdx = usedIdx;
	}


	public String getUsedCmtWriter() {
		return usedCmtWriter;
	}


	public void setUsedCmtWriter(String usedCmtWriter) {
		this.usedCmtWriter = usedCmtWriter;
	}


	public String getUsedCmtContent() {
		return usedCmtContent;
	}


	public void setUsedCmtContent(String usedCmtContent) {
		this.usedCmtContent = usedCmtContent;
	}


	public String getUsedCmtRegDate() {
		return usedCmtRegDate;
	}


	public void setUsedCmtRegDate(String usedCmtRegDate) {
		this.usedCmtRegDate = usedCmtRegDate;
	}


	public String getIsDel() {
		return isDel;
	}


	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}


	public String getIsPrivate() {
		return isPrivate;
	}


	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}


	public String getGenerationIdx() {
		return generationIdx;
	}


	public void setGenerationIdx(String generationIdx) {
		this.generationIdx = generationIdx;
	}


	@Override
	public String toString() {
		return "UsedCmt [usedCmtIdx=" + usedCmtIdx + ", usedIdx=" + usedIdx + ", usedCmtWriter=" + usedCmtWriter
				+ ", usedCmtContent=" + usedCmtContent + ", usedCmtRegDate=" + usedCmtRegDate + ", isDel=" + isDel
				+ ", isPrivate=" + isPrivate + ", generationIdx=" + generationIdx + "]";
	}

}
