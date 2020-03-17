package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.NoticeDao;
import com.tj.culture4u.dao.NoticeDao;
import com.tj.culture4u.dto.FreeBoardDto;
import com.tj.culture4u.dto.NoticeDto;

public class NModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// nId로 해당 글 가져와서 글수정화면으로 보내주기
		int nId = Integer.parseInt(request.getParameter("nId"));
		NoticeDao boardDao = NoticeDao.getInstance();
		NoticeDto dto = boardDao.modifyView(nId);
		request.setAttribute("modify_view", dto);
	}

}
