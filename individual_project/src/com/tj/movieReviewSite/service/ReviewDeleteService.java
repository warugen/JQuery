package com.tj.movieReviewSite.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.movieReviewSite.dao.FreeBoardDao;
import com.tj.movieReviewSite.dao.ReviewDao;

public class ReviewDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int movieNo = Integer.parseInt(request.getParameter("movieNo"));
		int rNo = Integer.parseInt(request.getParameter("rNo"));
		String mId = request.getParameter("mId");
		ReviewDao rDao = ReviewDao.getInstance();
		int result = rDao.review_delete(movieNo, rNo, mId);
		if(result==FreeBoardDao.SUCCESS) {
			request.setAttribute("review_deleteResult", "댓글삭제완료");
		}else {
			request.setAttribute("errorMsg", "댓글삭제중 오류발생");
		}
	}

}
