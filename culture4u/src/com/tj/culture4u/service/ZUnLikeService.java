package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.culture4u.dao.MagazineLikeDao;
import com.tj.culture4u.dao.MagazineLikeDao;
import com.tj.culture4u.dto.MagazineDto;
import com.tj.culture4u.dto.MemberDto;

public class ZUnLikeService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 댓글 삭제하기
		// 세션에서 mId, zId값 가져오기
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		int zId = ((MagazineDto)session.getAttribute("magazinew_view")).getzId();
		
		MagazineLikeDao cmtDao = MagazineLikeDao.getInstance();
		int result = cmtDao.cancleLike(zId, mId);
		
		if(result == MagazineLikeDao.SUCCESS) {
			// 좋아요 삭제 성공
			request.setAttribute("resultUnLike", "좋아요가 삭제 되었습니다.");
		} else {
			// 좋아요 삭제 실패
			request.setAttribute("resultUnLike", "좋아요 삭제가 실패 되었습니다.");
		}

	}

}
