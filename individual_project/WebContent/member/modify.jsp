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
	width: 100px;
	background-color: black;
	color: white;
	margin: 0 5px;
	border: 0;
}
</style>
</head>
<body>
	<c:if test="${not empty errorMsg }">
		<script>
			alert('${errorMsg}');
			history.go(-1);
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<form action="${conPath }/modify.do" method="post">
		<h2>개인정보수정</h2>
		<hr>
		<table>
			<tr><th>아이디</th>
				<td><input type="text" name="mId" required="required" value="${member.mId }"></td>
			</tr>
			<tr><th>비밀번호</th>	
				<td><input type="password" name="mPw" required="required"></td>
			</tr>
			<tr><th>이름</th>
				<td><input type="text" name="mName" required="required" value="${member.mName }"></td>
			<tr><th>생년월일</th>
				<td><input type="date" name="mBirth" value="${member.mBirth }"></td>
			</tr>
			<tr><th>메일</th>
				<td><input type="text" name="mEmail" value="${member.mEmail }"></td>
			</tr>
			<tr><th>전화번호</th>
				<td><input type="text" name="mTel" value="${member.mTel }"></td>
			</tr>
			<tr><th>주소</th>
				<td><input type="text" name="mAddress" value="${member.mAddress }"></td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="정보수정" class="btn">
						<input type="reset" value="취소" class="btn">
						<input type="reset" value="이전" class="btn" onclick="history.go(-1)">
				</td>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>