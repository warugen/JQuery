package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.ReviewBoardDao;
import com.tj.culture4u.dto.ReviewBoardDto;

public class RModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// rId로 해당 글 가져와서 글수정화면으로 보내주기
		int rId = Integer.parseInt(request.getParameter("rId"));
		ReviewBoardDao boardDao = ReviewBoardDao.getInstance();
		ReviewBoardDto dto = boardDao.modifyView_replyView(rId);
		request.setAttribute("modify_view", dto);
	}

}
