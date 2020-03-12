package com.tj.culture4u.dto;
import java.sql.Date;

import java.sql.Timestamp;

/**
 * 이 클래스는 회원 DTO 클래스다. 
 * 회원 DAO에서 사용 하기위한 클래스
 *
 * @version     1.00 20/03/11
 * @author      오병근
 * @see         com.tj.culture4u.dto
 * @since       JDK1.8
 */

public class MemberDto {
	/**
	 * 
	 * mId      VARCHAR2(30)  PRIMARY KEY, -- 아이디
	mPw      VARCHAR2(30)  NOT NULL,    -- 비밀번호
	mName    VARCHAR2(30)  NOT NULL,    -- 이름
	mEmail   VARCHAR2(30),              -- 이메일
	mPhoto   VARCHAR2(30),              -- 사진
	mBirth   DATE,                      -- 생년월일
	mAddress VARCHAR2(100),             -- 주소
	mRdate   DATE DEFAULT SYSDATE,      -- 가입등록일
	mLelve   NUMBER(1)     DEFAULT 1    -- 회원레벨
	 * */
	private String mId;
	private String mPw;
	private String mName;
	private String mEmail;
	private String mPhoto;
	private Date   mBirth;
	private String mAddress;
	private Date   mRdate;
	private int	   mLevel;
	
	public MemberDto() { }
	public MemberDto(String mId, String mPw, String mName, String mEmail, String mPhoto, Date mBirth, String mAddress,
			          Date mRdate, int mLevel) {
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mPhoto = mPhoto;
		this.mBirth = mBirth;
		this.mAddress = mAddress;
		this.mRdate = mRdate;
		this.mLevel = mLevel;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmPhoto() {
		return mPhoto;
	}
	public void setmPhoto(String mPhoto) {
		this.mPhoto = mPhoto;
	}
	public Date getmBirth() {
		return mBirth;
	}
	public void setmBirth(Date mBirth) {
		this.mBirth = mBirth;
	}
	public String getmAddress() {
		return mAddress;
	}
	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	public Date getmRdate() {
		return mRdate;
	}
	public void setmRdate(Date mRdate) {
		this.mRdate = mRdate;
	}
	public int getmLevel() {
		return mLevel;
	}
	public void setmLevel(int mLevel) {
		this.mLevel = mLevel;
	}
	
	@Override
	public String toString() {
		return "MemberDto [mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mEmail=" + mEmail + ", mPhoto="
				+ mPhoto + ", mBirth=" + mBirth + ", mAddress=" + mAddress + ", mRdate=" + mRdate + ", mLevel=" + mLevel
				+ "]";
	}
 
}
