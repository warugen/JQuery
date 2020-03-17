package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.NoticeDao;
import com.tj.culture4u.dto.NoticeDto;

public class NContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// nid 가져와서 해당 글 dto로 가져오기
		int nId = Integer.parseInt(request.getParameter("nId"));
		NoticeDao boardDao = NoticeDao.getInstance();
		NoticeDto dto = boardDao.contentView(nId);
		request.setAttribute("notice_view", dto);

	}

}
