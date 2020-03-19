package com.tj.movieReviewSite.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.movieReviewSite.dao.MemberDao;
import com.tj.movieReviewSite.dto.MemberDto;

public class MListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=5 , BLOCKSIZE=3;
		int startRow = (currentPage-1)*PAGESIZE + 1;
		int endRow = startRow+PAGESIZE -1 ;
		MemberDao mDao = MemberDao.getInstance();
		ArrayList<MemberDto> members = mDao.MemberList(startRow, endRow);
		request.setAttribute("members", members);
		int totCnt = mDao.getMemberTotCnt();
		int pageCnt = (int) Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", currentPage);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
	}
}
