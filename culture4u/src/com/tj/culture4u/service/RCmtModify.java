package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.CmtReviewBoardDao;


public class RCmtModify implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 댓글 수정하기
		int cRno = Integer.parseInt(request.getParameter("cRno"));
		String cRtext = request.getParameter("cRtext");
		CmtReviewBoardDao cmtDao = CmtReviewBoardDao.getInstance();
		int result = cmtDao.cmtModify(cRno, cRtext);
		
		if(result == CmtReviewBoardDao.SUCCESS) {
			// 댓글 수정 성공
			request.setAttribute("resultMsg", "댓글이 수정 되었습니다.");
		} else {
			// 댓글 수정 실패
			request.setAttribute("resultMsg", "댓글 수정이 실패 되었습니다.");
		}

	}

}
