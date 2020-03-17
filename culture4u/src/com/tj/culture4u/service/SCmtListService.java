package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.CmtShowDao;

public class SCmtListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 댓글 목록 (sId, cSrdate DESC, mId로 mName가져오기)
		int sId = Integer.parseInt(request.getParameter("sId"));
		
		CmtShowDao cmtDao = CmtShowDao.getInstance();
		request.setAttribute("showCmtList", cmtDao.cmtList(sId));
	}

}
