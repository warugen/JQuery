package com.tj.movieReviewSite.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.movieReviewSite.dao.MemberDao;

public class MJoinIdChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.join_mId_Chk(mId);
		if(result==MemberDao.EXISTENT) {
			request.setAttribute("errorMsg", "중복된 아이디입니다.");
		}else {
			request.setAttribute("mIdChkMsg", "사용가능한 아이디입니다.");
		}
	}

}
