package com.tj.movieReviewSite.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.movieReviewSite.dao.AdminDao;
import com.tj.movieReviewSite.dao.MemberDao;
import com.tj.movieReviewSite.dto.AdminDto;

public class AdminLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aId = request.getParameter("aId");
		String aPw = request.getParameter("aPw");
		AdminDao aDao = AdminDao.getInstance();
		int result = aDao.admin_loginChk(aId, aPw);
		if(result==AdminDao.ADMIN_LOGIN_SUCCESS) {
			HttpSession session = request.getSession();
			AdminDto admin = aDao.getAdmin(aId);
			session.setAttribute("admin", admin);
			request.setAttribute("adminResult", "관리자계정로그인");
		}else {
			request.setAttribute("errorMsg", "관리자계정 로그인 실패");
		}
	}

}
