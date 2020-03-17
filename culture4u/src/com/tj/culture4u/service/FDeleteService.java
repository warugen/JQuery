package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.FreeBoardDao;

public class FDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fId = Integer.parseInt(request.getParameter("fId"));
		FreeBoardDao boardDao = FreeBoardDao.getInstance();
		int result = boardDao.delete(fId);
		if(result == FreeBoardDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
			request.setAttribute("deleteResult", 1);
		}else {
			request.setAttribute("resultMsg", "글삭제 실패");
			request.setAttribute("deleteResult", 0);
		}

	}

}
