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

import com.tj.culture4u.dto.CmtFreeBoardDto;

public class CmtFreeBoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private static CmtFreeBoardDao instance = new CmtFreeBoardDao();
	public static CmtFreeBoardDao getInstance() {
		return instance;
	}
	
	private CmtFreeBoardDao() {}
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
	
	// 해당 게시글의 댓글 목록 가져오기 (fId)
	public ArrayList<CmtFreeBoardDto> cmtList(int fId) {
		ArrayList<CmtFreeBoardDto> list = new ArrayList<CmtFreeBoardDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT C.*, M.mName FROM CMT_FREEBOARD C, C_MEMBER M WHERE c.mid = m.mid AND C.fId = ? ORDER BY cFrdate";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int	   cFno = rs.getInt("cFno");
				String mId = rs.getString("mId");
				String mName = rs.getString("mName"); // join해서 출력
				String cFtext = rs.getString("fTitle");
				Date   cFrdate = rs.getDate("cFrdate");
				
				list.add(new CmtFreeBoardDto(cFno, mId, mName, fId, cFtext, cFrdate));
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
	public int cmtWrite(String mId, int fId, String cFtext) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO CMT_FREEBOARD (cFno, mId, fId, cFtext) " + 
				"    VALUES(CMT_FREEBOARD_SEQ.nextval, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, fId);
			pstmt.setString(3, cFtext);
			
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
	public CmtFreeBoardDto getCmt() {
		CmtFreeBoardDto cmt = null;
		
		return cmt;
	}
	
	
	// 댓글 수정
	public int cmtModify(int cFno, String cFtext) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql ="UPDATE CMT_FREEBOARD SET cFtext = ?, cFrdate = SYSDATE WHERE cFno = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cFtext);
			pstmt.setInt(2, cFno);
			
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
	public int cmtDelete(int cFno) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM FREEBOARD WHERE FID = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cFno);
			
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
