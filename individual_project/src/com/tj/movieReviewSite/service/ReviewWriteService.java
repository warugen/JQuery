package com.tj.movieReviewSite.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.movieReviewSite.dao.ReviewDao;
import com.tj.movieReviewSite.dto.MemberDto;

public class ReviewWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		int movieNo = Integer.parseInt(request.getParameter("movieNo"));
		String rContent = request.getParameter("rContent");
		ReviewDao rDao = ReviewDao.getInstance();
		int result = rDao.review_insert(movieNo, mId, rContent);
		if(result==ReviewDao.SUCCESS) {
			request.setAttribute("review_write_result", "댓글입력완료");
		}else {
			request.setAttribute("errorMsg", "댓글입력실패");
		}
	}

}
