package com.tj.culture4u.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.service.ALoginService;
import com.tj.culture4u.service.Service;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		
		/*********************************************************************
		 * 
		 * 							관리자(admin) 
		 * 
		 * *******************************************************************/
		if (command.equals("/main.do")) {
			// 메인화면 호출
			viewPage = "/main/main.jsp";
			
		} else if (command.equals("/adminloginView.do")) {
			// 관리자 로그인 페이지 호출
			
			viewPage = "/admin/adminLogin.jsp";
			
		} else if (command.equals("/adminlogin.do")) {
			// admin login 처리 ALoginService.java
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "/allView.do";
			
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		} else if (command.equals("")) {
			viewPage = "";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
