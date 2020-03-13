package com.tj.culture4u.dto;

import java.sql.Date;

public class CmtReviewBoardDto {
	/**
	 * cRno    NUMBER(6)    PRIMARY KEY, -- 댓글번호
	rId     NUMBER(6)    REFERENCES REVIEW_BOARD(rId), -- 후기글번호
	mId     VARCHAR2(30) REFERENCES C_MEMBER(mId), -- 아이디
	cRtext  VARCHAR2(100),     -- 댓글내용
	cRrdate DATE DEFAULT SYSDATE      -- 댓글작성일
	 * */
	
	private int cRno;
	private String mId;
	private String mName;
	private int rId;
	private String cRtext;
	private Date cRrdate;
	
	public CmtReviewBoardDto() {}
	public CmtReviewBoardDto(int cRno, String mId, String mName, int rId, String cRtext, Date cRrdate) {
		this.cRno = cRno;
		this.mId = mId;
		this.mName = mName;
		this.rId = rId;
		this.cRtext = cRtext;
		this.cRrdate = cRrdate;
	}
	
	//  getter & setter
	public int getcRno() {
		return cRno;
	}
	public void setcRno(int cRno) {
		this.cRno = cRno;
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
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getcRtext() {
		return cRtext;
	}
	public void setcRtext(String cRtext) {
		this.cRtext = cRtext;
	}
	public Date getcRrdate() {
		return cRrdate;
	}
	public void setcRrdate(Date cRrdate) {
		this.cRrdate = cRrdate;
	}
	
	
	@Override
	public String toString() {
		return "CmtReviewBoardDto [cRno=" + cRno + ", mId=" + mId + ", mName=" + mName + ", rId=" + rId + ", cRtext="
				+ cRtext + ", cRrdate=" + cRrdate + "]";
	}
	
	
}
