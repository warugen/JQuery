package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.CmtFreeBoardDao;

public class FCmtModify implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 댓글 수정하기
		int cFno = Integer.parseInt(request.getParameter("cFno"));
		String cFtext = request.getParameter("cFtext");
		CmtFreeBoardDao cmtDao = CmtFreeBoardDao.getInstance();
		int result = cmtDao.cmtModify(cFno, cFtext);
		
		if(result == CmtFreeBoardDao.SUCCESS) {
			// 댓글 수정 성공
			request.setAttribute("resultMsg", "댓글이 수정 되었습니다.");
		} else {
			// 댓글 수정 실패
			request.setAttribute("resultMsg", "댓글 수정이 실패 되었습니다.");
		}

	}

}
