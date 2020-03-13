/**
 * 이 클래스는 후기게시판 DTO 클래스다. 
 * 후기게시판 DAO에서 사용 하기위한 클래스
 *
 * @version     1.00 20/03/11
 * @author      오병근
 * @see         com.tj.culture4u.dto
 * @since       JDK1.8
 */
package com.tj.culture4u.dto;

import java.sql.Date;

public class ReviewBoardDto {
	/**
	 * rId       NUMBER(6)      PRIMARY KEY,               -- 후기글번호
	mId       VARCHAR2(30)   REFERENCES C_MEMBER(mId),  -- 아이디
	rTitle    VARCHAR2(100)  NOT NULL,                  -- 글제목
	rContent  VARCHAR2(4000) NOT NULL,                  -- 글본문
	rFileName VARCHAR2(100),                            -- 첨부파일이름
	rRdate    DATE DEFAULT SYSDATE,                     -- 작성일
	rHit      NUMBER(6)      DEFAULT 0,                 -- 조회수
	rGroup    NUMBER(6),                                -- 글그룹
	rStep     NUMBER(6)      DEFAULT 0,                 -- 그룹내출력순서
	rIndent   NUMBER(6)      DEFAULT 0,                 -- 들여쓰기정도
	rIp       VARCHAR2(30)                              -- 글쓴IP
	 * */
	
	private int    rId;
	private String mId;
	private String mName;	// join 해서 가져오기
	private String rTitle;
	private String rContent;
	private String rFileName;
	private Date   rRdate;
	private int    rHit;
	private int    rGroup;
	private int    rStep;
	private int    rIndent;
	private String rIp;
	
	public ReviewBoardDto() {}
	public ReviewBoardDto(int rId, String mId, String mName, String rTitle, String rContent, String rFileName,
			Date rRdate, int rHit, int rGroup, int rStep, int rIndent, String rIp) {
		this.rId = rId;
		this.mId = mId;
		this.mName = mName;
		this.rTitle = rTitle;
		this.rContent = rContent;
		this.rFileName = rFileName;
		this.rRdate = rRdate;
		this.rHit = rHit;
		this.rGroup = rGroup;
		this.rStep = rStep;
		this.rIndent = rIndent;
		this.rIp = rIp;
	}
	
	// getter&setter
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
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
	public String getrTitle() {
		return rTitle;
	}
	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public String getrFileName() {
		return rFileName;
	}
	public void setrFileName(String rFileName) {
		this.rFileName = rFileName;
	}
	public Date getrRdate() {
		return rRdate;
	}
	public void setrRdate(Date rRdate) {
		this.rRdate = rRdate;
	}
	public int getrHit() {
		return rHit;
	}
	public void setrHit(int rHit) {
		this.rHit = rHit;
	}
	public int getrGroup() {
		return rGroup;
	}
	public void setrGroup(int rGroup) {
		this.rGroup = rGroup;
	}
	public int getrStep() {
		return rStep;
	}
	public void setrStep(int rStep) {
		this.rStep = rStep;
	}
	public int getrIndent() {
		return rIndent;
	}
	public void setrIndent(int rIndent) {
		this.rIndent = rIndent;
	}
	public String getrIp() {
		return rIp;
	}
	public void setrIp(String rIp) {
		this.rIp = rIp;
	}
	
	// toString
	@Override
	public String toString() {
		return "ReviewBoardDto [rId=" + rId + ", mId=" + mId + ", mName=" + mName + ", rTitle=" + rTitle + ", rContent="
				+ rContent + ", rFileName=" + rFileName + ", rRdate=" + rRdate + ", rHit=" + rHit + ", rGroup=" + rGroup
				+ ", rStep=" + rStep + ", rIndent=" + rIndent + ", rIp=" + rIp + "]";
	}
	
	
	
	
	
	
}
