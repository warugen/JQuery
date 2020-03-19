package com.tj.movieReviewSite.dao;

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

import com.tj.movieReviewSite.dto.FaqDto;

public class FaqDao {
	private static FaqDao instance = new FaqDao();
	public static FaqDao getInstance() {
		return instance;
	}
	public static final int SUCCESS = 1; // 공지사항 등록,수정,삭제 성공
	public static final int FAIL = 0; // 공지사항 등록 ,수정,삭제  실패
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
	public ArrayList<FaqDto> faq_list_board(int startRow , int endRow){
		ArrayList<FaqDto> faqs = new ArrayList<FaqDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT F.* ,ANAME FROM FAQ F, ADMIN A" + 
				"                                            WHERE F.AID = A.AID" + 
				"                                            ORDER BY FNO DESC)A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int fNo 		   = rs.getInt("fNo");
				 String aId 	   = rs.getString("aId");
				 String aName 	   = rs.getString("aName");
				 String fTitle      = rs.getString("fTitle");
				 String fContent	   = rs.getString("fContent");
				 int fHit           = rs.getInt("fHit");
				 Date fRdate		   = rs.getDate("fRdate");
				 faqs.add(new FaqDto(fNo, aId, aName, fTitle, fContent, fHit, fRdate));
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
		return faqs;
	}
	public int faq_board_insert(String aId, String fTitle, String fContent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="INSERT INTO FAQ (FNO,AID,FTITLE,FCONTENT,FHIT)" + 
				"    VALUES (FAQ_SEQ.NEXTVAL,?,?, ?,0)";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fContent);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "공지사항 등록 성공": "공지사항 등록 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
	public int faq_board_modify(String fTitle, String fContent, int fNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="UPDATE FAQ SET FTITLE = ?," + 
				"                FCONTENT=?," + 
				"                FRDATE = SYSDATE" + 
				"        WHERE FNO=?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fTitle);
			pstmt.setString(2, fContent);
			pstmt.setInt(3, fNo);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "공지사항 수정 성공": "공지사항 수정 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
	
	public int faq_board_delete(int fNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="DELETE FROM FAQ WHERE FNO=?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "공지사항 삭제 성공": "공지사항 삭제 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
	public FaqDto getFaq_list(int fNo) {
		fHitUp(fNo);
		FaqDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT F.*,ANAME FROM FAQ F, ADMIN A " + 
				"    WHERE F.AID=A.AID AND FNO=?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String aId 	   = rs.getString("aId");
				 String aName 	   = rs.getString("aName");
				 String fTitle      = rs.getString("fTitle");
				 String fContent	   = rs.getString("fContent");
				 int fHit           = rs.getInt("fHit");
				 Date fRdate		   = rs.getDate("fRdate");
				 dto = new FaqDto(fNo, aId, aName, fTitle, fContent, fHit, fRdate);
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
		return dto;
	}
	public int faq_board_listTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT COUNT(*) FROM FAQ";
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
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return totCnt;
	}
	public FaqDto faq_modify_view(int fNo) {
		FaqDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT F.*,ANAME FROM FAQ F, ADMIN A " + 
				"    WHERE F.AID = A.AID AND FNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String aId = rs.getString("aId");
				 String aName = rs.getString("aName");
				 String fTitle = rs.getString("fTitle");
				 String fContent = rs.getString("fContent");
				 int fHit = rs.getInt("fHit");
				 Date fRdate = rs.getDate("fRdate");
				 dto = new FaqDto(fNo, aId, aName, fTitle, fContent, fHit, fRdate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return dto;
	}
	public void fHitUp(int fNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="UPDATE FAQ SET FHIT=FHIT+1 WHERE FNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
}























