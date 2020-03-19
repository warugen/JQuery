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
import com.tj.movieReviewSite.dto.MovieDto;

public class MovieDao {
	private static MovieDao instance = new MovieDao();
	public static MovieDao getInstance() {
		return instance;
	}
	public static final int SUCCESS = 1; // 영화업로드 등록,수정,삭제 성공
	public static final int FAIL = 0; // 영화업로드 등록 ,수정,삭제  실패
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
	public ArrayList<MovieDto> movie_list_board(int startRow , int endRow){
		ArrayList<MovieDto> movies = new ArrayList<MovieDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MOVIE" + 
				"                                            ORDER BY MOVIENO DESC)A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int movieNo = rs.getInt("movieNo");
				 String movieName = rs.getString("movieName");
				 String movieData = rs.getString("movieData");
				 String movieContent = rs.getString("movieContent");
				 Date movieRdate = rs.getDate("movieRdate");
				 movies.add(new MovieDto(movieNo, movieName, movieData, movieContent, movieRdate));
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
		return movies;
	}
	public int movie_upload(String movieName, String movieData, String movieContent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="INSERT INTO MOVIE (MOVIENO,MOVIENAME,MOVIEDATA,MOVIECONTENT)" + 
				"    VALUES (MOVIE_SEQ.NEXTVAL,?,?,?)";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieName);
			pstmt.setString(2, movieData);
			pstmt.setString(3, movieContent);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "영화 업로드 성공": "영화 업로드 실패");
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
	public int movie_board_modify(String movieName, String movieData, String movieContent, int movieNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="UPDATE MOVIE SET MOVIENAME=?," + 
				"                    MOVIEDATA=?," + 
				"                    MOVIECONTENT=?" + 
				"            WHERE MOVIENO=?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieName);
			pstmt.setString(2, movieData);
			pstmt.setString(3, movieContent);
			pstmt.setInt(4, movieNo);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "영화정보 수정 성공": "영화정보 수정 실패");
		} catch (SQLException e) {
			System.out.println("영화수정 예외 : " + e.getMessage());
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
	
	public int movie_board_delete(int movieNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="DELETE FROM MOVIE WHERE MOVIENO=?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "영화정보 삭제 성공": "영화정보 삭제 실패");
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
	public MovieDto getMovie(int movieNo) {
		MovieDto movies = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM MOVIE WHERE MOVIENO=?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 String movieName = rs.getString("movieName");
				 String movieData = rs.getString("movieData");
				 String movieContent = rs.getString("movieContent");
				 Date movieRdate = rs.getDate("movieRdate");
				 movies = new MovieDto(movieNo, movieName, movieData, movieContent, movieRdate);
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
		return movies;
	}
	public int movie_board_listTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT COUNT(*) FROM MOVIE";
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
	public MovieDto faq_modify_view(int movieNo) {
		MovieDto movies = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM MOVIE WHERE MOVIENO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String movieName = rs.getString("movieName");
				 String movieData = rs.getString("movieData");
				 String movieContent = rs.getString("movieContent");
				 Date movieRdate = rs.getDate("movieRdate");
				 movies = new MovieDto(movieNo, movieName, movieData, movieContent, movieRdate);
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
		return movies;
	}
}
