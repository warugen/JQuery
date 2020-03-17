package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.CmtShowDao;

public class SCmtDelete implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 댓글 삭제하기
		int cSno = Integer.parseInt(request.getParameter("cSno"));
		CmtShowDao cmtDao = CmtShowDao.getInstance();
		int result = cmtDao.cmtDelete(cSno);
		
		if(result == CmtShowDao.SUCCESS) {
			request.setAttribute("resultCmtShowDelete", "댓글삭제성공");
		} else {
			request.setAttribute("resultCmtShowDelete", "댓글삭제성공");
		}

	}

}
