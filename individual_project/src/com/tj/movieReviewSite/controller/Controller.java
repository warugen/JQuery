package com.tj.movieReviewSite.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.movieReviewSite.service.AdminLoginService;
import com.tj.movieReviewSite.service.FContentService;
import com.tj.movieReviewSite.service.FListService;
import com.tj.movieReviewSite.service.FModifyService;
import com.tj.movieReviewSite.service.FModifyViewService;
import com.tj.movieReviewSite.service.FWriteService;
import com.tj.movieReviewSite.service.FdeleteService;
import com.tj.movieReviewSite.service.LogoutService;
import com.tj.movieReviewSite.service.MJoinIdChkService;
import com.tj.movieReviewSite.service.MJoinService;
import com.tj.movieReviewSite.service.MListService;
import com.tj.movieReviewSite.service.MLoginService;
import com.tj.movieReviewSite.service.MModifyService;
import com.tj.movieReviewSite.service.MoiveModifyViewService;
import com.tj.movieReviewSite.service.MovieContentService;
import com.tj.movieReviewSite.service.MovieDeleteService;
import com.tj.movieReviewSite.service.MovieModifyService;
import com.tj.movieReviewSite.service.MovieWriteService;
import com.tj.movieReviewSite.service.MoviewListService;
import com.tj.movieReviewSite.service.RAppendReviewListService;
import com.tj.movieReviewSite.service.ReviewDeleteService;
import com.tj.movieReviewSite.service.ReviewWriteService;
import com.tj.movieReviewSite.service.Service;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		Service service = null;
		String viewPage = null;
		if(command.equals("/main.do")) {
			viewPage = "main/main.jsp";
		}else if(command.equals("/joinView.do")) {
			viewPage = "member/join.jsp";
		}else if(command.equals("/joinIdChk.do")) {
			service = new MJoinIdChkService();
			service.execute(request, response);
			viewPage = "member/joinIdChk.jsp";
		}else if(command.equals("/join.do")){
			service = new MJoinService();
			service.execute(request, response);
			viewPage = "member/login.jsp";
		}else if(command.equals("/loginView.do")) {
			viewPage = "member/login.jsp"; 
		}else if(command.equals("/login.do")) {
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/logout.do")) {
			System.out.println("로그아웃 부분");
			service = new LogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/modifyView.do")) {
			viewPage = "member/modify.jsp";
		}else if(command.equals("/modify.do")) {
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}
		/* * * * * * * * * * * * * * * * * */
		/* * * * * * * * 관리자* * * * * * * */
		/* * * * * * * * * * * * * * * * * */
		else if(command.equals("/adminLoginView.do")) {
			viewPage = "admin/adminLogin.jsp";
		}else if(command.equals("/adminLogin.do")) {
			service = new AdminLoginService();
			service.execute(request, response);
			viewPage = "memberList.do";
		}else if(command.equals("/memberList.do")) {
			service = new MListService();
			service.execute(request, response);
			viewPage = "member/memberList.jsp";
		}
		/* * * * * * * * * * * * * * * * * */
		/* * * * * * * * 공지사항* * * * * * * */
		/* * * * * * * * * * * * * * * * * */
		else if(command.equals("/faqListView.do")) {
			viewPage = "faqList.do";
		}else if(command.equals("/faqList.do")) {
			service = new FListService();
			service.execute(request, response);
			viewPage = "faq/faqList.jsp";
		}else if(command.equals("/faq_write_view.do")) {
			viewPage = "faq/fWrite_view.jsp";
		}else if(command.equals("/faqWrite.do")){
			service = new FWriteService();
			service.execute(request, response);
			viewPage = "faqList.do";
		}else if(command.equals("/fContent_view.do")) {
			service = new FContentService();
			service.execute(request, response);
			viewPage = "faq/fContent_view.jsp";
		}else if(command.equals("/faq_modify_view.do")) {
			service = new FModifyViewService();
			service.execute(request, response);
			viewPage = "faq/fModify_view.jsp";
		}else if(command.equals("/fModify.do")) {
			service = new FModifyService();
			service.execute(request, response);
			viewPage = "faqList.do";
		}else if(command.equals("/faq_delete.do")){
			service = new FdeleteService();
			service.execute(request, response);
			viewPage = "faqList.do";
		}
		/* * * * * * * * * * * * * * * * * */
		/* * * * * * * * 영화정보* * * * * * * */
		/* * * * * * * * * * * * * * * * * */
		else if(command.equals("/movieList.do")) {
			service = new MoviewListService();
			service.execute(request, response);
			viewPage = "movie/movieList.jsp";
		}else if(command.equals("/movie_write_view.do")) {
			viewPage = "movie/movie_write.jsp";
		}else if(command.equals("/movieWrite.do")) {
			service = new MovieWriteService();
			service.execute(request, response);
			viewPage = "movieList.do";
		}else if(command.equals("/movie_content_view.do")) {
			service = new MovieContentService();
			service.execute(request, response);
			viewPage = "movie/movie_content.jsp";
		}else if(command.equals("/movie_modify_view.do")) {
			service = new MoiveModifyViewService();
			service.execute(request, response);
			viewPage = "movie/movie_modify_view.jsp";
		}else if(command.equals("/movie_modify.do")) {
			service = new MovieModifyService();
			service.execute(request, response);
			viewPage = "movieList.do";
		}else if(command.equals("/movie_delete.do")) {
			service = new MovieDeleteService();
			service.execute(request, response);
			viewPage ="movieList.do";
			/* * * * * * * * * * * * * * * * * */
			/* * * * * * * * 댓글(REVIEW)* * * * * * * */
			/* * * * * * * * * * * * * * * * * */	
		}else if(command.equals("/reviewList.do")) {
			viewPage = "movie_content_view.do";
		}else if(command.equals("/review_write.do")) {
			service = new ReviewWriteService();
			service.execute(request, response);
			viewPage = "movie_content_view.do";
		}else if(command.equals("/appendReviewList.do")) {
			service = new RAppendReviewListService();
			service.execute(request, response);
			viewPage = "review/appendReview.jsp";
		}else if(command.equals("/review_delete")) {
			service = new ReviewDeleteService();
			service.execute(request, response);
			viewPage = "movie_content_view.do";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
