package com.tj.movieReviewSite.dto;

import java.sql.Date;

public class FreeBoardDto {
	private int bNo;
	private String mId;
	private String mName;
	private String bTitle;
	private String bContent;
	private Date bRdate;
	private int bHit;
	private int bGroup;
	private int bStep;
	private int bIndent;
	public FreeBoardDto(int bNo, String mId, String mName, String bTitle, String bContent, Date bRdate, int bHit,
			int bGroup, int bStep, int bIndent) {
		this.bNo = bNo;
		this.mId = mId;
		this.mName = mName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bRdate = bRdate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public Date getbRdate() {
		return bRdate;
	}
	public void setbRdate(Date bRdate) {
		this.bRdate = bRdate;
	}
	public int getbHit() {
		return bHit;
	}
	public void setbHit(int bHit) {
		this.bHit = bHit;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbStep() {
		return bStep;
	}
	public void setbStep(int bStep) {
		this.bStep = bStep;
	}
	public int getbIndent() {
		return bIndent;
	}
	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}
	@Override
	public String toString() {
		return "FreeBoardDto [bNo=" + bNo + ", mId=" + mId + ", mName=" + mName + ", bTitle=" + bTitle + ", bContent="
				+ bContent + ", bRdate=" + bRdate + ", bHit=" + bHit + ", bGroup=" + bGroup + ", bStep=" + bStep
				+ ", bIndent=" + bIndent + "]";
	}
	
}
