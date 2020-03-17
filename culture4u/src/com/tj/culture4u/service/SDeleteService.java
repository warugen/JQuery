package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.MonthlyShowDao;

public class SDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 글 삭제하기(sId로 삭제하기)
		int sId = Integer.parseInt(request.getParameter("sId"));
		MonthlyShowDao mDao = MonthlyShowDao.getInstance();
		int result = mDao.delete(sId);
		if(result == MonthlyShowDao.SUCCESS) {
			request.setAttribute("resultShowDelete", "삭제 성공");
		} else {
			request.setAttribute("resultShowDelete", "삭제 실패");
		}

	}

}
