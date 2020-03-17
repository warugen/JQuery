package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.culture4u.dao.CmtFreeBoardDao;
import com.tj.culture4u.dto.FreeBoardDto;
import com.tj.culture4u.dto.MemberDto;
import com.tj.culture4u.dto.ReviewBoardDto;

public class RCmtWrite implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 세션에서 mId, fId값 가져오기
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		//int fId= Integer.parseInt(request.getParameter("fId"));
		int rId = ((ReviewBoardDto)session.getAttribute("review_cmt_view")).getrId();
		String cRtext = request.getParameter("cRtext");
		
		CmtFreeBoardDao cmtDao = CmtFreeBoardDao.getInstance();
		int result = cmtDao.cmtWrite(mId, rId, cRtext);
		
		if(result == CmtFreeBoardDao.SUCCESS) {
			// 댓글 등록 성공
			request.setAttribute("resultMsg", "댓글이 등록 되었습니다.");
		} else {
			// 댓글 등록 실패
			request.setAttribute("resultMsg", "댓글 등록이 실패 되었습니다.");
		}

	}

}
