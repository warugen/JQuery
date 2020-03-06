package com.tj.ex.dao;

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

import com.tj.ex.dto.BoardDto;

public class BoardDao {
	public static final int EXISTENT = 0;
	public static final int NONEXISTENT = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private static BoardDao instance = new BoardDao();
	public static BoardDao getInstance() {
		return instance;
	}
	
	private BoardDao() {}
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
	
	// 글목록(startRow부터 endRow까지)
	public ArrayList<BoardDto> boardList(int startRow, int endRow){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* " + 
				"    FROM (SELECT F.* ,MNAME FROM FILEBOARD F, MVC_MEMBER M WHERE F.MID=M.MID ORDER BY FGROUP DESC, FSTEP) A) " + 
				"WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int fId = rs.getInt("fId");
				String mId = rs.getString("mId");
				String mName = rs.getString("mName"); // join해서 출력
				String fTitle = rs.getString("fTitle");
				String fContent = rs.getString("fContent");
				String fFileName = rs.getString("fFileName");
				Date   fRdate = rs.getDate("fRdate");
				int    fHit = rs.getInt("fHit");
				int    fGroup = rs.getInt("fGroup");
				int    fStep = rs.getInt("fStep");
				int    fIndent = rs.getInt("fIndent");
				String fIp = rs.getString("fIp");
				
				list.add(new BoardDto(fId, mId, mName, fTitle, fContent, fFileName, fRdate, fHit, fGroup, fStep, fIndent, fIp));
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
	
	// 글갯수
	public int getBoardTotCnt() {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM FILEBOARD";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
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
	
	// 글쓰기(원글)
	public int write(String mId, String fTitle, String fContent, String fFileName, String fIp) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP) " + 
				"    VALUES (FILEBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, FILEBOARD_SEQ.CURRVAL, 0, 0, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fContent);
			pstmt.setString(4, fFileName);
			pstmt.setString(5, fIp);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "원글쓰기 성공" : "원글쓰기 실패");
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
	
	// FHit 하나 올리기(1번글 조회수 하나 올리기)
	public void hitUp(int fId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE FILEBOARD SET FHIT = FHIT + 1 " + 
				"WHERE FID = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			int result = pstmt.executeUpdate();
			
			System.out.println(result == SUCCESS ? "조회수 올리기 성공" : "조회수 올리기 실패");
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
		
	}
	
	// FId로 글 dto보기
	public BoardDto contentView(int fId) {
		
		// 글 조회수 1 올리기 호출
		hitUp(fId);
		
		BoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM FILEBOARD WHERE FID = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String mId = rs.getString("mId");
				String mName = rs.getString("mName"); // join해서 출력
				String fTitle = rs.getString("fTitle");
				String fContent = rs.getString("fContent");
				String fFileName = rs.getString("fFileName");
				Date   fRdate = rs.getDate("fRdate");
				int    fHit = rs.getInt("fHit");
				int    fGroup = rs.getInt("fGroup");
				int    fStep = rs.getInt("fStep");
				int    fIndent = rs.getInt("fIndent");
				String fIp = rs.getString("fIp");
				
				dto = new BoardDto(fId, mId, mName, fTitle, fContent, fFileName, fRdate, fHit, fGroup, fStep, fIndent, fIp);
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
		
		return dto;
	}
	
	
	// 글 수정하기(FId, FTitle, FContent, FILENAME,  FIp, FRDATE)
	public int modify(int fId, String fTitle, String fContent, String fFileName, String fIp) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql ="UPDATE FILEBOARD SET FTITLE = ?, " + 
				"                    FCONTENT = ?, " + 
				"                    FFILENAME = ?, " + 
				"                    FIP = ?, " + 
				"                    FRDATE = SYSDATE " + 
				"WHERE FID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fTitle);
			pstmt.setString(2, fContent);
			pstmt.setString(3, fFileName);
			pstmt.setString(4, fIp);
			pstmt.setInt(5, fId);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글 수정 성공" : "글 수정 실패");
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
	
	// 글 삭제하기(FId로 삭제하기)
	public int delete(int fId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM FILEBOARD WHERE FID = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글 삭제 성공" : "글 삭제 실패");
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
	
	// 답변글 추가전 STEP a 수행
	public void preReplyStepA(int fGroup, int fStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FILEBOARD SET FSTEP = FSTEP + 1 WHERE FGROUP = ? AND FSTEP > ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fGroup);
			pstmt.setInt(2, fStep);
			int result = pstmt.executeUpdate();
			
			System.out.println(result == SUCCESS ? "답변글 step A 성공" : "답변글 step A 실패");
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
	}
	
	// 답변글 쓰기
	public int reply(String mId, String fTitle, String fContent, String fFileName, int fGroup, int fStep, int fIndent, String fIp) {
		// stepA 실행
		preReplyStepA(fGroup, fStep);
		
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP) " + 
				"    VALUES (FILEBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fContent);
			pstmt.setString(3, fFileName);
			pstmt.setInt(5, fGroup);
			pstmt.setInt(6, fStep + 1);
			pstmt.setInt(7, fIndent + 1);
			pstmt.setString(8, fIp);
			result = pstmt.executeUpdate();
			
			System.out.println(result == SUCCESS ? "답변쓰기 성공" : "답변쓰기 실패");
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
