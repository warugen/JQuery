package com.tj.culture4u.dto;

public class MagazineLikeDto {
	/**
	 * mLid NUMBER(6)    PRIMARY KEY,               -- 스크랩번호
	zId NUMBER(6)    REFERENCES MAGAZINE(zId),   -- 매거진번호
    mId VARCHAR2(30) REFERENCES C_MEMBER(mId)  -- 아이디
	 * */
	
	private int mLid;
	private int zId;
	private String mId;
	private String zTitle;	  // join해서 가져오기
	private String zFileName; // join해서 가져오기
	
	public MagazineLikeDto() {}
	public MagazineLikeDto(int mLid, int zId, String mId, String zTitle, String zFileName) {
		this.mLid = mLid;
		this.zId = zId;
		this.mId = mId;
		this.zTitle = zTitle;
		this.zFileName = zFileName;
	}
	
	// getter & setter
	public int getmLid() {
		return mLid;
	}
	public void setmLid(int mLid) {
		this.mLid = mLid;
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
	public String getzTitle() {
		return zTitle;
	}
	public void setzTitle(String zTitle) {
		this.zTitle = zTitle;
	}
	public String getzFileName() {
		return zFileName;
	}
	public void setzFileName(String zFileName) {
		this.zFileName = zFileName;
	}
	
	// toString
	@Override
	public String toString() {
		return "MagazineLikeDto [mLid=" + mLid + ", zId=" + zId + ", mId=" + mId + ", zTitle=" + zTitle + ", zFileName="
				+ zFileName + "]";
	}	
	
}
