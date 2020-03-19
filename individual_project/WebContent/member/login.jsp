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
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${empty member and not empty errorMsg}">
		<script>
			alert('${errorMsg}');
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<form action="${conPath }/login.do" method="post">
		<h2>LOGIN</h2>
		<hr>
		<table>
			<tr><th>User name</th>
				<td><input type="text" name="mId" value="${mId }" placeholder="Username"  required="required" autofocus="autofocus"></td>
			</tr>
			<tr><th>Password</th>
				<td><input type="password" name="mPw" placeholder="Password"  required="required"></td>
			</tr>
			<tr><td colspan="2">
					<input type="submit" value="로그인" class="btn">
	 				<input type="button" value="회원가입"  class="btn" onclick="location.href='${conPath}/joinView.do'">
				</td>
			</tr>
			</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>