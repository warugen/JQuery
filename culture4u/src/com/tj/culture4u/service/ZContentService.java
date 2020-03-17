package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.MagazineDao;
import com.tj.culture4u.dto.MagazineDto;

public class ZContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// zId 가져와서 해당 글 dto로 가져오기
		int zId = Integer.parseInt(request.getParameter("zId"));
		MagazineDao boardDao = MagazineDao.getInstance();
		MagazineDto dto = boardDao.contentView(zId);
		request.setAttribute("magazinew_view", dto);

	}

}
