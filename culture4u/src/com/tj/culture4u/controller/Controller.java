package com.tj.culture4u.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.culture4u.service.ALoginService;
import com.tj.culture4u.service.FCmtDelete;
import com.tj.culture4u.service.FCmtListService;
import com.tj.culture4u.service.FCmtModify;
import com.tj.culture4u.service.FCmtWrite;
import com.tj.culture4u.service.FContentService;
import com.tj.culture4u.service.FDeleteService;
import com.tj.culture4u.service.FListService;
import com.tj.culture4u.service.FModifyService;
import com.tj.culture4u.service.FModifyViewService;
import com.tj.culture4u.service.FReplyService;
import com.tj.culture4u.service.FReplyViewService;
import com.tj.culture4u.service.FWriteService;
import com.tj.culture4u.service.MAllViewService;
import com.tj.culture4u.service.MImgUpload;
import com.tj.culture4u.service.MJoinSerivce;
import com.tj.culture4u.service.MLoginService;
import com.tj.culture4u.service.MModifyService;
import com.tj.culture4u.service.MidConfirmService;
import com.tj.culture4u.service.MlogoutService;
import com.tj.culture4u.service.Service;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
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
			viewPage = "/main.do";
			
		} else if (command.equals("/logout.do")) {
			// 관리자 / 회원 -  로그아웃 처리 MlogoutService.java
			service = new MlogoutService();
			service.execute(request, response);
			viewPage = "/main.do";
			
		} else if (command.equals("/allView.do")) {
			// 회원목록화면 페이지로 이동 MAllViewService.java
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "/member/mAllView.jsp";
			
		}
		/*********************************************************************
		 * 
		 * 							회원(member) 
		 * 
		 * *******************************************************************/
		else if (command.equals("/loginView.do")) {
			// 로그인 페이지로 이동하기
			viewPage = "/member/login.jsp";
			
		} else if (command.equals("/login.do")) {
			// 로그인 처리 MLoginService.java
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "/list.do";
			
		}  else if (command.equals("/joinView.do")) {
			// 회원가입 페이지로 이동하기
			viewPage = "/member/join.jsp";
			
		} else if (command.equals("/ckId.do")) {
			// 중복 ID 체크 MidConfirmService.java
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "/member/idConfirm.jsp";

		} else if (command.equals("/imgUp.do")) {
			// summernote에 이미지업로드하고 저장 경로 설정 MImgUpload.java
			service = new MImgUpload();
			service.execute(request, response);
			viewPage = "/fileboard/imgUp.jsp";
		
		} else if (command.equals("/join.do")) {
			// 회원가입 처리 MJoinSerivce.java
			service = new MJoinSerivce();
			service.execute(request, response);
			viewPage = "/loginView.do";
			
		} else if (command.equals("/modifyView.do")) {
			// 회원정보 수정화면으로 이동
			viewPage = "/member/modify.jsp";
			
		} else if (command.equals("/modify.do")) {
			// 회원정보 수정화면 처리 MModifyService.java
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "/list.do";
			
		} 
		/*********************************************************************
		 * 
		 * 						자유게시판(FreeBoard) 
		 * 
		 * *******************************************************************/
		else if (command.equals("/free_write_view.do")) {
			// 자유게시판 글쓰기 버튼 눌렀을때 -> 글쓰기 화면으로 이동하기
			viewPage = "/freeboard/write_view.jsp";
			
		} else if (command.equals("/free_write_view2.do")) {
			// 자유게시판 글쓰기 버튼 눌렀을때 -> 글쓰기 화면으로 이동하기
			viewPage = "/freeboard/write_view2.jsp";
			
		} else if (command.equals("/free_write.do")) {
			// 자유게시판 글쓰기 저장하기 FWriteService.java
			service = new  FWriteService();
			service.execute(request, response);
			viewPage = "/free_list.do";
			
		} else if (command.equals("/free_content_view.do")) {
			// 자유게시판 게시글 보기 처리  -> FContentService.java
			service = new FContentService();
			service.execute(request, response);
			viewPage = "/freeboard/content_view.jsp";
			
		} else if (command.equals("/free_list.do")) {
			// 자유게시판으로 이동하기 FListService.java
			service = new FListService();
			service.execute(request, response);
			viewPage = "/freeboard/list.jsp";
			
		} else if (command.equals("/free_boradModify_view.do")) {
			// 자유게시판 글수정하기화면 이동 처리  -> FModifyViewService.java
			service = new FModifyViewService();
			service.execute(request, response);
			viewPage = "/freeboard/modify_view.jsp";
			
		} else if(command.equals("/free_boradModify.do")) {
			// 글수정 처리  -> BModifyService.java
			service = new FModifyService();
			service.execute(request, response);
			viewPage = "free_list.do";
			
		} else if(command.equals("/free_delete.do")) {
			// 게시글 삭제 처리  -> BDeleteService.java
			service = new FDeleteService();
			service.execute(request, response);
			viewPage = "free_list.do";
			
		} else if(command.equals("/free_reply_view.do")) {
			// 답변글달기 화면 이동 처리  -> BReplyViewService.java
			service = new FReplyViewService();
			service.execute(request, response);
			viewPage = "/freeboard/reply_view.jsp";
			
		} else if(command.equals("/free_reply.do")) {
			// 답변글 달기 처리  -> BReplyService.java
			service = new FReplyService();
			service.execute(request, response);
			viewPage = "free_list.do";
			
		} else if (command.equals("/free_cmt_list.do")) {
			// 해당 글에대한 댓글목록 가져오기 FCmtListService.java
			service = new FCmtListService();
			service.execute(request, response);
			viewPage = "/freeboard/cmt_list.jsp";
			
		} else if (command.equals("/free_cmt_wirte.do")) {
			// 댓글 작성하기 FCmtWrite.java
			service = new FCmtWrite();
			service.execute(request, response);
			viewPage = "/free_cmt_list.do";
			
		} else if (command.equals("/free_cmt_modify.do")) {
			// 댓글 수정하기 FCmtModify.java
			service = new FCmtModify();
			service.execute(request, response);
			viewPage = "/free_cmt_list.do";
			
		} else if (command.equals("/free_cmt_delete.do")) {
			// 댓글 삭제하기 FCmtDelete.java
			service = new FCmtDelete();
			service.execute(request, response);
			viewPage = "/free_cmt_list.do";
			
		} 
		/*********************************************************************
		 * 
		 * 						후기게시판(FreeBoard) 
		 * 
		 * *******************************************************************/
		else if (command.equals("")) {
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
