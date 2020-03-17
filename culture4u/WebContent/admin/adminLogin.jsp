<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${conPath }/css/style.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/adminlogin.do" method="post">
			<table>
				<caption>관리자 모드 로그인</caption>
				<tr>
					<th>ID</th>
					<td><input type="text" name="mId" value="${mId }"
						required="required"></td>
				</tr>
				<tr>
					<th>PW</th>
					<td><input type="password" name="mPw" required="required"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="로그인">
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>