package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.CmtShowDao;

public class SCmtModify implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cSno = Integer.parseInt(request.getParameter("cSno"));
		String cStext = request.getParameter("cStext");
		CmtShowDao cmtDao = CmtShowDao.getInstance();
		int result = cmtDao.cmtModify(cSno, cStext);
		
		if(result == CmtShowDao.SUCCESS) {
			request.setAttribute("resultCmtShowModify", "댓글수정성공");
		} else {
			request.setAttribute("resultCmtShowModify", "댓글수정성공");
		}
	}
}
