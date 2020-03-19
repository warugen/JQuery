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
	height: 30px;
	width:150px;
	background-color: #F5A9BC;
	border: 0;
	font-weight: bold;
	color: white;
}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/faqWrite.do" method="post">
			<h2>Notice</h2>
			<hr>
			<table>
				<tr>
					<td>TITLE</td>
					<td><input type="text" name="fTitle" required="required" size="30"></td>
				</tr>
				<tr>
					<td>CONTENT</td>
					<td><textarea name="fContent" rows="30" cols="30"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="공지사항 등록" class="btn">
						<input type="reset" value="취소" class="btn" onclick="locatiron.href='history.go(-1)'"> 
						<input type="button" value="목록" class="btn"
							onclick="location.href='${conPath}/faqListView.do'">
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>