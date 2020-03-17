package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.MagazineDao;
import com.tj.culture4u.dao.MagazineDao;

public class ZListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// list.do -> list.do?pageNum=1 or list.do?pageNum=5
		final int PAGESIZE = 16, BLOCKSIZE = 5;
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage -1 ) * PAGESIZE + 1;
		int endRow = (startRow + PAGESIZE) -1;
		
		MagazineDao bDao = MagazineDao.getInstance();
		request.setAttribute("list", bDao.boardList(startRow, endRow));	// 글 목록
		int totCnt = bDao.getTotCnt();
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
