package com.tj.culture4u.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.culture4u.dto.AdminDto;

public class AdminDao {
	/** 로그인 성공 실패 */
	public static final int LOGIN_FAIL = 0;
	public static final int LOGIN_SUCCESS = 1;
	
	private static AdminDao instance = new AdminDao();
	public static AdminDao getInstance() {
		return instance;
	}
	
	private AdminDao() {}
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/** 
	 * 관리자 로그인 체크
	 * 
	 *  @param String aId : 관리자 로그인 아이디
	 *  @param String aPw : 관리자 로그인 패스워드
	 *  
	 *  @return 로그인 성공이면 LOGIN_SUCCESS, 실패하면 LOGIN_FAIL
	 *  
	 * */
	public int loginCheck(String aId, String aPw) {
		int result = LOGIN_FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM ADMIN WHERE aId = ? AND aPw = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, aPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = LOGIN_SUCCESS;
			} else {
				result = LOGIN_FAIL;
			}
			System.out.println(result == LOGIN_SUCCESS ? "관리자 로그인 성공" : "관리자 로그인 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	
	/** 
	 * 관리자 aId로 DTO 가져오기
	 * 
	 *  @param String aId : 관리자 로그인 아이디
	 *  
	 *  @return 로그인 성공이면 LOGIN_SUCCESS, 실패하면 LOGIN_FAIL
	 *  
	 * */
	public AdminDto getAdmin(String aId) {
		AdminDto admin = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM ADMIN WHERE AID = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String aPw = rs.getString("aPw");
				String aName = rs.getString("aName");
				
				admin = new AdminDto(aId, aPw, aName);
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return admin;
	}
}
