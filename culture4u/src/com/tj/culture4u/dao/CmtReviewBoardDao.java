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

import com.tj.culture4u.dto.CmtReviewBoardDto;

public class CmtReviewBoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private static CmtReviewBoardDao instance = new CmtReviewBoardDao();
	public static CmtReviewBoardDao getInstance() {
		return instance;
	}
	
	private CmtReviewBoardDao() {}
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
	
	// 해당 게시글의 댓글 목록 가져오기 (rId, cFrdate DESC, mId로 mName가져오기)
	public ArrayList<CmtReviewBoardDto> cmtList(int rId) {
		ArrayList<CmtReviewBoardDto> list = new ArrayList<CmtReviewBoardDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT C.*, M.mName FROM CMT_REVIEW C, C_MEMBER M WHERE c.mid = m.mid AND C.rId = ? ORDER BY cRrdate";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int	   cRno = rs.getInt("cRno");
				String mId = rs.getString("mId");
				String mName = rs.getString("mName"); // join해서 출력
				String cRtext = rs.getString("cRtext");
				Date   cRrdate = rs.getDate("cFrdate");
				
				list.add(new CmtReviewBoardDto(cRno, mId, mName, rId, cRtext, cRrdate));
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
	public int cmtWrite(String mId, int rId, String cRtext) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO CMT_REVIEW (cRno, rId, mId, cRtext) " + 
				"    VALUES(CMT_REVIEW_SEQ.nextval, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, rId);
			pstmt.setString(3, cRtext);
			
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
	public CmtReviewBoardDto getCmt() {
		CmtReviewBoardDto cmt = null;
		
		return cmt;
	}
	
	
	// 댓글 수정
	public int cmtModify(int cRno, String cRtext) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql ="UPDATE CMT_FREEBOARD SET cRtext = ?, cRrdate = SYSDATE WHERE cRno = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cRtext);
			pstmt.setInt(2, cRno);
			
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
	public int cmtDelete(int cRno) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM CMT_REVIEW WHERE cRno = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cRno);
			
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
