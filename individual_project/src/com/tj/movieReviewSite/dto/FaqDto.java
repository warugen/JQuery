package com.tj.movieReviewSite.dto;

import java.sql.Date;

public class FaqDto {
	private int fNo;
	private String aId;
	private String aName;
	private String fTitle;
	private String fContent;
	private int fHit;
	private Date fRdate;
	public FaqDto() {}
	public FaqDto(int fNo, String aId, String aName, String fTitle, String fContent, int fHit, Date fRdate) {
		this.fNo = fNo;
		this.aId = aId;
		this.aName = aName;
		this.fTitle = fTitle;
		this.fContent = fContent;
		this.fHit = fHit;
		this.fRdate = fRdate;
	}
	public int getfNo() {
		return fNo;
	}
	public void setfNo(int fNo) {
		this.fNo = fNo;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public String getfTitle() {
		return fTitle;
	}
	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}
	public String getfContent() {
		return fContent;
	}
	public void setfContent(String fContent) {
		this.fContent = fContent;
	}
	public int getfHit() {
		return fHit;
	}
	public void setfHit(int fHit) {
		this.fHit = fHit;
	}
	public Date getfRdate() {
		return fRdate;
	}
	public void setfRdate(Date fRdate) {
		this.fRdate = fRdate;
	}
	@Override
	public String toString() {
		return "FaqDto [fNo=" + fNo + ", aId=" + aId + ", aName=" + aName + ", fTitle=" + fTitle + ", fContent="
				+ fContent + ", fHit=" + fHit + ", fRdate=" + fRdate + "]";
	}
	
}
