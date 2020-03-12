/**
 * 이 클래스는 자유게시판 DTO 클래스다. 
 * 자유게시판 DAO에서 사용 하기위한 클래스
 *
 * @version     1.00 20/03/11
 * @author      오병근
 * @see         com.tj.culture4u.dto
 * @since       JDK1.8
 */
package com.tj.culture4u.dto;

import java.sql.Date;

public class FreeBoardDto {
	/**
	fId       NUMBER(6)      PRIMARY KEY, -- 글번호
	mId       VARCHAR2(30)   REFERENCES C_MEMBER(mId),   -- 글쓴이id
	fTitle    VARCHAR2(100)  NOT NULL,    -- 글제목
	fContent  VARCHAR2(4000) ,        -- 글본문
	fFileName VARCHAR2(100)  ,        -- 첨부파일이름
	fRdate    DATE DEFAULT SYSDATE,       -- 작성일
	fHit      NUMBER(6)      DEFAULT 0,        -- 조회수
	fGroup    NUMBER(6)      NOT NULL,        -- 글그룹
	fStep     NUMBER(6)      DEFAULT 0,        -- 그룹내출력순서
	fIndent   NUMBER(6)      DEFAULT 0,        -- 들여쓰기정도
	fIp       VARCHAR2(30)            -- 글쓴IP
	 * 
	 * 
	 * */
	private int fId;
	private String mId;
	private String mName; // join해서 출력
	private String fTitle;
	private String fContent;
	private String fFileName;
	private Date   fRdate;
	private int    fHit;
	private int    fGroup;
	private int    fStep;
	private int    fIndent;
	private String fIp;
	
	public FreeBoardDto() {}
	public FreeBoardDto(int fId, String mId, String mName, String fTitle, String fContent, String fFileName, Date fRdate,
			int fHit, int fGroup, int fStep, int fIndent, String fIp) {
		this.fId = fId;
		this.mId = mId;
		this.mName = mName;
		this.fTitle = fTitle;
		this.fContent = fContent;
		this.fFileName = fFileName;
		this.fRdate = fRdate;
		this.fHit = fHit;
		this.fGroup = fGroup;
		this.fStep = fStep;
		this.fIndent = fIndent;
		this.fIp = fIp;
	}
	
	// getter & setter
	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
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
	public String getfFileName() {
		return fFileName;
	}
	public void setfFileName(String fFileName) {
		this.fFileName = fFileName;
	}
	public Date getfRdate() {
		return fRdate;
	}
	public void setfRdate(Date fRdate) {
		this.fRdate = fRdate;
	}
	public int getfHit() {
		return fHit;
	}
	public void setfHit(int fHit) {
		this.fHit = fHit;
	}
	public int getfGroup() {
		return fGroup;
	}
	public void setfGroup(int fGroup) {
		this.fGroup = fGroup;
	}
	public int getfStep() {
		return fStep;
	}
	public void setfStep(int fStep) {
		this.fStep = fStep;
	}
	public int getfIndent() {
		return fIndent;
	}
	public void setfIndent(int fIndent) {
		this.fIndent = fIndent;
	}
	public String getfIp() {
		return fIp;
	}
	public void setfIp(String fIp) {
		this.fIp = fIp;
	}
	
	// toString
	@Override
	public String toString() {
		return "BoardDto [fId=" + fId + ", mId=" + mId + ", mName=" + mName + ", fTitle=" + fTitle + ", fContent="
				+ fContent + ", fFileName=" + fFileName + ", fRdate=" + fRdate + ", fHit=" + fHit + ", fGroup=" + fGroup
				+ ", fStep=" + fStep + ", fIndent=" + fIndent + ", fIp=" + fIp + "]";
	}
}
