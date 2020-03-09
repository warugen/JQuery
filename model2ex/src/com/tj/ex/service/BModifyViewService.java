package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.BoardDao;
import com.tj.ex.dto.BoardDto;

public class BModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// fId로 해당 글 가져와서 글수정화면으로 보내주기
		int fId = Integer.parseInt(request.getParameter("fId"));
		BoardDao boardDao = BoardDao.getInstance();
		BoardDto dto = boardDao.modifyView_replyView(fId);
		request.setAttribute("modify_view", dto);

	}

}
