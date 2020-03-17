package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.MagazineDao;
import com.tj.culture4u.dao.MagazineDao;

public class ZDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int zId = Integer.parseInt(request.getParameter("zId"));
		MagazineDao boardDao = MagazineDao.getInstance();
		int result = boardDao.delete(zId);
		if(result == MagazineDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
			request.setAttribute("deleteResult", 1);
		}else {
			request.setAttribute("resultMsg", "글삭제 실패");
			request.setAttribute("deleteResult", 0);
		}

	}

}
