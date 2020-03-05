package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ProductDao;
import com.tj.ex.dto.ProductDto;

public class ProductService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pname = request.getParameter("pname");
		ProductDao pDao = ProductDao.getInstance();
		ProductDto product = pDao.getProduct(pname);
		if(product == null) {
			request.setAttribute("stockResult", "미취급 상품입니다.");
			
		} else {
			request.setAttribute("stockResult", product.getPstock()+"개 재고");
		}
	}

}
