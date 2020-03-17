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
import com.tj.culture4u.dto.MagazineLikeDto;

public class MagazineLikeDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private static MagazineLikeDao instance = new MagazineLikeDao();
	public static MagazineLikeDao getInstance() {
		return instance;
	}
	
	private MagazineLikeDao() {}
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
	
	// mId로 매거진게시글의 좋아요한 목록 가져오기 
	public ArrayList<MagazineLikeDto> likeList(String mId) {
		ArrayList<MagazineLikeDto> list = new ArrayList<MagazineLikeDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT L.*, M.* FROM MLIKE L, MAGAZINE M  WHERE L.zId = M.zId AND mId = ? ORDER BY mLid";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int zId = rs.getInt("zId");
				int mLid = rs.getInt("mLid");
				String zTitle = rs.getString("zTitle"); // join해서 출력
				
				//list.add(new MagazineLikeDto(mLid, zId, mId, zTitle));
				list.add(new MagazineLikeDto(mLid, zId, mId, zTitle, null));
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
	
	// mId로 매거진게시글의 좋아요한 목록 가져오기 
	public ArrayList<MagazineLikeDto> likeList(String mId, int startRow, int endRow) {
		ArrayList<MagazineLikeDto> list = new ArrayList<MagazineLikeDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.*  " + 
				"    FROM (L.MLID, L.MID, L.ZID, MNAME, Z.ZTITLE, Z.ZFILENAME FROM MLIKE L, C_MEMBER M, MAGAZINE Z WHERE L.MID=M.MID AND L.ZID = Z.ZID AND L.MID = ? ORDER BY mLid DESC) A) " + 
				"WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int mLid = rs.getInt("mLid");
				String mName = rs.getString("mName"); // join해서 출력
				int zId = rs.getInt("zId");	
				String zTitle = rs.getString("zTitle");			// join해서 출력
				String zFileName = rs.getString("zFileName");	// join해서 출력커버이미지등록
				
				list.add(new MagazineLikeDto(mLid, zId, mId, zTitle, zFileName));
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
	
	// 해당 id로 좋아요한 매거진 갯수 가져오기
	public int getTotCnt(String mId) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM (SELECT L.MLID, L.MID, L.ZID, MNAME, Z.ZTITLE, Z.ZFILENAME FROM MLIKE L, C_MEMBER M, MAGAZINE Z WHERE L.MID=M.MID AND L.ZID = Z.ZID AND L.MID = ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
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
	
	// 좋아요 누른 게시물 저장하기
	public int doLike(int zId, String mId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO MLIKE (mLid, zId, mId) " + 
				"    VALUES(MLIKE_SEQ.nextval, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, zId);
			pstmt.setString(2, mId);
			
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "좋아요 저장 성공" : "좋아요 저장 실패");
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
	
	// 좋아요 취소 하기 (int zId, String mId)
	/**
	 * 먼저 해당 아이디로 해당 매거진 게시물을 좋아요 했는지 확인해야하나?
	 * 확인해야한다면 먼저 확인한후 해당하는게 있으면 삭제 실시한다.
	 * 그런데 게시물 표시할때 좋아요 한게 표시되니까 상관없을수도....
	 * */ 
	 
	public int cancleLike(int zId, String mId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MLIKE WHERE zId = ? AND mId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, zId);
			pstmt.setString(2, mId);			
			
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
