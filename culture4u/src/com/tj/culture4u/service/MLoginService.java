package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.culture4u.dao.MemberDao;
import com.tj.culture4u.dto.MemberDto;

public class MLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 처리하기
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.loginCheck(mId, mPw);
		if(result == MemberDao.LOGIN_SUCCESS) {
			//  로그인 성공
			HttpSession session = request.getSession();
			MemberDto member = mDao.getMember(mId);
			session.setAttribute("member", member);
			
		} else {
			// 로그인 실패
			request.setAttribute("errorMsg", "아이디와 비번을 확인하세요");
		}
				

	}

}
