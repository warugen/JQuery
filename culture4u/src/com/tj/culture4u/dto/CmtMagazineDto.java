package com.tj.culture4u.dto;

import java.sql.Date;

public class CmtMagazineDto {
	/**
	 * cZno    NUMBER(6)     PRIMARY KEY,              -- 댓글번호
    zId     NUMBER(6)     REFERENCES MAGAZINE(zId), -- 매거진번호
    mId     VARCHAR2(30)  REFERENCES C_MEMBER(mId), -- 아이디
	cZtext  VARCHAR2(100),                     -- 댓글내용
	cZrdate DATE DEFAULT SYSDATE                    -- 댓글작성일
	 * */
	
	private int cZno;
	private int zId;
	private String mId;
	private String mName;	// join해서 가져오기
	private String cZtext;
	private Date cZrdate;
	
	public CmtMagazineDto() {}
	public CmtMagazineDto(int cZno, int zId, String mId, String mName, String cZtext, Date cZrdate) {
		this.cZno = cZno;
		this.zId = zId;
		this.mId = mId;
		this.mName = mName;
		this.cZtext = cZtext;
		this.cZrdate = cZrdate;
	}
	
	// getter & setter
	public int getcZno() {
		return cZno;
	}
	public void setcZno(int cZno) {
		this.cZno = cZno;
	}
	public int getzId() {
		return zId;
	}
	public void setzId(int zId) {
		this.zId = zId;
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
	public String getcZtext() {
		return cZtext;
	}
	public void setcZtext(String cZtext) {
		this.cZtext = cZtext;
	}
	public Date getcZrdate() {
		return cZrdate;
	}
	public void setcZrdate(Date cZrdate) {
		this.cZrdate = cZrdate;
	}
	
	@Override
	public String toString() {
		return "CmtMagazineDto [cZno=" + cZno + ", zId=" + zId + ", mId=" + mId + ", mName=" + mName + ", cZtext="
				+ cZtext + ", cZrdate=" + cZrdate + "]";
	}
}
