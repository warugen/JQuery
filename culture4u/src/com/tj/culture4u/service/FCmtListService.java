package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.CmtFreeBoardDao;

public class FCmtListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 해당글 fId에 해당하는 댓글 가져오기
		
		int fId = Integer.parseInt(request.getParameter("fId"));
		
		CmtFreeBoardDao cmtDao = CmtFreeBoardDao.getInstance();
		request.setAttribute("cmtList", cmtDao.cmtList(fId));

	}

}
