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
	<form action="${conPath }/movieWrite.do" method="post"  enctype="multipart/form-data">
		<h2>Movie List Up</h2>
		<hr>
		<table>
			<tr>
				<th>Movie Name</th>
				<td><input type="text" name="movieName" id="movieName" required="required" value="그랑고">
				</td>
			</tr>
			<tr>
				<th>Movie Poster</th>
				<td><input type="file" name="movieData" id="movieData" required="required"></td>
			</tr>
			<tr>
				<th>Movie Content</th>
				<td><textarea rows="30" cols="30" id="movieContent" name="movieContent">그랑고</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="영화등록" class="btn">
					<input type="reset" value="취소" class="btn">
					<input type="button" value="목록" class="btn" onclick="location.href='${conPath}/movieList.do'">
				</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>