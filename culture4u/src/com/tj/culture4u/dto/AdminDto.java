/**
 * 이 클래스는 관리자 DTO 클래스다. 
 * 관리자 DAO에서 사용 하기위한 클래스
 *
 * @version     1.00 20/03/11
 * @author      오병근
 * @see         com.tj.culture4u.dto
 * @since       JDK1.8
 */
package com.tj.culture4u.dto;


public class AdminDto {
	/**
	 * 관리자id
	 * 비밀번호
	 * 이름
	 */
	private String aId;
	private String aPw;
	private String aName;
	public AdminDto() { }
	public AdminDto(String aId, String aPw, String aName) {
		this.aId = aId;
		this.aPw = aPw;
		this.aName = aName;
	}
	
	// getter & setter
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaPw() {
		return aPw;
	}
	public void setaPw(String aPw) {
		this.aPw = aPw;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	
	// toString
	@Override
	public String toString() {
		return "AdminDto [aId=" + aId + ", aPw=" + aPw + ", aName=" + aName + "]";
	}
}
