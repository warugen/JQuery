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

import com.tj.culture4u.dto.MonthlyShowDto;


public class MonthlyShowDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private static MonthlyShowDao instance = new MonthlyShowDao();
	public static MonthlyShowDao getInstance() {
		return instance;
	}
	
	private MonthlyShowDao() {}
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
	
	// 월별로 글목록 가져오기 (startRow부터 endRow까지)
	public ArrayList<MonthlyShowDto> monthlyList(String monthly){
		ArrayList<MonthlyShowDto> list = new ArrayList<MonthlyShowDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MONTHLY_SHOW WHERE sStartDate >= TRUNC(TO_DATE(?, 'YY/MM'), 'MM')  AND sStartDate <= LAST_DAY(TO_DATE(?, 'YY/MM')) ORDER BY sStartDate";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, monthly);
			pstmt.setString(2, monthly);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int sId = rs.getInt("sId");
				String sTitle = rs.getString("sTitle");
				String sContent = rs.getString("sContent");
				Date sStartDate = rs.getDate("sStartDate");
				Date sEndDate = rs.getDate("sEndDate");
				String sPlace = rs.getString("sPlace");
				Date sRdate = rs.getDate("sRdate");
				int sHit = rs.getInt("sHit");
				String sIp = rs.getString("sIp");
				
				list.add(new MonthlyShowDto(sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sRdate, sHit, sIp));
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
	
	// 글갯수 가져오기 필요한가??
	/**
	public int getTotCnt() {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM MAGAZINE";
		
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
	*/
	
	// 공연정보 등록하기 (sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sIp)
	public int registShow(String sTitle, String sContent, Date sStartDate, Date sEndDate, String sPlace, String sIp) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO MONTHLY_SHOW (sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sIp) " + 
				"    VALUES (MONTHLY_SHOW_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sTitle);
			pstmt.setString(2, sContent);
			pstmt.setDate(3, sStartDate);
			pstmt.setDate(4, sEndDate);
			pstmt.setString(5, sPlace);
			pstmt.setString(6, sIp);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "공연등록 쓰기 성공" : "공연등록 쓰기 실패");
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
	
	// sHit 하나 올리기(조회수 하나 올리기)
	public void hitUp(int sId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MONTHLY_SHOW SET sHit = sHit + 1 " + 
				"WHERE sId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sId);
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
	
	// zId로 글 dto보기
	public MonthlyShowDto contentView(int sId) {
		
		// 글 조회수 1 올리기 호출
		hitUp(sId);
		
		MonthlyShowDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MONTHLY_SHOW WHERE sId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String sTitle = rs.getString("sTitle");
				String sContent = rs.getString("sContent");
				Date sStartDate = rs.getDate("sStartDate");
				Date sEndDate = rs.getDate("sEndDate");
				String sPlace = rs.getString("sPlace");
				Date sRdate = rs.getDate("sRdate");
				int sHit = rs.getInt("sHit");
				String sIp = rs.getString("sIp");
				
				dto = new MonthlyShowDto(sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sRdate, sHit, sIp);
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
	
	// 글수정화면 보이기 (원글수정 호출)
	public MonthlyShowDto modifyView(int sId) {
		// 해당 fid로 글수정할 dto 가져오기 조회수1up은 안한다.
		MonthlyShowDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MONTHLY_SHOW WHERE sId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String sTitle = rs.getString("sTitle");
				String sContent = rs.getString("sContent");
				Date sStartDate = rs.getDate("sStartDate");
				Date sEndDate = rs.getDate("sEndDate");
				String sPlace = rs.getString("sPlace");
				Date sRdate = rs.getDate("sRdate");
				int sHit = rs.getInt("sHit");
				String sIp = rs.getString("sIp");
				
				dto = new MonthlyShowDto(sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sRdate, sHit, sIp);
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
	
	
	// 공연글 수정하기(sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sIp, sRdate)
	public int modify(int sId, String sTitle, String sContent, Date sStartDate, Date sEndDate, String sPlace, String sIp) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql ="UPDATE MONTHLY_SHOW SET sTitle = ?,  " + 
				"                    sContent = ?,  " + 
				"                    sStartDate = ?,  " + 
				"                    sEndDate = ?,  " + 
				"                    sPlace = ?,  " + 
				"                    sRdate = SYSDATE,  " + 
				"                    sIp = ?  " + 
				"WHERE sId = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sTitle);
			pstmt.setString(2, sContent);
			pstmt.setDate(3, sStartDate);
			pstmt.setDate(4, sEndDate);
			pstmt.setString(5, sPlace);
			pstmt.setString(6, sIp);
			pstmt.setInt(7, sId);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "공연글 수정 성공" : "공연글 수정 실패");
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
	
	// 공연글 삭제하기(sId로 삭제하기)
	public int delete(int sId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MONTHLY_SHOW WHERE sId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sId);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "공연글 삭제 성공" : "공연글 삭제 실패");
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
	
		
	// 글 검색하기 추가??
}
