package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.culture4u.dao.CmtFreeBoardDao;
import com.tj.culture4u.dao.CmtMagazineDao;
import com.tj.culture4u.dto.MagazineDto;
import com.tj.culture4u.dto.MemberDto;

public class ZCmtWrite implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 세션에서 mId, fId값 가져오기
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		//int fId= Integer.parseInt(request.getParameter("fId"));
		int zId = ((MagazineDto)session.getAttribute("magazinew_view")).getzId();
		String cZtext = request.getParameter("cZtext");
		
		CmtMagazineDao cmtDao = CmtMagazineDao.getInstance();
		int result = cmtDao.cmtWrite(mId, zId, cZtext);
		
		if(result == CmtMagazineDao.SUCCESS) {
			// 댓글 등록 성공
			request.setAttribute("resultMsg", "댓글이 등록 되었습니다.");
		} else {
			// 댓글 등록 실패
			request.setAttribute("resultMsg", "댓글 등록이 실패 되었습니다.");
		}

	}

}
