package com.tj.culture4u.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.culture4u.dto.CmtShowDto;

public class CmtShowDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private static CmtShowDao instance = new CmtShowDao();
	public static CmtShowDao getInstance() {
		return instance;
	}
	
	private CmtShowDao() {}
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
	
	// 해당 게시글의 댓글 목록 가져오기 (sId, cSrdate DESC, mId로 mName가져오기)
	public ArrayList<CmtShowDto> cmtList(int sId) {
		ArrayList<CmtShowDto> list = new ArrayList<CmtShowDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT C.*, M.mName FROM CMT_SHOW C, C_MEMBER M WHERE c.mid = m.mid AND C.sId = ? ORDER BY cSrdate";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int	   cSno = rs.getInt("cSno");
				String mId = rs.getString("mId");
				String mName = rs.getString("mName"); // join해서 출력
				String cStext = rs.getString("cStext");
				Date   cSrdate = rs.getDate("cSrdate");
				
				list.add(new CmtShowDto(cSno, sId, mId, mName, cStext, cSrdate));
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
		return list;
	}
	
	// 댓글 입력하기
	public int cmtWrite(String mId, int sId, String cStext) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO CMT_SHOW (cSno, sId, mId, cStext) " + 
				"    VALUES(CMT_SHOW_SEQ.nextval, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sId);
			pstmt.setString(2, mId);
			pstmt.setString(3, cStext);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글쓰기 성공" : "댓글쓰기 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
	// 수정할 댓글 가져오기??
	public CmtShowDto getCmt() {
		CmtShowDto cmt = null;
		
		return cmt;
	}
	
	
	// 댓글 수정
	public int cmtModify(int cSno, String cStext) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql ="UPDATE CMT_SHOW SET cStext = ?, cSrdate = SYSDATE WHERE cSno = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cStext);
			pstmt.setInt(2, cSno);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글 수정 성공" : "댓글 수정 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
	
	// 댓글 삭제 (cFno)
	public int cmtDelete(int cSno) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM CMT_SHOW WHERE cSno = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cSno);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글 삭제 성공" : "댓글 삭제 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
}
