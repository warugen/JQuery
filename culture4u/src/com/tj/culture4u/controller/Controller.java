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
import com.tj.culture4u.service.NContentService;
import com.tj.culture4u.service.NDeleteService;
import com.tj.culture4u.service.NListService;
import com.tj.culture4u.service.NModifyService;
import com.tj.culture4u.service.NModifyViewService;
import com.tj.culture4u.service.NWriteService;
import com.tj.culture4u.service.RCmtDelete;
import com.tj.culture4u.service.RCmtListService;
import com.tj.culture4u.service.RCmtModify;
import com.tj.culture4u.service.RCmtWrite;
import com.tj.culture4u.service.RContentService;
import com.tj.culture4u.service.RDeleteService;
import com.tj.culture4u.service.RListService;
import com.tj.culture4u.service.RModifyService;
import com.tj.culture4u.service.RModifyViewService;
import com.tj.culture4u.service.RReplyService;
import com.tj.culture4u.service.RReplyViewService;
import com.tj.culture4u.service.RWriteService;
import com.tj.culture4u.service.SCmtDelete;
import com.tj.culture4u.service.SCmtListService;
import com.tj.culture4u.service.SCmtModify;
import com.tj.culture4u.service.SCmtWrite;
import com.tj.culture4u.service.SContentService;
import com.tj.culture4u.service.SDeleteService;
import com.tj.culture4u.service.SListService;
import com.tj.culture4u.service.SModifyService;
import com.tj.culture4u.service.SModifyViewService;
import com.tj.culture4u.service.SWriteService;
import com.tj.culture4u.service.Service;
import com.tj.culture4u.service.ZCmtDelete;
import com.tj.culture4u.service.ZCmtListService;
import com.tj.culture4u.service.ZCmtModify;
import com.tj.culture4u.service.ZCmtWrite;
import com.tj.culture4u.service.ZContentService;
import com.tj.culture4u.service.ZDeleteService;
import com.tj.culture4u.service.ZLikeListService;
import com.tj.culture4u.service.ZLikeService;
import com.tj.culture4u.service.ZListService;
import com.tj.culture4u.service.ZModifyService;
import com.tj.culture4u.service.ZModifyViewService;
import com.tj.culture4u.service.ZUnLikeService;
import com.tj.culture4u.service.ZWriteService;

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
		 * 						후기게시판(ReviewBoard) 
		 * 
		 * *******************************************************************/
		else if (command.equals("/review_list.do")) {
			// 후기게시판으로 이동하기 FListService.java
			service = new RListService();
			service.execute(request, response);
			viewPage = "/reviewboard/list.jsp";
			
		} else if (command.equals("/review_write_view.do")) {
			// 후기게시판 글쓰기 버튼 눌렀을때 -> 글쓰기 화면으로 이동하기
			viewPage = "/reviewboard/write_view.jsp";
			
		} else if (command.equals("/review_write_view2.do")) {
			// 후기게시판 글쓰기 버튼 눌렀을때 -> 글쓰기 화면으로 이동하기
			viewPage = "/reviewboard/write_view2.jsp";
			
		} else if (command.equals("/review_write.do")) {
			// 후기게시판 글쓰기 저장하기 RWriteService.java
			service = new  RWriteService();
			service.execute(request, response);
			viewPage = "/review_list.do";
			
		} else if (command.equals("/review_content_view.do")) {
			// 후기게시판 게시글 보기 처리  -> FContentService.java
			service = new RContentService();
			service.execute(request, response);
			viewPage = "/reviewboard/content_view.jsp";
			
		} else if (command.equals("/review_boradModify_view.do")) {
			// 후기게시판 글수정하기화면 이동 처리  -> FModifyViewService.java
			service = new RModifyViewService();
			service.execute(request, response);
			viewPage = "/reviewboard/modify_view.jsp";
			
		} else if(command.equals("/review_boradModify.do")) {
			// 글수정 처리  -> BModifyService.java
			service = new RModifyService();
			service.execute(request, response);
			viewPage = "/review_list.do";
			
		} else if(command.equals("/review_delete.do")) {
			// 게시글 삭제 처리  -> BDeleteService.java
			service = new RDeleteService();
			service.execute(request, response);
			viewPage = "/review_list.do";
			
		} else if(command.equals("/review_reply_view.do")) {
			// 답변글달기 화면 이동 처리  -> BReplyViewService.java
			service = new RReplyViewService();
			service.execute(request, response);
			viewPage = "/reviewboard/reply_view.jsp";
			
		} else if(command.equals("/review_reply.do")) {
			// 답변글 달기 처리  -> BReplyService.java
			service = new RReplyService();
			service.execute(request, response);
			viewPage = "/review_list.do";
			
		} else if (command.equals("/review_cmt_list.do")) {
			// 해당 글에대한 댓글목록 가져오기 FCmtListService.java
			service = new RCmtListService();
			service.execute(request, response);
			viewPage = "/reviewboard/cmt_list.jsp";
			
		} else if (command.equals("/review_cmt_wirte.do")) {
			// 댓글 작성하기 FCmtWrite.java
			service = new RCmtWrite();
			service.execute(request, response);
			viewPage = "/review_cmt_list.do";
			
		} else if (command.equals("/review_cmt_modify.do")) {
			// 댓글 수정하기 FCmtModify.java
			service = new RCmtModify();
			service.execute(request, response);
			viewPage = "/review_cmt_list.do";
			
		} else if (command.equals("/review_cmt_delete.do")) {
			// 댓글 삭제하기 FCmtDelete.java
			service = new RCmtDelete();
			service.execute(request, response);
			viewPage = "/review_cmt_list.do";
			
		} 
		/*********************************************************************
		 * 
		 * 						메거진(Magazine) 
		 * 
		 * *******************************************************************/
		else if (command.equals("/magazine_list.do")) {
			// 메거진으로 이동하기 ZListService.java
			service = new ZListService();
			service.execute(request, response);
			viewPage = "/magazine/list.jsp";
			
		} else if (command.equals("/magazine_write_view.do")) {
			// 메거진 글쓰기 버튼 눌렀을때 -> 글쓰기 화면으로 이동하기
			viewPage = "/magazine/write_view.jsp";
			
		} else if (command.equals("/magazine_write_view2.do")) {
			// 메거진 글쓰기 버튼 눌렀을때 -> 글쓰기 화면으로 이동하기
			viewPage = "/magazine/write_view2.jsp";
			
		} else if (command.equals("/magazine_write.do")) {
			// 메거진 글쓰기 저장하기 RWriteService.java
			service = new  ZWriteService();
			service.execute(request, response);
			viewPage = "/magazine_list.do";
			
		} else if (command.equals("/magazine_content_view.do")) {
			// 메거진 게시글 보기 처리  -> FContentService.java
			service = new ZContentService();
			service.execute(request, response);
			viewPage = "/magazine/content_view.jsp";
			
		} else if (command.equals("/magazine_boradModify_view.do")) {
			// 메거진 글수정하기화면 이동 처리  -> FModifyViewService.java
			service = new ZModifyViewService();
			service.execute(request, response);
			viewPage = "/magazine/modify_view.jsp";
			
		} else if(command.equals("/magazine_boradModify.do")) {
			// 글수정 처리  -> ZModifyService.java
			service = new ZModifyService();
			service.execute(request, response);
			viewPage = "/magazine_list.do";
			
		} else if(command.equals("/magazine_delete.do")) {
			// 게시글 삭제 처리  -> BDeleteService.java
			service = new ZDeleteService();
			service.execute(request, response);
			viewPage = "/magazine_list.do";
			
		} else if (command.equals("/magazine_cmt_list.do")) {
			// 해당 글에대한 댓글목록 가져오기 FCmtListService.java
			service = new ZCmtListService();
			service.execute(request, response);
			viewPage = "/magazine/cmt_list.jsp";
			
		} else if (command.equals("/magazine_cmt_wirte.do")) {
			// 댓글 작성하기 FCmtWrite.java
			service = new ZCmtWrite();
			service.execute(request, response);
			viewPage = "/magazine_cmt_list.do";
			
		} else if (command.equals("/magazine_cmt_modify.do")) {
			// 댓글 수정하기 FCmtModify.java
			service = new ZCmtModify();
			service.execute(request, response);
			viewPage = "/magazine_cmt_list.do";
			
		} else if (command.equals("/magazine_cmt_delete.do")) {
			// 댓글 삭제하기 FCmtDelete.java
			service = new ZCmtDelete();
			service.execute(request, response);
			viewPage = "/magazine_cmt_list.do";
			
		} else if (command.equals("/magazine_like.do")) {
			// 매거진 좋아요 눌렀을때 ZLikeService.java
			service = new ZLikeService();
			service.execute(request, response);
			viewPage = "";
		} else if (command.equals("/magazine_unlike.do")) {
			// 매거진 좋아요 취소 ZUnLikeService.java
			service = new ZUnLikeService();
			service.execute(request, response);
			viewPage = "";
		} else if (command.equals("/like_list.do")) {
			// 해당 아이디로 좋아요 누른 게시물 가져오기 ZLikeListService.java
			service = new ZLikeListService();
			service.execute(request, response);
			viewPage = "";
		} 
		/*********************************************************************
		 * 
		 * 						월간공연일정(MonthlyShow) 
		 * 
		 * *******************************************************************/
		else if (command.equals("/monthly_list.do")) {
			// 월간공연일정으로 이동하기 SListService.java
			service = new SListService();
			service.execute(request, response);
			viewPage = "/monthly/list.jsp";
			
		} else if (command.equals("/monthly_write_view.do")) {
			// 월간공연일정 등록하기 버튼 눌렀을때 -> 등록 화면으로 이동하기
			viewPage = "/monthly/write_view.jsp";
			
		} else if (command.equals("/monthly_write_view2.do")) {
			// 월간공연일정 등록하기 버튼 눌렀을때 -> 등록 화면으로 이동하기
			viewPage = "/monthly/write_view2.jsp";
			
		} else if (command.equals("/monthly_write.do")) {
			// 메거진 글쓰기 저장하기 SWriteService.java
			service = new  SWriteService();
			service.execute(request, response);
			viewPage = "/magazine_list.do";
			
		} else if (command.equals("/monthly_content_view.do")) {
			// 메거진 게시글 보기 처리  -> SContentService.java
			service = new SContentService();
			service.execute(request, response);
			viewPage = "/monthly/content_view.jsp";
			
		} else if (command.equals("/monthly_boradModify_view.do")) {
			// 메거진 글수정하기화면 이동 처리  -> SModifyViewService.java
			service = new SModifyViewService();
			service.execute(request, response);
			viewPage = "/monthly/modify_view.jsp";
			
		} else if(command.equals("/monthly_boradModify.do")) {
			// 글수정 처리  -> SModifyService.java
			service = new SModifyService();
			service.execute(request, response);
			viewPage = "/monthly_list.do";
			
		} else if(command.equals("/monthly_delete.do")) {
			// 게시글 삭제 처리  -> SDeleteService.java
			service = new SDeleteService();
			service.execute(request, response);
			viewPage = "/monthly_list.do";
			
		} else if (command.equals("/monthly_cmt_list.do")) {
			// 해당 글에대한 댓글목록 가져오기 SCmtListService.java
			service = new SCmtListService();
			service.execute(request, response);
			viewPage = "/monthly/cmt_list.jsp";
			
		} else if (command.equals("/monthly_cmt_wirte.do")) {
			// 댓글 작성하기 SCmtWrite.java
			service = new SCmtWrite();
			service.execute(request, response);
			viewPage = "/monthly_cmt_list.do";
			
		} else if (command.equals("/monthly_cmt_modify.do")) {
			// 댓글 수정하기 SCmtModify.java
			service = new SCmtModify();
			service.execute(request, response);
			viewPage = "/monthly_cmt_list.do";
			
		} else if (command.equals("/monthly_cmt_delete.do")) {
			// 댓글 삭제하기 SCmtDelete.java
			service = new SCmtDelete();
			service.execute(request, response);
			viewPage = "/monthly_cmt_list.do";
			
		} 
		/*********************************************************************
		 * 
		 * 						공지사항(notice)
		 * 
		 * *******************************************************************/
		else if (command.equals("/notice_write_view.do")) {
			// 공지사항 글쓰기 버튼 눌렀을때 -> 글쓰기 화면으로 이동하기
			viewPage = "/notice/write_view.jsp";
			
		} else if (command.equals("/notice_write_view2.do")) {
			// 공지사항 글쓰기 버튼 눌렀을때 -> 글쓰기 화면으로 이동하기
			viewPage = "/notice/write_view2.jsp";
			
		} else if (command.equals("/notice_write.do")) {
			// 공지사항 글쓰기 저장하기 NWriteService.java
			service = new  NWriteService();
			service.execute(request, response);
			viewPage = "/free_list.do";
			
		} else if (command.equals("/notice_content_view.do")) {
			// 공지사항 게시글 보기 처리  -> NContentService.java
			service = new NContentService();
			service.execute(request, response);
			viewPage = "/notice/content_view.jsp";
			
		} else if (command.equals("/notice_list.do")) {
			// 공지사항으로 이동하기 NListService.java
			service = new NListService();
			service.execute(request, response);
			viewPage = "/notice/list.jsp";
			
		} else if (command.equals("/notice_boradModify_view.do")) {
			// 공지사항 글수정하기화면 이동 처리  -> NModifyViewService.java
			service = new NModifyViewService();
			service.execute(request, response);
			viewPage = "/notice/modify_view.jsp";
			
		} else if(command.equals("/notice_boradModify.do")) {
			// 공지사항 수정 처리  -> NModifyService.java
			service = new NModifyService();
			service.execute(request, response);
			viewPage = "notice_list.do";
			
		} else if(command.equals("/notice_delete.do")) {
			// 공지사항 삭제 처리  -> NDeleteService.java
			service = new NDeleteService();
			service.execute(request, response);
			viewPage = "notice_list.do";
			
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
