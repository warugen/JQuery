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

import com.tj.culture4u.dto.NoticeDto;


public class NoticeDao {
	public static final int EXISTENT = 0;
	public static final int NONEXISTENT = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private static NoticeDao instance = new NoticeDao();
	public static NoticeDao getInstance() {
		return instance;
	}
	
	private NoticeDao() {}
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
	public ArrayList<NoticeDto> boardList(int startRow, int endRow){
		ArrayList<NoticeDto> list = new ArrayList<NoticeDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.*  " + 
				"    FROM (SELECT * FROM NOTICE ORDER BY nRdate DESC) A)  " + 
				" WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int nId = rs.getInt("nId"); 
				String aId = rs.getString("aId");
				String nTitle = rs.getString("nTitle"); 
				String nContent = rs.getString("nContent");
				String nFileName = rs.getString("nFileName");
				Date nRdate = rs.getDate("nRdate");
				int nHit = rs.getInt("nHit");
				
				list.add(new NoticeDto(nId, aId, nTitle, nContent, nFileName, nRdate, nHit));
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
		
		String sql = "SELECT COUNT(*) FROM NOTICE";
		
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
	
	// 공지글쓰기(원글) (nId, aId, nTitle, nContent, nFileName)
	public int write(String aId, String nTitle, String nContent, String nFileName) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO NOTICE (nId, aId, nTitle, nContent, nFileName ) " + 
				"    VALUES (NOTICE_SEQ.NEXTVAL, ? ,? ,?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, nTitle);
			pstmt.setString(3, nContent);
			pstmt.setString(4, nFileName);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "공지글쓰기 성공" : "공지글쓰기 실패");
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
	public void hitUp(int nId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE NOTICE SET nHit = nHit + 1 " +
						"WHERE nId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nId);
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
	
	// nId로 글 dto보기
	public NoticeDto contentView(int nId) {
		
		// 글 조회수 1 올리기 호출
		hitUp(nId);
		
		NoticeDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM NOTICE WHERE nId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String aId = rs.getString("aId");
				String nTitle = rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				String nFileName = rs.getString("nFileName");
				Date   nRdate = rs.getDate("nRdate");
				int    nHit = rs.getInt("nHit");
				
				dto = new NoticeDto(nId, aId, nTitle, nContent, nFileName, nRdate, nHit);
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
	
	// 글수정 (원글수정쓸때 호출)
	public NoticeDto modifyView(int nId) {
		// 해당 fid로 글수정할 dto 가져오기 조회수1up은 안한다.
		NoticeDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM NOTICE WHERE nId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String aId = rs.getString("aId");
				String nTitle = rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				String nFileName = rs.getString("nFileName");
				Date   nRdate = rs.getDate("nRdate");
				int    nHit = rs.getInt("nHit");
				
				dto = new NoticeDto(nId, aId, nTitle, nContent, nFileName, nRdate, nHit);
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
	
	// 글 수정하기 (nId, nTitle, nContent, nFILENAME,  nIp, )
	public int modify(int nId, String nTitle, String nContent, String nFileName) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql ="UPDATE NOTICE SET nTitle = ?, " + 
				"                    nContent = ?, " + 
				"                    nFileName = ?, " + 
				"                    nRdate = SYSDATE " + 
				"WHERE nId = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nTitle);
			pstmt.setString(2, nContent);
			pstmt.setString(3, nFileName);
			pstmt.setInt(4, nId);
			
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
	public int delete(int nId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM NOTICE WHERE nId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nId);
			
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
	
	/**
	 * 공지사항은 답변글 달 수 없다.
	 * 
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
	*/
	
	// 글 검색하기 추가??
}
