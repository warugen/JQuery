package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.NoticeDao;

public class NDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nId = Integer.parseInt(request.getParameter("nId"));
		NoticeDao boardDao = NoticeDao.getInstance();
		int result = boardDao.delete(nId);
		if(result == NoticeDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
			request.setAttribute("deleteResult", 1);
		}else {
			request.setAttribute("resultMsg", "글삭제 실패");
			request.setAttribute("deleteResult", 0);
		}

	}

}
