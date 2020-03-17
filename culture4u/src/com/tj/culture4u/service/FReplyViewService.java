package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.FreeBoardDao;
import com.tj.culture4u.dto.FreeBoardDto;

public class FReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fId = Integer.parseInt(request.getParameter("fId"));
		FreeBoardDao boardDao = FreeBoardDao.getInstance();
		FreeBoardDto dto      = boardDao.modifyView_replyView(fId);
		request.setAttribute("reply_view", dto);
	}

}
