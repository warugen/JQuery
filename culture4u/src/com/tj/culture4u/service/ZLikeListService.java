package com.tj.culture4u.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.culture4u.dao.MagazineLikeDao;
import com.tj.culture4u.dto.MagazineLikeDto;
import com.tj.culture4u.dto.MemberDto;

public class ZLikeListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		final int PAGESIZE = 3, BLOCKSIZE = 5;
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage -1 ) * PAGESIZE + 1;
		int endRow = (startRow + PAGESIZE) -1;
		
		// 세션에서 mId, fId값 가져오기
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		
		MagazineLikeDao mDao = MagazineLikeDao.getInstance();
		request.setAttribute("likeList", mDao.likeList(mId, startRow, endRow));
		
		int totCnt = mDao.getTotCnt(mId);
		int pageCnt = (int)Math.ceil((double)totCnt / PAGESIZE);
		int startPage = ((currentPage -1) / BLOCKSIZE ) * BLOCKSIZE + 1;
		int endPage = (startPage + BLOCKSIZE) -1;
		
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", currentPage);
		request.setAttribute("pageCnt", pageCnt);
		
	}

}
