package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.CmtFreeBoardDao;

public class FCmtDelete implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 댓글 삭제하기
		int cFno = Integer.parseInt(request.getParameter("cFno"));
		CmtFreeBoardDao cmtDao = CmtFreeBoardDao.getInstance();
		int result = cmtDao.cmtDelete(cFno);
		
		if(result == CmtFreeBoardDao.SUCCESS) {
			// 댓글 삭제 성공
			request.setAttribute("resultMsg", "댓글이 삭제 되었습니다.");
		} else {
			// 댓글 삭제 실패
			request.setAttribute("resultMsg", "댓글 삭제가 실패 되었습니다.");
		}

	}

}
