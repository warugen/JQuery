package com.tj.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.service.ALoginService;
import com.tj.ex.service.BContentService;
import com.tj.ex.service.BDeleteService;
import com.tj.ex.service.BListService;
import com.tj.ex.service.BModifyService;
import com.tj.ex.service.BModifyViewService;
import com.tj.ex.service.BReplyService;
import com.tj.ex.service.BReplyViewService;
import com.tj.ex.service.BWriteService;
import com.tj.ex.service.MAllViewService;
import com.tj.ex.service.MImgUpload;
import com.tj.ex.service.MJoinSerivce;
import com.tj.ex.service.MLoginService;
import com.tj.ex.service.MModifyService;
import com.tj.ex.service.MidConfirmService;
import com.tj.ex.service.MlogoutService;
import com.tj.ex.service.Service;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class MController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MController() {
        super();
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
		
		if(command.equals("/adminloginView.do")) {
			// 관리자 로그인 페이지 호출
			
			viewPage = "/admin/adminLogin.jsp";
			
		} else if (command.equals("/adminlogin.do")) {
			// admin login 처리 ALoginService.java
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "/allView.do";
			
		} else if (command.equals("/list.do")) {
			// 고객센터로(게시판목록) 이동하기 BListService.java
			service = new BListService();
			service.execute(request, response);
			viewPage = "/fileboard/list.jsp";
			
		} else if (command.equals("/joinView.do")) {
			// 회원가입 페이지로 이동하기
			viewPage = "/member/join.jsp";
			
		} else if (command.equals("/ckId.do")) {
			// 중복 ID 체크 MidConfirmService.java
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "/member/idConfirm.jsp";
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		} else if (command.equals("/imgUp.do")) {
			// 이미지업로드하기 MImgUpload.java
			service = new MImgUpload();
			service.execute(request, response);
			viewPage = "/fileboard/imgUp.jsp";
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		} else if (command.equals("/join.do")) {
			// 회원가입 처리 MJoinSerivce.java
			service = new MJoinSerivce();
			service.execute(request, response);
			viewPage = "/loginView.do";
			
		} else if (command.equals("/loginView.do")) {
			// 로그인 페이지로 이동하기
			viewPage = "/member/login.jsp";
			
		} else if (command.equals("/login.do")) {
			// 로그인 처리 MLoginService.java
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "/list.do";
			
		} else if (command.equals("/allView.do")) {
			// 회원목록화면 페이지로 이동 MAllViewService.java
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "/member/mAllView.jsp";
			
		} else if (command.equals("/logout.do")) {
			// 로그아웃 처리 MlogoutService.java
			service = new MlogoutService();
			service.execute(request, response);
			viewPage = "/main.do";
			
		} else if (command.equals("/main.do")) {
			viewPage = "/main/main.jsp";
			
		} else if (command.equals("/modifyView.do")) {
			// 회원정보 수정화면 처리 MModifyService.java
			viewPage = "/member/modify.jsp";
			
		} else if (command.equals("/modify.do")) {
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "/list.do";
			
		} else if (command.equals("/write_view.do")) {
			// 글쓰기 버튼 눌렀을때 -> 글쓰기 화면으로 이동하기
			viewPage = "/fileboard/write_view.jsp";
			
		} else if (command.equals("/write_view2.do")) {
			// 글쓰기 버튼 눌렀을때 -> 글쓰기 화면으로 이동하기
			viewPage = "/fileboard/write_view2.jsp";
			
		} else if (command.equals("/write.do")) {
			// 글쓰기 저장하기 BWriteService.java
			service = new  BWriteService();
			service.execute(request, response);
			viewPage = "/list.do";
			
		} else if (command.equals("/content_view.do")) {
			// 게시글 보기 처리  -> BContentService.java
			service = new BContentService();
			service.execute(request, response);
			viewPage = "fileboard/content_view.jsp";
			
		} else if (command.equals("/boradModify_view.do")) {
			// 글수정하기화면 이동 처리  -> BModifyViewService.java
			service = new BModifyViewService();
			service.execute(request, response);
			viewPage = "fileboard/modify_view.jsp";
			
		} else if(command.equals("/boradModify.do")) {
			// 글수정 처리  -> BModifyService.java
			service = new BModifyService();
			service.execute(request, response);
			viewPage = "list.do";
			
		} else if(command.equals("/delete.do")) {
			// 게시글 삭제 처리  -> BDeleteService.java
			service = new BDeleteService();
			service.execute(request, response);
			viewPage = "list.do";
			
		} else if(command.equals("/reply_view.do")) {
			// 답변글달기 화면 이동 처리  -> BReplyViewService.java
			service = new BReplyViewService();
			service.execute(request, response);
			viewPage = "fileboard/reply_view.jsp";
			
		} else if(command.equals("/reply.do")) {
			// 답변글 달기 처리  -> BReplyService.java
			service = new BReplyService();
			service.execute(request, response);
			viewPage = "list.do";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
