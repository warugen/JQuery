package com.tj.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.service.CkEmailService;
import com.tj.ex.service.CkIdService;
import com.tj.ex.service.JoinService;
import com.tj.ex.service.LoginService;
import com.tj.ex.service.Service;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
		
		if(command.equals("/ckId.do")) {
			// service의 execute 호출 : CkIdService.java
			service = new CkIdService();
			service.execute(request, response);
			viewPage = "/member/chId.jsp";
			
		} else if (command.equals("/chEmail.do")) {
			// service의 execute 호출 : CkEmailService.java
			service = new CkEmailService();
			service.execute(request, response);
			viewPage = "/member/chEmail.jsp";
			
		} else if (command.equals("/join.do")) {
			// service의 execute 호출 : JoinService.java
			service = new JoinService();
			service.execute(request, response);
			viewPage = "/member/login.jsp";
			
		} else if (command.equals("/login.do")) {
			// service의 execute 호출 : LoginService.java
			System.out.println("111111");
			service = new LoginService();
			service.execute(request, response);
			viewPage = "/main/main.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}

