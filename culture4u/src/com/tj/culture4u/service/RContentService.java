package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.ReviewBoardDao;
import com.tj.culture4u.dto.ReviewBoardDto;

public class RContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// rId 가져와서 해당 글 dto로 가져오기
		int rId = Integer.parseInt(request.getParameter("rId"));
		ReviewBoardDao boardDao = ReviewBoardDao.getInstance();
		ReviewBoardDto dto = boardDao.contentView(rId);
		request.setAttribute("review_cmt_view", dto);

	}

}
