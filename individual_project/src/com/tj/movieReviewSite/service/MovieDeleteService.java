package com.tj.movieReviewSite.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.movieReviewSite.dao.MovieDao;

public class MovieDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int movieNo = Integer.parseInt(request.getParameter("movieNo")); 
		MovieDao mDao = MovieDao.getInstance();
		int result = mDao.movie_board_delete(movieNo);
		if(result==MovieDao.SUCCESS) {
			request.setAttribute("movie_delete_result", "영화정보삭제 성공");
		}else{
			request.setAttribute("errorMsg", "영화정보삭제 삭제 실패");
		}

	}

}
