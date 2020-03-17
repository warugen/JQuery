package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.FreeBoardDao;
import com.tj.culture4u.dto.FreeBoardDto;

public class FModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// fId로 해당 글 가져와서 글수정화면으로 보내주기
		int fId = Integer.parseInt(request.getParameter("fId"));
		FreeBoardDao boardDao = FreeBoardDao.getInstance();
		FreeBoardDto dto = boardDao.modifyView_replyView(fId);
		request.setAttribute("modify_view", dto);
	}

}
