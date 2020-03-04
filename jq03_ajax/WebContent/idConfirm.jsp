<%@page import="com.tj.ex.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String mId = request.getParameter("mId");
	MemberDao mDao = MemberDao.getInstance();
	int result = mDao.mIdConfirm(mId);
	if(result == MemberDao.EXISTENT){
		out.print("중복된 ID입니다.");
	} else {
		out.print("사용가능한 ID입니다.");
	}
%>
</body>
</html>