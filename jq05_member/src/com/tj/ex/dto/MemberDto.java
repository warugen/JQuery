package com.tj.ex.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberDto {
/*
 * ID     VARCHAR2(50) PRIMARY KEY,
    PW     VARCHAR2(50) NOT NULL,
    NAME   VARCHAR2(50)    NOT NULL,
    BIRTH  DATE    NOT NULL,
    GENDER VARCHAR2(10)    NOT NULL,
    EMAIL  VARCHAR2(50),
    TEL    VARCHAR2(50)    NOT NULL,
    ADDRESS    VARCHAR2(250),    
    RDATE  DATE    DEFAULT SYSDATE
 * */
	
	private String id;
	private String pw;
	private String name;
	private Date	birth;
	private String gender;
	private String email;
	private String tel;
	private String address;
	private Timestamp rdate;
	
	public MemberDto() {}
	public MemberDto(String id, String pw, String name, Date birth, String gender, String email, String tel,
			String address, Timestamp rdate) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.rdate = rdate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getRdate() {
		return rdate;
	}
	public void setRdate(Timestamp rdate) {
		this.rdate = rdate;
	}
	
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pw=" + pw + ", name=" + name + ", birth=" + birth + ", gender=" + gender
				+ ", email=" + email + ", tel=" + tel + ", address=" + address + ", rdate=" + rdate + "]";
	}
	
	
}
