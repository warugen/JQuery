/**
 * 이 클래스는 자유게시판 댓글 DTO 클래스다. 
 * 자유게시판 댓글 DAO에서 사용 하기위한 클래스
 *
 * @version     1.00 20/03/11
 * @author      오병근
 * @see         com.tj.culture4u.dto
 * @since       JDK1.8
 */
package com.tj.culture4u.dto;

import java.sql.Date;

public class CmtFreeBoardDto {
	/**
	 * cFno    NUMBER(6)     PRIMARY KEY, -- 댓글번호
	mId     VARCHAR2(30)  REFERENCES C_MEMBER(mId), -- 아이디
	fId     NUMBER(6)     REFERENCES FREEBOARD(fId), -- 글번호
	cFtext  VARCHAR2(100) ,     -- 댓글내용
	cFrdate DATE DEFAULT SYSDATE      -- 댓글작성일
	 * */
	
	private int cFno;
	private String mId;
	private String mName;	// join으로 가져오기
	private int fId;
	private String cFtext;
	private Date cFrdate;
	
	public CmtFreeBoardDto() {}
	public CmtFreeBoardDto(int cFno, String mId, String mName, int fId, String cFtext, Date cFrdate) {
		this.cFno = cFno;
		this.mId = mId;
		this.mName = mName;
		this.fId = fId;
		this.cFtext = cFtext;
		this.cFrdate = cFrdate;
	}
	
	// getter & setter
	public int getcFno() {
		return cFno;
	}
	public void setcFno(int cFno) {
		this.cFno = cFno;
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
	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
	}
	public String getcFtext() {
		return cFtext;
	}
	public void setcFtext(String cFtext) {
		this.cFtext = cFtext;
	}
	public Date getcFrdate() {
		return cFrdate;
	}
	public void setcFrdate(Date cFrdate) {
		this.cFrdate = cFrdate;
	}
	
	// toString
	@Override
	public String toString() {
		return "CmtFreeBoardDto [cFno=" + cFno + ", mId=" + mId + ", mName=" + mName + ", fId=" + fId + ", cFtext="
				+ cFtext + ", cFrdate=" + cFrdate + "]";
	}
	
}
