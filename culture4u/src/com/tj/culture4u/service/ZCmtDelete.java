package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.CmtMagazineDao;
import com.tj.culture4u.dao.CmtMagazineDao;

public class ZCmtDelete implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 댓글 삭제하기
		int cZno = Integer.parseInt(request.getParameter("cZno"));
		CmtMagazineDao cmtDao = CmtMagazineDao.getInstance();
		int result = cmtDao.cmtDelete(cZno);
		
		if(result == CmtMagazineDao.SUCCESS) {
			// 댓글 삭제 성공
			request.setAttribute("resultMsg", "댓글이 삭제 되었습니다.");
		} else {
			// 댓글 삭제 실패
			request.setAttribute("resultMsg", "댓글 삭제가 실패 되었습니다.");
		}

	}

}
