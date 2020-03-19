package com.tj.movieReviewSite.dto;

import java.sql.Date;

public class MovieDto {
	private int movieNo;
	private String movieName;
	private String movieData;
	private String movieContent;
	private Date movieRdate;
	public MovieDto() {}
	public MovieDto(int movieNo, String movieName, String movieData, String movieContent, Date movieRdate) {
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.movieData = movieData;
		this.movieContent = movieContent;
		this.movieRdate = movieRdate;
	}
	public int getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieData() {
		return movieData;
	}
	public void setMovieData(String movieData) {
		this.movieData = movieData;
	}
	public String getMovieContent() {
		return movieContent;
	}
	public void setMovieContent(String movieContent) {
		this.movieContent = movieContent;
	}
	public Date getMovieRdate() {
		return movieRdate;
	}
	public void setMovieRdate(Date movieRdate) {
		this.movieRdate = movieRdate;
	}
	@Override
	public String toString() {
		return "MovieDto [movieNo=" + movieNo + ", movieName=" + movieName + ", movieData=" + movieData
				+ ", movieContent=" + movieContent + ", movieRdate=" + movieRdate + "]";
	}
	
}
