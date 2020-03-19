package com.tj.movieReviewSite.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.movieReviewSite.dao.FaqDao;
import com.tj.movieReviewSite.dto.AdminDto;
import com.tj.movieReviewSite.dto.FaqDto;

public class FWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		 HttpSession session = request.getSession();
		 String aId = ((AdminDto)session.getAttribute("admin")).getaId();
		 String fTitle = request.getParameter("fTitle");
		 String fContent = request.getParameter("fContent");
		 FaqDao fDao = FaqDao.getInstance();
		 int result = fDao.faq_board_insert(aId, fTitle, fContent);
		 if(result==FaqDao.SUCCESS) {
			 request.setAttribute("fWriteResult", "공지사항 등록이 완료되었습니다.");
		 }else {
			 request.setAttribute("errorMsg", "공지사항 등록을 실패하였습니다");
		 }
	}

}
