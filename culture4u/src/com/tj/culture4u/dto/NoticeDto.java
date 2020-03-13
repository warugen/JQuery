package com.tj.culture4u.dto;

import java.sql.Date;

public class NoticeDto {
	/**
	 * nId       NUMBER(6)      PRIMARY KEY,           -- 공지사항번호
    aId       VARCHAR2(30)   REFERENCES ADMIN(aId), -- 관리자id
	nTitle    VARCHAR2(100)  NOT NULL,              -- 글제목
	nContent  VARCHAR2(4000) NOT NULL,              -- 글본문
	nFileName VARCHAR(100)   NULL,                  -- 첨부파일이름
	nRdate    DATE DEFAULT SYSDATE,       -- 작성일
	nHit      NUMBER(6)      DEFAULT 0              -- 조회수
	 * */
	
	private int nId;
	private String aId;
	private String nTitle;
	private String nContent;
	private String nFileName;
	private Date nRdate;
	private int nHit;
	
	public NoticeDto() {}
	public NoticeDto(int nId, String aId, String nTitle, String nContent, String nFileName, Date nRdate, int nHit) {
		this.nId = nId;
		this.aId = aId;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nFileName = nFileName;
		this.nRdate = nRdate;
		this.nHit = nHit;
	}
	
	
	public int getnId() {
		return nId;
	}
	public void setnId(int nId) {
		this.nId = nId;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getnTitle() {
		return nTitle;
	}
	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}
	public String getnContent() {
		return nContent;
	}
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}
	public String getnFileName() {
		return nFileName;
	}
	public void setnFileName(String nFileName) {
		this.nFileName = nFileName;
	}
	public Date getnRdate() {
		return nRdate;
	}
	public void setnRdate(Date nRdate) {
		this.nRdate = nRdate;
	}
	public int getnHit() {
		return nHit;
	}
	public void setnHit(int nHit) {
		this.nHit = nHit;
	}
	
	// toString
	@Override
	public String toString() {
		return "NoticeDto [nId=" + nId + ", aId=" + aId + ", nTitle=" + nTitle + ", nContent=" + nContent
				+ ", nFileName=" + nFileName + ", nRdate=" + nRdate + ", nHit=" + nHit + "]";
	}
	
	
}
