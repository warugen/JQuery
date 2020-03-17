package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.MagazineDao;
import com.tj.culture4u.dto.MagazineDto;

public class ZModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// zId로 해당 글 가져와서 글수정화면으로 보내주기
		int zId = Integer.parseInt(request.getParameter("zId"));
		MagazineDao boardDao = MagazineDao.getInstance();
		MagazineDto dto = boardDao.modifyView(zId);
		request.setAttribute("modify_view", dto);

	}

}
