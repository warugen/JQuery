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

import com.tj.movieReviewSite.dto.MovieDto;
import com.tj.movieReviewSite.dto.ReviewDto;

public class ReviewDao {
	private static ReviewDao instance = new ReviewDao()	;
	public static ReviewDao getInstance() {
		return instance;
	}
	public static final int SUCCESS = 1;   //댓긇 입력,수정,삭제 성공
	public static final int FAIL = 0;  //댓긇 입력,수정,삭제 실패
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage()+"커넥션 객체 오류");
		}
		return conn;
	}
	public ArrayList<ReviewDto> review_list(int movieNo, int startRow, int endRow){
		ArrayList<ReviewDto> reviews = new ArrayList<ReviewDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM(SELECT ROWNUM RN, A.* FROM  (SELECT R.*,MNAME FROM REVIEW R, MEMBER M" + 
				"                                                WHERE movieno=? and R.MID = M.MID ORDER BY RRDATE DESC)A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int rNo = rs.getInt("rNo");
				 String mId = rs.getString("mId");
				 String mName = rs.getString("mName");
				 String rContent = rs.getString("rContent");
				 Timestamp rRdate = rs.getTimestamp("rRdate");
				 reviews.add(new ReviewDto(rNo, movieNo, mId, mName, rContent, rRdate));
			}
		} catch (SQLException e) {
			System.out.println("댓글 리스트 예외 : " + e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return reviews;
	}
	public int review_insert(int movieNo,String mId,String rContent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="INSERT INTO REVIEW (RNO,MOVIENO,MID,RCONTENT) " + 
				"    VALUES (REVIEW_SEQ.NEXTVAL,?,?,?)";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			pstmt.setString(2, mId);
			pstmt.setString(3, rContent);
			result=pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "댓글입력성공": "댓글입력실패");
		} catch (SQLException e) {
			System.out.println("댓글 달다 예외 : " +e.getMessage());
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
	public int  review_delete(int movieNo,int rNo, String mId) {
		int result= FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="DELETE FROM REVIEW WHERE MOVIENO=? AND  rNO=? AND mId=?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, rNo);
			pstmt.setString(3, mId);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "댓글삭제성공": "댓글삭제실패");
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
	public int getReviewTotCnt(int movieNo) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT COUNT(*) FROM REVIEW WHERE MOVIENO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
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
}

























