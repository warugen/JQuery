package com.tj.culture4u.dto;

import java.sql.Date;

public class CmtShowDto {
	/**
	 * cSno    NUMBER(6)    PRIMARY KEY, -- 댓글번호
	sId     NUMBER(6)    REFERENCES MONTHLY_SHOW(sId), -- 공연번호
	mId     VARCHAR2(30) REFERENCES C_MEMBER(mId), -- 아이디
	cStext  VARCHAR2(100),     -- 댓글내용
	cSrdate DATE DEFAULT SYSDATE      -- 댓글작성일
	 * */
	
	private int cSno;
	private int sId;
	private String mId;
	private String mName;	// join
	private String cStext;
	private Date cSrdate;
	
	public CmtShowDto() {}
	public CmtShowDto(int cSno, int sId, String mId, String mName, String cStext, Date cSrdate) {
		this.cSno = cSno;
		this.sId = sId;
		this.mId = mId;
		this.mName = mName;
		this.cStext = cStext;
		this.cSrdate = cSrdate;
	}
	
	// getter & setter
	public int getcSno() {
		return cSno;
	}
	public void setcSno(int cSno) {
		this.cSno = cSno;
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
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
	public String getcStext() {
		return cStext;
	}
	public void setcStext(String cStext) {
		this.cStext = cStext;
	}
	public Date getcSrdate() {
		return cSrdate;
	}
	public void setcSrdate(Date cSrdate) {
		this.cSrdate = cSrdate;
	}
	
	// toString
	@Override
	public String toString() {
		return "CmtShowDto [cSno=" + cSno + ", sId=" + sId + ", mId=" + mId + ", mName=" + mName + ", cStext=" + cStext
				+ ", cSrdate=" + cSrdate + "]";
	}
	
}
