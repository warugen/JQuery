package com.tj.movieReviewSite.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.movieReviewSite.dao.FaqDao;
import com.tj.movieReviewSite.dao.MovieDao;
import com.tj.movieReviewSite.dto.FaqDto;
import com.tj.movieReviewSite.dto.MovieDto;

public class MoiveModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int movieNo = Integer.parseInt(request.getParameter("movieNo"));
		MovieDao mDao = MovieDao.getInstance();
		MovieDto dto = mDao.getMovie(movieNo);
		request.setAttribute("movie_modify_view", dto);

	}

}
