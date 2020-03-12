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

import com.tj.culture4u.dto.FreeBoardDto;


public class FreeBoardDao {
	public static final int EXISTENT = 0;
	public static final int NONEXISTENT = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private static FreeBoardDao instance = new FreeBoardDao();
	public static FreeBoardDao getInstance() {
		return instance;
	}
	
	private FreeBoardDao() {}
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
	public ArrayList<FreeBoardDto> boardList(int startRow, int endRow){
		ArrayList<FreeBoardDto> list = new ArrayList<FreeBoardDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* " + 
				"    FROM (SELECT F.* ,MNAME FROM FREEBOARD F, C_MEMBER M WHERE F.MID=M.MID ORDER BY FGROUP DESC, FSTEP) A) " + 
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
				
				list.add(new FreeBoardDto(fId, mId, mName, fTitle, fContent, fFileName, fRdate, fHit, fGroup, fStep, fIndent, fIp));
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
	
	// 글갯수 가져오기
	public int getBoardTotCnt() {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM FREEBOARD";
		
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
	
	// 글쓰기(원글) (fId, mId, fTitle, fContent, fFileName, fGroup, fIp)
	public int write(String mId, String fTitle, String fContent, String fFileName, String fIp) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO FREEBOARD (fId, mId, fTitle, fContent, fFileName, fGroup, fIp) " + 
				"    VALUES (FREEBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, FREEBOARD_SEQ.CURRVAL, ?)";
		
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
		
		String sql = "UPDATE FREEBOARD SET fHit = fHit + 1 " + 
						"WHERE fId = ?";
		
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
	public FreeBoardDto contentView(int fId) {
		
		// 글 조회수 1 올리기 호출
		hitUp(fId);
		
		FreeBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT F.*, MNAME FROM FREEBOARD F, C_MEMBER M WHERE M.MID=F.MID AND FID = ?";
		
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
				
				dto = new FreeBoardDto(fId, mId, mName, fTitle, fContent, fFileName, fRdate, fHit, fGroup, fStep, fIndent, fIp);
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
	
	// 글수정 (원글수정과 답변쓸때 호출)
	public FreeBoardDto modifyView_replyView(int fId) {
		// 해당 fid로 글수정할 dto 가져오기 조회수1up은 안한다.
		FreeBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT F.*, MNAME FROM FREEBOARD F, C_MEMBER M WHERE M.MID=F.MID AND FID = ?";
		
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
				
				dto = new FreeBoardDto(fId, mId, mName, fTitle, fContent, fFileName, fRdate, fHit, fGroup, fStep, fIndent, fIp);
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
	
	
	// 글 수정하기 (FId, FTitle, FContent, FILENAME,  FIp, FRDATE)
	public int modify(int fId, String fTitle, String fContent, String fFileName, String fIp) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql ="UPDATE FREEBOARD SET FTITLE = ?, " + 
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
		
		String sql = "DELETE FROM FREEBOARD WHERE FID = ?";
		
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
		String sql = "UPDATE FREEBOARD SET FSTEP = FSTEP + 1 WHERE FGROUP = ? AND FSTEP > ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fGroup);
			pstmt.setInt(2, fStep);
			int result = pstmt.executeUpdate();
			
			System.out.println(result == SUCCESS ? "답변글 step A 성공" : "답변글 step A 실패");
		} catch (Exception e) {
			System.out.println("전처리 : "+e.getMessage());
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
		
		String sql = "INSERT INTO FREEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP) " + 
				"    VALUES (FREEBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fContent);
			pstmt.setString(4, fFileName);
			pstmt.setInt(5, fGroup);
			pstmt.setInt(6, fStep + 1);
			pstmt.setInt(7, fIndent + 1);
			pstmt.setString(8, fIp);
			System.out.println();
			result = pstmt.executeUpdate();
			
			System.out.println(result == SUCCESS ? "답변쓰기 성공" : "답변쓰기 실패");
		} catch (Exception e) {
			System.out.println("답글달다 예외 "+e.getMessage());
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
