package com.tj.culture4u.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.MonthlyShowDao;

public class SListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 현재 년/월 구하기
		SimpleDateFormat format1 = new SimpleDateFormat ( "yy/MM");
		Date time = new Date();
		String time1 = format1.format(time);
		System.out.println(time1);
		
		// 이번달 공연 가져오기
		MonthlyShowDao sDao = MonthlyShowDao.getInstance();
		request.setAttribute("showList", sDao.monthlyList(time1));
		

	}

}
