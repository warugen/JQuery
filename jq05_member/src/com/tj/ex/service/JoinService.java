package com.tj.ex.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;

public class JoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id     = request.getParameter("id");
		String pw     = request.getParameter("pw");
		String name   = request.getParameter("name");
		Date birth  = Date.valueOf(request.getParameter("birth"));
		String gender = request.getParameter("gender");
		String email  = request.getParameter("email");
		String tel    = request.getParameter("tel");
		String address= request.getParameter("address");
		
		MemberDto member = new MemberDto(id, pw, name, birth, gender, email, tel, address, null);
		MemberDao mDao = MemberDao.getInstance();
		
		int result = mDao.joinMember(member);
		if(result == MemberDao.SUCCESS) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			request.setAttribute("joinResult", "회원가입이 완료되었습니다");
			System.out.println("회원가입성공");
		}else {
			request.setAttribute("joinResult", "회원가입이 실패되었습니다");
			System.out.println("회원가입실패");
		}
	}

}
