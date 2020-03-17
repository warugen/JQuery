package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.culture4u.dao.CmtShowDao;
import com.tj.culture4u.dto.MemberDto;

public class SCmtWrite implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 댓글 입력하기 cSno, sId, mId, cStext
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		int sId = Integer.parseInt(request.getParameter("sId"));
		String cStext = request.getParameter("cStext");
		
		CmtShowDao cmtDao = CmtShowDao.getInstance();
		int result = cmtDao.cmtWrite(mId, sId, cStext);
		
		if(result == CmtShowDao.SUCCESS) {
			// 댓글 등록 성공
			request.setAttribute("resultCmtShow", "댓글동록성공");
		} else {
			request.setAttribute("resultCmtShow", "댓글동록실패");
		}

	}

}
