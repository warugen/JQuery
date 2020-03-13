package com.tj.culture4u.dto;

import java.sql.Date;

public class MonthlyShowDto {
	/**
	 * sId        NUMBER(6)     PRIMARY KEY, -- 공연번호
	sTitle     VARCHAR2(100)       NOT NULL, -- 공연제목
	sContent   VARCHAR2(4000)       NOT NULL, -- 공연내용
	sStartDate DATE          NOT NULL, -- 공연시작일
	sEndDate   DATE          NOT NULL, -- 공연종료일
	sPlace     VARCHAR2(100) NOT NULL, -- 공연장소
	sRdate     DATE DEFAULT SYSDATE,     -- 작성일
	sHit       NUMBER(6) DEFAULT 0,     -- 조회수
	sIp        VARCHAR2(30)      -- 글쓴IP
	 * */
	
	private int sId;
	private String sTitle;
	private String sContent;
	private Date sStartDate;
	private Date sEndDate;
	private String sPlace;
	// private String sLocation;   지도 저장하려면 추가해주기
	private Date sRdate;
	private int sHit;
	private String sIp;
	
	public MonthlyShowDto() {}
	public MonthlyShowDto(int sId, String sTitle, String sContent, Date sStartDate, Date sEndDate, String sPlace,
			Date sRdate, int sHit, String sIp) {
		this.sId = sId;
		this.sTitle = sTitle;
		this.sContent = sContent;
		this.sStartDate = sStartDate;
		this.sEndDate = sEndDate;
		this.sPlace = sPlace;
		this.sRdate = sRdate;
		this.sHit = sHit;
		this.sIp = sIp;
	}
	
	// getter & setter
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsTitle() {
		return sTitle;
	}
	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public String getsContent() {
		return sContent;
	}
	public void setsContent(String sContent) {
		this.sContent = sContent;
	}
	public Date getsStartDate() {
		return sStartDate;
	}
	public void setsStartDate(Date sStartDate) {
		this.sStartDate = sStartDate;
	}
	public Date getsEndDate() {
		return sEndDate;
	}
	public void setsEndDate(Date sEndDate) {
		this.sEndDate = sEndDate;
	}
	public String getsPlace() {
		return sPlace;
	}
	public void setsPlace(String sPlace) {
		this.sPlace = sPlace;
	}
	public Date getsRdate() {
		return sRdate;
	}
	public void setsRdate(Date sRdate) {
		this.sRdate = sRdate;
	}
	public int getsHit() {
		return sHit;
	}
	public void setsHit(int sHit) {
		this.sHit = sHit;
	}
	public String getsIp() {
		return sIp;
	}
	public void setsIp(String sIp) {
		this.sIp = sIp;
	}
	
	// toString
	@Override
	public String toString() {
		return "MonthlyShowDto [sId=" + sId + ", sTitle=" + sTitle + ", sContent=" + sContent + ", sStartDate="
				+ sStartDate + ", sEndDate=" + sEndDate + ", sPlace=" + sPlace + ", sRdate=" + sRdate + ", sHit=" + sHit
				+ ", sIp=" + sIp + "]";
	}
	
}
