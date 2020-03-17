package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.CmtMagazineDao;

public class ZCmtListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 해당글 zId에 해당하는 댓글 가져오기		
		int zId = Integer.parseInt(request.getParameter("zId"));
		
		CmtMagazineDao cmtDao = CmtMagazineDao.getInstance();
		request.setAttribute("zCmtList", cmtDao.cmtList(zId));
	}

}
