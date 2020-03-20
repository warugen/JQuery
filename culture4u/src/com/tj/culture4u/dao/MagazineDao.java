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

import com.tj.culture4u.dto.MagazineDto;

public class MagazineDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private static MagazineDao instance = new MagazineDao();
	public static MagazineDao getInstance() {
		return instance;
	}
	
	private MagazineDao() {}
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
	public ArrayList<MagazineDto> boardList(int startRow, int endRow){
		ArrayList<MagazineDto> list = new ArrayList<MagazineDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.*  " + 
				"    FROM (SELECT * FROM MAGAZINE ORDER BY zRdate DESC) A) " + 
				"WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int zId = rs.getInt("zId");
				String zTitle = rs.getString("zTitle");
				String zContent = rs.getString("zContent");
				String zFileName = rs.getString("zFileName");
				Date   zRdate = rs.getDate("zRdate");
				int    zHit = rs.getInt("zHit");
				String zIp = rs.getString("zIp");
				int zLike = rs.getInt("zLike");
				
				list.add(new MagazineDto(zId, zTitle, zContent, zFileName, zRdate, zHit, zIp, zLike));
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
	
	// 글쓰기(원글) (zId, zTitle, zContent, zFileName, zIp)
	public int write(String zTitle, String zContent, String zFileName, String zIp) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, zIp) " + 
				"    VALUES (MAGAZINE_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, zTitle);
			pstmt.setString(2, zContent);
			pstmt.setString(3, zFileName);
			pstmt.setString(4, zIp);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "매거진글 쓰기 성공" : "매거진글 쓰기 실패");
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
	public void hitUp(int zId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MAGAZINE SET zHit = zHit + 1 " + 
				"WHERE zId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, zId);
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
	public MagazineDto contentView(int zId) {
		
		// 글 조회수 1 올리기 호출
		hitUp(zId);
		
		MagazineDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MAGAZINE WHERE zId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, zId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String zTitle = rs.getString("zTitle");
				String zContent = rs.getString("zContent");
				String zFileName = rs.getString("zFileName");
				Date   zRdate = rs.getDate("zRdate");
				int    zHit = rs.getInt("zHit");
				String zIp = rs.getString("zIp");
				int zLike = rs.getInt("zLike");
				
				dto = new MagazineDto(zId, zTitle, zContent, zFileName, zRdate, zHit, zIp, zLike);
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
	public MagazineDto modifyView(int zId) {
		// 해당 zId로 글수정할 dto 가져오기 조회수1up은 안한다.
		MagazineDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MAGAZINE WHERE zId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, zId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String zTitle = rs.getString("zTitle");
				String zContent = rs.getString("zContent");
				String zFileName = rs.getString("zFileName");
				Date   zRdate = rs.getDate("zRdate");
				int    zHit = rs.getInt("zHit");
				String zIp = rs.getString("zIp");
				int zLike = rs.getInt("zLike");
				
				dto = new MagazineDto(zId, zTitle, zContent, zFileName, zRdate, zHit, zIp, zLike);
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
	
	
	// 글 수정하기 (zId, zTitle, zContent, zFileName,  zIp, rRdate)
	public int modify(int zId, String zTitle, String zContent, String zFileName, String zIp) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql ="UPDATE MAGAZINE SET zTitle = ?, " + 
				"                    zContent = ?, " + 
				"                    zFileName = ?, " + 
				"                    zRdate = SYSDATE, " +
				"                    zIp = ? " + 
				"WHERE zId = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, zTitle);
			pstmt.setString(2, zContent);
			pstmt.setString(3, zFileName);
			pstmt.setString(4, zIp);
			pstmt.setInt(5, zId);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "매거진글 수정 성공" : "매거진글 수정 실패");
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
	
	// 글 삭제하기(zId로 삭제하기)
	public int delete(int zId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MAGAZINE WHERE zId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, zId);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "매거진글 삭제 성공" : "매거진글 삭제 실패");
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
	
	// 좋아요 눌렀으면 좋아요숫자 더하기
	public int likePlus(int zId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MAGAZINE SET zLike = zLike + 1 " + 
				"WHERE zId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, zId);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "좋아요 더하기 성공" : "좋아요 더하기 실패");
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
	
	// 좋아요 눌렀으면 좋아요숫자 빼기
	public int likeMinus(int zId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MAGAZINE SET zLike = zLike - 1\r\n" + 
				"WHERE zId = 1";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, zId);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "좋아요 빼기 성공" : "좋아요 빼기 실패");
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
	
	// 좋아요 숫자 가져오기(zId)
	public int getLikeCnt(int zId) {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MAGAZINE WHERE zId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totCnt = rs.getInt("zLike");
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
		
	// 글 검색하기 추가??
}
