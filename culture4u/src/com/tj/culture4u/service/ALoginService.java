package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.AdminDao;
import com.tj.culture4u.dto.AdminDto;


public class ALoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//  관리자 로그인 처리
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		AdminDao aDao = AdminDao.getInstance();
		
		int result = aDao.loginCheck(mId, mPw);
		if(result == AdminDao.LOGIN_SUCCESS) {
			AdminDto aDto = aDao.getAdmin(mId);
			//HttpSession session = request.getSession();
			//session.setAttribute("admin", aDto);
			request.setAttribute("admin", aDto);
			request.setAttribute("adminLoginResult", "관리자계정으로 들어 오셨습니다.");
		} else {
			request.setAttribute("adminLoginResult", "관리자계정으로 로그인 실패하였습니다.");
		}

	}

}
