package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.CmtMagazineDao;

public class ZCmtModify implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 댓글 수정하기
		int cZno = Integer.parseInt(request.getParameter("cZno"));
		String cZtext = request.getParameter("cZtext");
		CmtMagazineDao cmtDao = CmtMagazineDao.getInstance();
		int result = cmtDao.cmtModify(cZno, cZtext);
		
		if(result == CmtMagazineDao.SUCCESS) {
			// 댓글 수정 성공
			request.setAttribute("resultMsg", "댓글이 수정 되었습니다.");
		} else {
			// 댓글 수정 실패
			request.setAttribute("resultMsg", "댓글 수정이 실패 되었습니다.");
		}

	}

}
