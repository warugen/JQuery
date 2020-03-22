package com.tj.culture4u.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.dao.MagazineDao;

public class MagazineSlider implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 매거진 슬라이더 가져오기
		MagazineDao bDao = MagazineDao.getInstance();
		request.setAttribute("SliderList", bDao.sliderList());	// 슬라이더 가져오기
	}

}
