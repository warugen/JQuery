package com.tj.movieReviewSite.dto;

import java.sql.Timestamp;

public class ReviewDto {
	private int rNo;
	private int movieNo;
	private String mId;
	private String mName;
	private String rContent;
	private Timestamp rRdate;
	public ReviewDto() {}
	public ReviewDto(int rNo, int movieNo, String mId, String mName, String rContent, Timestamp rRdate) {
		this.rNo = rNo;
		this.movieNo = movieNo;
		this.mId = mId;
		this.mName = mName;
		this.rContent = rContent;
		this.rRdate = rRdate;
	}
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public int getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
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
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public Timestamp getrRdate() {
		return rRdate;
	}
	public void setrRdate(Timestamp rRdate) {
		this.rRdate = rRdate;
	}
	@Override
	public String toString() {
		return "ReviewDto [rNo=" + rNo + ", movieNo=" + movieNo + ", mId=" + mId + ", mName=" + mName + ", rContent="
				+ rContent + ", rRdate=" + rRdate + "]";
	}
	
}
