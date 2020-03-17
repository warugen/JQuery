package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.CmtReviewBoardDao;

public class RCmtListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 해당글 fId에 해당하는 댓글 가져오기		
		int rId = Integer.parseInt(request.getParameter("rId"));
		
		CmtReviewBoardDao cmtDao = CmtReviewBoardDao.getInstance();
		request.setAttribute("rCmtList", cmtDao.cmtList(rId));

	}

}
