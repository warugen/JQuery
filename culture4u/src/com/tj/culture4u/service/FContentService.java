package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.FreeBoardDao;
import com.tj.culture4u.dto.FreeBoardDto;

public class FContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// fid 가져와서 해당 글 dto로 가져오기
		int fId = Integer.parseInt(request.getParameter("fId"));
		FreeBoardDao boardDao = FreeBoardDao.getInstance();
		FreeBoardDto dto = boardDao.contentView(fId);
		request.setAttribute("free_cmt_view", dto);
	}

}
