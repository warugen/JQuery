package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;

public class CkIdService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// ID 중복체크 처리
		String id = request.getParameter("id");
		MemberDao mDao = MemberDao.getInstance();
		int result  = mDao.checkId(id);
		
		if(result == MemberDao.NON_EXISTENT) {
			request.setAttribute("idResult", "사용 가능한 ID입니다.");
		} else {
			request.setAttribute("idResult", "이미 등록된 ID입니다.");
		}

	}

}
