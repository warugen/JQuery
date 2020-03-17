package com.tj.culture4u.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.MemberDao;
import com.tj.culture4u.dto.MemberDto;

public class MAllViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 전체 회원목록 가져오기
		// pageNum받고, startRow, endRow 계산해서
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 3, BLOCKSIZE=5;
		int startRow = (currentPage-1)*PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		// dao의 list(startRow, endRow) 실행결과를 request.setAttribute
		MemberDao mDao = MemberDao.getInstance();
		ArrayList<MemberDto> members = mDao.allMember(startRow, endRow);
		request.setAttribute("mAllView", members);
		// totCnt, pageCnt, startPage, endPage, BLOCKSIZE, pageNum
		//    => request.setAttribute
		int totCnt = mDao.getMemberTotCnt();
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE +1;
		int endPage   = startPage + BLOCKSIZE -1 ;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("pageNum", currentPage);

	}

}
