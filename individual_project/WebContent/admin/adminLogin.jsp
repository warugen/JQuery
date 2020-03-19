<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
<style>
#content_form .btn{
	width: 30%;
	height:60px;
	background-color: black;
	color: white;
	margin: 0 5px;
	border: 0;
}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<form action="${conPath }/adminLogin.do" method="post">
		<h2>ADMIN LOGIN</h2>
		<hr>	
		<table>
			<tr><th>Admin name</th>
				<td><input type="text" name="aId" placeholder="Username"  required="required" value="admin" autofocus="autofocus"></td>
			</tr>
			<tr><th>Password</th>
				<td><input type="password" name="aPw" placeholder="Password"  value="111" required="required"></td></tr>
			<tr><td colspan="2">
					<input type="submit" value="로그인" class="btn">
				</td>
			</tr>
			</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>