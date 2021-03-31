package com.kh.aboo.board.info.model.vo;

public class InfoCmt {
	
	private String cIdx;
	private String bIdx;
	private String cWdate;
	private String cContent;
	private String cWriter;
	private int cIsDel;
	private int cIsPrivate;
	private String generationIdx;
	
	public InfoCmt() {
		
	}

	public InfoCmt(String cIdx, String bIdx, String cWdate, String cContent, String cWriter, int cIsDel, int cIsPrivate,
			String generationIdx) {
		super();
		this.cIdx = cIdx;
		this.bIdx = bIdx;
		this.cWdate = cWdate;
		this.cContent = cContent;
		this.cWriter = cWriter;
		this.cIsDel = cIsDel;
		this.cIsPrivate = cIsPrivate;
		this.generationIdx = generationIdx;
	}

	public String getcIdx() {
		return cIdx;
	}

	public void setcIdx(String cIdx) {
		this.cIdx = cIdx;
	}

	public String getbIdx() {
		return bIdx;
	}

	public void setbIdx(String bIdx) {
		this.bIdx = bIdx;
	}

	public String getcWdate() {
		return cWdate;
	}

	public void setcWdate(String cWdate) {
		this.cWdate = cWdate;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public String getcWriter() {
		return cWriter;
	}

	public void setcWriter(String cWriter) {
		this.cWriter = cWriter;
	}

	public int getcIsDel() {
		return cIsDel;
	}

	public void setcIsDel(int cIsDel) {
		this.cIsDel = cIsDel;
	}

	public int getcIsPrivate() {
		return cIsPrivate;
	}

	public void setcIsPrivate(int cIsPrivate) {
		this.cIsPrivate = cIsPrivate;
	}

	public String getGenerationIdx() {
		return generationIdx;
	}

	public void setGenerationIdx(String generationIdx) {
		this.generationIdx = generationIdx;
	}

	@Override
	public String toString() {
		return "InfoCmt [cIdx=" + cIdx + ", bIdx=" + bIdx + ", cWdate=" + cWdate + ", cContent=" + cContent
				+ ", cWriter=" + cWriter + ", cIsDel=" + cIsDel + ", cIsPrivate=" + cIsPrivate + ", generationIdx="
				+ generationIdx + "]";
	}

}
