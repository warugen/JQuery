package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;

public class CkEmailService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// Email 중복 체크
		String email = request.getParameter("email");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.checkEmail(email);
		
		if(result == MemberDao.NON_EXISTENT) {
			request.setAttribute("emailResult", "가입 가능한 메일입니다.");
		} else {
			request.setAttribute("emailResult", "이미 등록된 메일입니다.");
		}

	}

}
