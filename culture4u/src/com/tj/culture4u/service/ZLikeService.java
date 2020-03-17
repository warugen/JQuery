package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.culture4u.dao.CmtMagazineDao;
import com.tj.culture4u.dao.MagazineLikeDao;
import com.tj.culture4u.dto.MagazineDto;
import com.tj.culture4u.dto.MemberDto;

public class ZLikeService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		// 세션에서 mId, zId값 가져오기
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		int zId = ((MagazineDto)session.getAttribute("magazinew_view")).getzId();
		
		MagazineLikeDao mDao = MagazineLikeDao.getInstance();
		
		int result = mDao.doLike(zId, mId);
		
		if(result == MagazineLikeDao.SUCCESS) {
			// 댓글 등록 성공
			request.setAttribute("resultLike", "좋아요 등록 되었습니다.");
		} else {
			// 댓글 등록 실패
			request.setAttribute("resultLike", "좋아요 등록이 실패 되었습니다.");
		}
		
	}

}
