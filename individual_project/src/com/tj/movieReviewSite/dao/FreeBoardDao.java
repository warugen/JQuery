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

import com.tj.movieReviewSite.dto.FreeBoardDto;

public class FreeBoardDao {
	private FreeBoardDao instance = new FreeBoardDao();
	public FreeBoardDao getInstance() {
		return instance;
	}
	public static final int SUCCESS = 1; // 원글 쓰기,수정,삭제 , 답변글쓰기 성공
	public static final int FAIL = 0 ; // 원글 쓰기,수정,삭제 , 답변글쓰기 실패
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
	public ArrayList<FreeBoardDto> freeBoard_list(int startRow, int endRow){
		ArrayList<FreeBoardDto> lists = new ArrayList<FreeBoardDto>();
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM (SELECT ROWNUM RN,A.* FROM(SELECT F.*,MNAME FROM FREEBOARD F, MEMBER M\r\n" + 
				"                                            WHERE F.MID= M.MID " + 
				"                                            ORDER BY BGROUP DESC, BSTEP)A)" + 
				"        WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int bNo = rs.getInt("bNo");
				 String mId = rs.getString("mId");
				 String mName= rs.getString("mName");
				 String bTitle= rs.getString("bTitle");
				 String bContent= rs.getString("bContent");
				 Date bRdate =rs.getDate("bRdate");
				 int bHit = rs.getInt("bHit");
				 int bGroup = rs.getInt("bGroup");
				 int bStep = rs.getInt("bStep");
				 int bIndent = rs.getInt("bIndent");
				 lists.add(new FreeBoardDto(bNo, mId, mName, bTitle, bContent, bRdate, bHit, bGroup, bStep, bIndent));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return lists;
	}
	public FreeBoardDto modify_freeBoardView_reply_freeBoardView(int bNO) {
		FreeBoardDto lists = null;
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT F.*,MNAME FROM FREEBOARD F, MEMBER M" + 
				"     WHERE F.MID= M.MID AND BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNO);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int bNo = rs.getInt("bNo");
				 String mId = rs.getString("mId");
				 String mName= rs.getString("mName");
				 String bTitle= rs.getString("bTitle");
				 String bContent= rs.getString("bContent");
				 Date bRdate =rs.getDate("bRdate");
				 int bHit = rs.getInt("bHit");
				 int bGroup = rs.getInt("bGroup");
				 int bStep = rs.getInt("bStep");
				 int bIndent = rs.getInt("bIndent");
				 lists = new FreeBoardDto(bNo, mId, mName, bTitle, bContent, bRdate, bHit, bGroup, bStep, bIndent);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return lists;
	}
	public int insert_freeBoard(FreeBoardDto dto) {
		int result = FAIL;
		Connection conn = null; 
		PreparedStatement pstmt = null;
		String sql="INSERT INTO FREEBOARD (BNO,MID,BTITLE,BCONTENT,BGROUP,BSTEP,BINDENT)" + 
				"    VALUES (FRE_SEQ.NEXTVAL,?,?,?,FRE_SEQ.CURRVAL,0,0)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmId());
			pstmt.setString(2, dto.getbTitle());
			pstmt.setString(3, dto.getbContent());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "글쓰기 성공" : "글쓰기 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return result;
	}
	public int modify_freeBoard(String bTitle,String bContent, int bNo) {
		int result = FAIL;
		Connection conn = null; 
		PreparedStatement pstmt = null;
		String sql="UPDATE FREEBOARD SET BTITLE=?," + 
				"                    BCONTENT=?" + 
				"                WHERE BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bContent);
			pstmt.setInt(3, bNo);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "글수정 성공" : "글수정 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return result;
	}
	public int delete_freeboard(int bNo) {
		int result = FAIL;
		Connection conn = null; 
		PreparedStatement pstmt = null;
		String sql="DELETE FROM FREEBOARD WHERE BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "글삭제 성공" : "글삭제 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return result;
	}
	public void stepA_freeBoard(int bGroup,int bStep) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		String sql="UPDATE FREEBOARD SET BSTEP = BSTEP +1 WHERE BGROUP=? AND BSTEP>?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bGroup);
			pstmt.setInt(2, bStep);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
	}
	public void bHitUp(int bNo) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		String sql="UPDATE FREEBOARD SET BHIT = BHIT+1 WHERE BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
	}
	public FreeBoardDto getFreeBoard(int bNo) {
		bHitUp(bNo);
		FreeBoardDto lists = null;
		Connection conn = null; 
		PreparedStatement pstmt = null;
		String sql="UPDATE FREEBOARD SET BHIT = BHIT+1 WHERE BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
					System.out.println(e.getMessage());
			}
		}
		return lists;
	}
	public int reply_freeBoard(String mId,String bTitle,String bContetn,int bGroup,int bStep,int bIndent) {
		int result = FAIL;
		Connection conn = null; 
		PreparedStatement pstmt = null;
		String sql="INSERT INTO FREEBOARD (BNO,MID,BTITLE,BCONTENT,BGROUP,BSTEP,BINDENT)" + 
				"    VALUES (FRE_SEQ.NEXTVAL,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContetn);
			pstmt.setInt(4, bGroup);
			pstmt.setInt(5, bStep+1);
			pstmt.setInt(6, bIndent+1);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "답글쓰기 성공" : "답글쓰기 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return result;
	}
	public int freeBoardTotCht() {
		int totCnt = 0;
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT COUNT(*) FROM FREEBOARD";
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
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return totCnt;
	}
}
