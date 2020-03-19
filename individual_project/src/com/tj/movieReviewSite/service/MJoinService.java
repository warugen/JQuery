package com.tj.movieReviewSite.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.movieReviewSite.dao.MemberDao;
import com.tj.movieReviewSite.dto.MemberDto;

public class MJoinService implements Service {

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
		MemberDto member = new MemberDto(mId, mPw, mName, mBirth, mEmail, mTel, mAddress, null);
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.join_member(member);
		if (result == MemberDao.SUCCESS) {
			HttpSession session = request.getSession();
			session.setAttribute("mId", mId);
			request.setAttribute("joinResult", "회원가입을  성공하였습니다.");
		} else {
			request.setAttribute("errorMsg", "회원가입을 실패하였습니다");
		}
	}
}
