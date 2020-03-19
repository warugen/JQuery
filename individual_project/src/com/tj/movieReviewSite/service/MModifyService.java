package com.tj.movieReviewSite.service;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tj.movieReviewSite.dao.MemberDao;
import com.tj.movieReviewSite.dto.MemberDto;
public class MModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			String mId = request.getParameter("mId");
			String mPw = request.getParameter("mPw");
			String mName = request.getParameter("mName");
			String mBirthStr = request.getParameter("mBirth");
			Date mBirth = null;
			if (!mBirthStr.equals("")) {
				mBirth = Date.valueOf(mBirthStr);
			}
			String mEmail = request.getParameter("mEmail");
			String mTel = request.getParameter("mTel");
			String mAddress = request.getParameter("mAddress");
			MemberDao mDao = MemberDao.getInstance();
			MemberDto member = new MemberDto(mId, mPw, mName, mBirth, mEmail, mTel, mAddress, null);
			int result = mDao.modify_meber(member);
			if(result==MemberDao.SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				request.setAttribute("modifyResult", "정보수정이 완료되었습니다.");
			}else {
				request.setAttribute("errorMsg", "정보수정중 오류가 발생하였습니다.");
			}
		
	}
}
