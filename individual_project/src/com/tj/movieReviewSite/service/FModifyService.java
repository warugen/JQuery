package com.tj.movieReviewSite.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.movieReviewSite.dao.FaqDao;

public class FModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String fTitle = request.getParameter("fTitle");
		String fContent = request.getParameter("fContent");
		int fNo = Integer.parseInt(request.getParameter("fNo"));
		FaqDao fDao = FaqDao.getInstance();
		int result= fDao.faq_board_modify(fTitle, fContent, fNo);
		if(result==FaqDao.SUCCESS) {
			request.setAttribute("fModifyResult", "공지사항 수정 성공");
		}else {
			request.setAttribute("errorMsg", "공지사항  수정 실패");
		}
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);
	}

}
