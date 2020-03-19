package com.tj.movieReviewSite.dao;

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

import com.tj.movieReviewSite.dto.MemberDto;

public class MemberDao {
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	public static final int SUCCESS = 1; // 회원가입성공, 정보수정 회원탈퇴 성공
	public static final int FAIL = 0; // 회원가입실패, 정보수정 회원탈퇴  실패 
	public static final int EXISTENT = 0; // 중복된 아이디
	public static final int NONEXTISTENT = 1; // 사용가능한아이디
	public static final int LOGIN_FAIL = 0; // 로그인 실패\
	public static final int LOGIN_SUCCESS = 1; // 로그인성공
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	//--회원가입
	public int join_member(MemberDto member) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="INSERT INTO MEMBER (MID,MPW,MNAME,MBIRTH,MEMAIL,MTEL,MADDRESS,MRDATE)" + 
				"    VALUES (?,?,?,?,?,?,?,SYSDATE)";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getmId());
			pstmt.setString(2, member.getmPw());
			pstmt.setString(3, member.getmName());
			pstmt.setDate(4, member.getmBirth());
			pstmt.setString(5, member.getmEmail());
			pstmt.setString(6, member.getmTel());
			pstmt.setString(7, member.getmAddress());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "회원가입성공" : "회원가입실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(result==SUCCESS? "회원가입성공" : "회원가입실패");
		}finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	public int modify_meber(MemberDto member) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="UPDATE MEMBER SET  MPW=?," + 
				"                    MNAME=?," + 
				"                    MBIRTH=?," + 
				"                    MEMAIL=?," + 
				"                    MTEL=?," + 
				"                    MADDRESS=?," + 
				"            WHERE MID=?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getmPw());
			pstmt.setString(2, member.getmName());
			pstmt.setDate(3, member.getmBirth());
			pstmt.setString(4, member.getmEmail());
			pstmt.setString(5, member.getmTel());
			pstmt.setString(6, member.getmAddress());
			pstmt.setString(7, member.getmId());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "정보수정성공" : "정보수정실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(result==SUCCESS? "정보수정성공" : "정보수정실패");
		}finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	//--ID중복체크
	public int join_mId_Chk(String mId) {
		int result = EXISTENT;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM MEMBER WHERE MID=?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = EXISTENT;
			}else {
				result = NONEXTISTENT;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	//--로그인
	public int login_chk(String mId) {
		int result = LOGIN_FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM MEMBER WHERE MID=?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = LOGIN_SUCCESS;
			}else {
				result = LOGIN_FAIL;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	//mId로 dto 가져오기
	public MemberDto getMember(String mId) {
		MemberDto member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM MEMBER WHERE MID=?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String mPw 		= rs.getString("mPw");
				 String mName 		= rs.getString("mName");
				 Date   mBirth 		= rs.getDate("mBirth");
				 String mEmail 		= rs.getString("mEmail");
				 String mTel 		= rs.getString("mTel");
				 String mAddress	= rs.getString("mAddress");
				 Date   mRdate 		= rs.getDate("mBirth");
				 member = new MemberDto(mId, mPw, mName, mBirth, mEmail, mTel, mAddress, mRdate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return member;
	}
	//--회원목록
	public ArrayList<MemberDto> MemberList(int startRow, int endRow){
		ArrayList<MemberDto> member = new ArrayList<MemberDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER)A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 String mId 		= rs.getString("mId");
				 String mPw 		= rs.getString("mPw");
				 String mName 		= rs.getString("mName");
				 Date   mBirth 		= rs.getDate("mBirth");
				 String mEmail 		= rs.getString("mEmail");
				 String mTel 		= rs.getString("mTel");
				 String mAddress	= rs.getString("mAddress");
				 Date   mRdate 		= rs.getDate("mBirth");
				 member.add(new MemberDto(mId, mPw, mName, mBirth, mEmail, mTel, mAddress, mRdate));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return member;
	}
	public int getMemberTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql="SELECT COUNT(*) FROM MEMBER";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totCnt = rs.getInt(1);
			}
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}finally {
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
	public int delete_member(String mId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql="DELETE FROM MEMBER WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = SUCCESS;
			}else {
				result = FAIL;
			}
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}finally {
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
}
