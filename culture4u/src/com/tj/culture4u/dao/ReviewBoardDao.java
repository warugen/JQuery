/**
 * 이 클래스는 후기게시판 DAO 클래스다. 
 * 후기게시판 SQL을 사용 하기위한 클래스
 *
 * @version     1.00 20/03/11
 * @author      오병근
 * @see         com.tj.culture4u.dto
 * @since       JDK1.8
 */
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

import com.tj.culture4u.dto.ReviewBoardDto;

public class ReviewBoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private static ReviewBoardDao instance = new ReviewBoardDao();
	public static ReviewBoardDao getInstance() {
		return instance;
	}
	
	private ReviewBoardDao() {}
	
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
	public ArrayList<ReviewBoardDto> boardList(int startRow, int endRow){
		ArrayList<ReviewBoardDto> list = new ArrayList<ReviewBoardDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.*  " + 
				"    FROM (SELECT F.* ,MNAME FROM REVIEW_BOARD F, C_MEMBER M WHERE F.MID=M.MID ORDER BY rGroup DESC, rStep) A) " + 
				"	 WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//int rId, String mId, String mName, String rTitle, String rContent, String rFileName,
				//Date rRdate, int rHit, int rGroup, int rStep, int rIndent, String rIp
				int rId = rs.getInt("rId");
				String mId = rs.getString("mId");
				String mName = rs.getString("mName"); // join해서 출력
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				String rFileName = rs.getString("rFileName");
				Date   rRdate = rs.getDate("rRdate");
				int    rHit = rs.getInt("rHit");
				int    rGroup = rs.getInt("rGroup");
				int    rStep = rs.getInt("rStep");
				int    rIndent = rs.getInt("rIndent");
				String rIp = rs.getString("rIp");
				
				list.add(new ReviewBoardDto(rId, mId, mName, rTitle, rContent, rFileName, rRdate, rHit, rGroup, rStep, rIndent, rIp));
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
		
		String sql = "SELECT COUNT(*) FROM REVIEW_BOARD";
		
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
	
	// 글쓰기(원글) (rId, mId, rTitle, rContent, rFileName, rGroup, rIp)
	public int write(String mId, String rTitle, String rContent, String rFileName, String rIp) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO REVIEW_BOARD (rId, mId, rTitle, rContent, rFileName, rGroup, rIp) " + 
				"    VALUES (REVIEW_BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, REVIEW_BOARD_SEQ.CURRVAL, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, rTitle);
			pstmt.setString(3, rContent);
			pstmt.setString(4, rFileName);
			pstmt.setString(5, rIp);
			
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

	// rHit 하나 올리기(1번글 조회수 하나 올리기)
	public void hitUp(int rId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE REVIEW_BOARD SET rHit = rHit + 1 " + 
						"WHERE rId = 1";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
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
	public ReviewBoardDto contentView(int rId) {
		
		// 글 조회수 1 올리기 호출
		hitUp(rId);
		
		ReviewBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT F.*, MNAME FROM REVIEW_BOARD F, C_MEMBER M WHERE M.MID=F.MID AND rId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String mId = rs.getString("mId");
				String mName = rs.getString("mName"); // join해서 출력
				String rTitle = rs.getString("fTitle");
				String rContent = rs.getString("fContent");
				String rFileName = rs.getString("fFileName");
				Date   rRdate = rs.getDate("fRdate");
				int    rHit = rs.getInt("fHit");
				int    rGroup = rs.getInt("fGroup");
				int    rStep = rs.getInt("fStep");
				int    rIndent = rs.getInt("fIndent");
				String rIp = rs.getString("fIp");
				
				dto = new ReviewBoardDto(rId, mId, mName, rTitle, rContent, rFileName, rRdate, rHit, rGroup, rStep, rIndent, rIp);
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
	
	// 수정할글과 답변쓸 글 불러오기 (원글수정과 답변쓸때 호출)
	public ReviewBoardDto modifyView_replyView(int rId) {
		// 해당 fid로 글수정할 dto 가져오기 조회수1up은 안한다.
		ReviewBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT F.*, MNAME FROM REVIEW_BOARD F, C_MEMBER M WHERE M.MID=F.MID AND rId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String mId = rs.getString("mId");
				String mName = rs.getString("mName"); // join해서 출력
				String rTitle = rs.getString("fTitle");
				String rContent = rs.getString("fContent");
				String rFileName = rs.getString("fFileName");
				Date   rRdate = rs.getDate("fRdate");
				int    rHit = rs.getInt("fHit");
				int    rGroup = rs.getInt("fGroup");
				int    rStep = rs.getInt("fStep");
				int    rIndent = rs.getInt("fIndent");
				String rIp = rs.getString("fIp");
				
				dto = new ReviewBoardDto(rId, mId, mName, rTitle, rContent, rFileName, rRdate, rHit, rGroup, rStep, rIndent, rIp);
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
	
	
	// 글 수정하기 (rID, rTitle, rContent, rFileName,  rIp, rRdate)
	public int modify(int rId, String rTitle, String rContent, String rFileName, String rIp) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql ="UPDATE REVIEW_BOARD SET rTitle = ?, " + 
				"                    rContent = ?, " + 
				"                    rFileName = ?, " + 
				"                    rIp = ?, " + 
				"                    rRdate = SYSDATE " + 
				"WHERE rID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rTitle);
			pstmt.setString(2, rContent);
			pstmt.setString(3, rFileName);
			pstmt.setString(4, rIp);
			pstmt.setInt(5, rId);
			
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
	
	// 글 삭제하기(rId로 삭제하기)
	public int delete(int rId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM REVIEW_BOARD WHERE rID = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
			
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
	public void preReplyStepA(int rGroup, int rStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW_BOARD SET rStep = rStep + 1 WHERE rGroup = ? AND rStep > ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rGroup);
			pstmt.setInt(2, rStep);
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
	public int reply(String mId, String rTitle, String rContent, String rFileName, int rGroup, int rStep, int rIndent, String rIp) {
		// stepA 실행
		preReplyStepA(rGroup, rStep);
		
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO REVIEW_BOARD (rId, mId, rTitle, rContent, rFileName, rGroup, rStep, rIndent, rIp) " + 
				"    VALUES (REVIEW_BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, rTitle);
			pstmt.setString(3, rContent);
			pstmt.setString(4, rFileName);
			pstmt.setInt(5, rGroup);
			pstmt.setInt(6, rStep + 1);
			pstmt.setInt(7, rIndent + 1);
			pstmt.setString(8, rIp);
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