package com.tj.movieReviewSite.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.movieReviewSite.dao.FaqDao;
import com.tj.movieReviewSite.dao.MovieDao;
import com.tj.movieReviewSite.dao.ReviewDao;
import com.tj.movieReviewSite.dto.FaqDto;
import com.tj.movieReviewSite.dto.MovieDto;
import com.tj.movieReviewSite.dto.ReviewDto;

public class MovieContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int movieNo = Integer.parseInt(request.getParameter("movieNo"));
		int dbMovieNo = movieNo;
		MovieDao mDao = MovieDao.getInstance();
		MovieDto dto = mDao.getMovie(dbMovieNo);
		request.setAttribute("movie_content_view", dto);
		
		/* *********** ********↓ ↓ ↓ ↓ ↓ ↓ 댓글리스트 부분 ↓ ↓ ↓ ↓ ↓ ↓ ↓ **************** ********************/
		String review_PageNum = request.getParameter("pageNum");
		if(review_PageNum==null) review_PageNum="1";
		int currentPage = Integer.parseInt(review_PageNum);
		final int PAGESIZE=5, BLOCKSIZE=1;
		int startRow = (currentPage-1)*PAGESIZE +1;
		int endRow = startRow + PAGESIZE - 1;
		ReviewDao rDao = ReviewDao.getInstance();
		ArrayList<ReviewDto> reviewlist = rDao.review_list(movieNo, startRow, endRow);
		request.setAttribute("reviewlist", reviewlist);
		int totCnt = rDao.getReviewTotCnt(movieNo);
		int pageCnt = (int) Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE +1;
		int endPage = startPage + BLOCKSIZE -1 ;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("review_PageNum", currentPage);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);

	}

}
