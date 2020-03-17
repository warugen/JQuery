package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.ReviewBoardDao;
import com.tj.culture4u.dao.ReviewBoardDao;
import com.tj.culture4u.dto.ReviewBoardDto;
import com.tj.culture4u.dto.ReviewBoardDto;

public class RReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rId = Integer.parseInt(request.getParameter("rId"));
		ReviewBoardDao boardDao = ReviewBoardDao.getInstance();
		ReviewBoardDto dto      = boardDao.modifyView_replyView(rId);
		request.setAttribute("reply_view", dto);
	}

}
