package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.ReviewBoardDao;

public class RDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rId = Integer.parseInt(request.getParameter("rId"));
		ReviewBoardDao boardDao = ReviewBoardDao.getInstance();
		int result = boardDao.delete(rId);
		if(result == ReviewBoardDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
			request.setAttribute("deleteResult", 1);
		}else {
			request.setAttribute("resultMsg", "글삭제 실패");
			request.setAttribute("deleteResult", 0);
		}

	}

}
