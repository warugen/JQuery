package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.MemberDao;

public class MidConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// ID 중복체크 처리
		String mId = request.getParameter("mId");
		MemberDao mDao = MemberDao.getInstance();
		int result  = mDao.mIdConfirm(mId);
		
		if(result == MemberDao.NONEXISTENT) {
			request.setAttribute("idResult", "사용 가능한 ID입니다.");
		} else {
			request.setAttribute("idResult", "이미 등록된 ID입니다.");
		}

	}

}
