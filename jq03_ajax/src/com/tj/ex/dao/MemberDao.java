package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.MemberDto;

public class MemberDao {
	public static final int EXISTENT = 0;
	public static final int NONEXISTENT = 1;
	public static final int LOGIN_FAIL = 0;
	public static final int LOGIN_SUCCESS = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	
	private MemberDao() {}
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
	
	// mId 중복체크
	public int mIdConfirm(String mId) {
		int result = EXISTENT;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MVC_MEMBER WHERE mID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("중복이야");
				result = EXISTENT;
			} else {
				System.out.println("가입한다");
				result = NONEXISTENT;
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
		return result;
	}
	
	// 회원가입
	public int joinMember(MemberDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO MVC_MEMBER (mId, mPw, mNAME, mEMAIL, mPHOTO, mBIRTH, mADDRESS)\r\n" + 
				"    VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmId());
			pstmt.setString(2, dto.getmPw());
			pstmt.setString(3, dto.getmName());
			pstmt.setString(4, dto.getmEmail());
			pstmt.setString(5, dto.getmPhoto());
			pstmt.setDate(6, dto.getmBirth());
			pstmt.setString(7, dto.getmAddress());
			
			result = pstmt.executeUpdate();
			
			System.out.println(result == SUCCESS ? "회원등록 성공" : "회원등록 실패");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + dto.toString());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return result;
	}
	
	public int loginCheck(String mId, String mPw) {
		int result = FAIL;
		
		String sql = "SELECT * FROM MVC_MEMBER WHERE mID = ? AND mPW = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = LOGIN_SUCCESS;
			} else {
				result = LOGIN_FAIL;
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
		return result;
	}
	
	// mId로 MemberDto 가져오기 
	public MemberDto getMember(String mId) {
		MemberDto member = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MVC_MEMBER WHERE mID = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String  	mPw = rs.getString("mPw");
				String  	mName = rs.getString("mName");
				String  	mEmail = rs.getString("mEmail");
				String 	 	mPhoto = rs.getString("mPhoto");
				Date		mBirth = rs.getDate("mBirth");
				String  	mAddress = rs.getString("mAddress");
				Timestamp	mRdate = rs.getTimestamp("mRdate");
				
				member = new MemberDto(mId, mPw, mName, mEmail, mPhoto, mBirth, mAddress, mRdate);
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
		
		return member;
	}
	
	// 회원정보수정
	public int modifyMember(MemberDto dto) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MVC_MEMBER SET mPW = ?, " + 
				"                    mNAME = ?, " +
				"                    mEmail = ?, " + 
				"                    mPHOTO = ?, " + 
				"                    mBIRTH = ?, " + 
				"                    mADDRESS = ? " + 
				"        WHERE mID = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmPw());
			pstmt.setString(2, dto.getmName());
			pstmt.setString(3, dto.getmEmail());
			pstmt.setString(4, dto.getmPhoto());
			pstmt.setDate(5, dto.getmBirth());
			pstmt.setString(6, dto.getmAddress());
			pstmt.setString(7, dto.getmId());
			
			result = pstmt.executeUpdate();
			
			System.out.println(result == SUCCESS ? "수정 성공" : "수정 실패");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + dto.toString());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return result;
	}
	
	// 회원 목록 가져오기
	public ArrayList<MemberDto> allMember(int startRow, int endRow) {
		ArrayList<MemberDto> members = new ArrayList<MemberDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.*  " + 
				"    FROM (SELECT * FROM MVC_MEMBER ORDER BY mRDATE DESC) A)  " + 
				"WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String		mId = rs.getString("mId");
				String  	mPw = rs.getString("mPw");
				String  	mName = rs.getString("mName");
				String  	mEmail = rs.getString("mEmail");
				String 	 	mPhoto = rs.getString("mPhoto");
				Date		mBirth = rs.getDate("mBirth");
				String  	mAddress = rs.getString("mAddress");
				Timestamp	mRdate = rs.getTimestamp("mRdate");
				
				members.add(new MemberDto(mId, mPw, mName, mEmail, mPhoto, mBirth, mAddress, mRdate));
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
		
		return members;
	}
	
	// 등록 회원 숫자로 가져오기
	public int getMemberTotCnt() {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM MVC_MEMBER";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totCnt = rs.getInt(1);
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
		
		return totCnt;
	}
}
