package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.MonthlyShowDao;
import com.tj.culture4u.dto.MonthlyShowDto;

public class SModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// sId 가져와서 해당 글 dto로 가져오기
		int sId = Integer.parseInt(request.getParameter("zId"));
		MonthlyShowDao mDao = MonthlyShowDao.getInstance();
		MonthlyShowDto dto = mDao.contentView(sId);
		request.setAttribute("monthlyModify_view", dto);
	}

}
