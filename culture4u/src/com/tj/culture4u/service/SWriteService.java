package com.tj.culture4u.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.MonthlyShowDao;

public class SWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// sTitle, sContent, sStartDate, sEndDate, sPlace, sIp
		
		String sTitle = request.getParameter("sTitle");
		String sContent = request.getParameter("sContent");
		String sStartDayStr = request.getParameter("sStartDay");
		String sEndDayStr = request.getParameter("sEndDay");
		Date sStartDate = null;
		Date sEndDate = null;
		if(!sStartDayStr.equals("")) {
			sStartDate = Date.valueOf(sStartDayStr);
		}
		if(!sEndDayStr.equals("")) {
			sEndDate = Date.valueOf(sEndDayStr);
		}
		String sPlace = request.getParameter("sPlace");
		String sIp = request.getRemoteAddr();
		
		MonthlyShowDao mDao = MonthlyShowDao.getInstance();
		int result = mDao.registShow(sTitle, sContent, sStartDate, sEndDate, sPlace, sIp);
		
		if(result == MonthlyShowDao.SUCCESS) {
			// 공연 등록 성공
			request.setAttribute("resultShowMsg", "글쓰기 성공");
		} else {
			// 공연 등록 실패
			request.setAttribute("resultShowMsg", "글쓰기 성공");
		}

	}

}
