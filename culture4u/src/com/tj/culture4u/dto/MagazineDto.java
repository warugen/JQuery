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

public class MagazineDto {
	/**
	zId       NUMBER(6)      PRIMARY KEY,   -- 매거진번호
	zTitle    VARCHAR2(100)  NOT NULL,      -- 매거진제목
	zContent  VARCHAR2(4000) NOT NULL,      -- 매거진본문
	zFileName VARCHAR2(100),                -- 첨부파일이름
	zRdate    DATE DEFAULT SYSDATE,         -- 작성일
	zHit      NUMBER(6)      DEFAULT 0,     -- 조회수
	zIp       VARCHAR2(30),                 -- 글쓴IP
	zLike     NUMBER(6)      DEFAULT 0      -- 좋아요 숫자
	 * 
	 * 
	 * */
	private int zId;
	private String zTitle;
	private String zContent;
	private String zFileName;
	private Date   zRdate;
	private int zHit;
	private String zIp;
	private int zLike;
		
	public MagazineDto() {}

	public MagazineDto(int zId, String zTitle, String zContent, String zFileName, Date zRdate, int zHit, String zIp,
			int zLike) {
		this.zId = zId;
		this.zTitle = zTitle;
		this.zContent = zContent;
		this.zFileName = zFileName;
		this.zRdate = zRdate;
		this.zHit = zHit;
		this.zIp = zIp;
		this.zLike = zLike;
	}

	// getter & setter
	public int getzId() {
		return zId;
	}

	public void setzId(int zId) {
		this.zId = zId;
	}

	public String getzTitle() {
		return zTitle;
	}

	public void setzTitle(String zTitle) {
		this.zTitle = zTitle;
	}

	public String getzContent() {
		return zContent;
	}

	public void setzContent(String zContent) {
		this.zContent = zContent;
	}

	public String getzFileName() {
		return zFileName;
	}

	public void setzFileName(String zFileName) {
		this.zFileName = zFileName;
	}

	public Date getzRdate() {
		return zRdate;
	}

	public void setzRdate(Date zRdate) {
		this.zRdate = zRdate;
	}

	public int getzHit() {
		return zHit;
	}

	public void setzHit(int zHit) {
		this.zHit = zHit;
	}

	public String getzIp() {
		return zIp;
	}

	public void setzIp(String zIp) {
		this.zIp = zIp;
	}

	public int getzLike() {
		return zLike;
	}

	public void setzLike(int zLike) {
		this.zLike = zLike;
	}

	// toString
	@Override
	public String toString() {
		return "MagazineDto [zId=" + zId + ", zTitle=" + zTitle + ", zContent=" + zContent + ", zFileName=" + zFileName
				+ ", zRdate=" + zRdate + ", zHit=" + zHit + ", zIp=" + zIp + ", zLike=" + zLike + "]";
	}
}
