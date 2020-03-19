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
#content_form .btn {
	height: 30px;
	width: 150px;
	background-color: #F5A9BC;
	border: 0;
	font-weight: bold;
	color: white;
}
#content_form table tr:first-child td:first-child img {
	margin-top: 20px;
}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/movie_modify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="movieNo" value="${movie_modify_view.movieNo }">
			<input type="text" name="dbMovieData" value="${movie_modify_view.movieData }">
			<table>
				<tr>
					
					<td rowspan="3" width="100">
						<img src="${conPath }/movieData/${movie_modify_view.movieData }" alt="영화포스터">
						<input type="file" value="${conPath }/movieData/${movie_modify_view.movieData }" name="movieData">
					</td>
				</tr>
				<tr><th height="30"><input type="text" name="movieName" value="${movie_modify_view.movieName }"></th></tr>
				<tr>
					<td>
						<textarea rows="30" cols="30" name="movieContent">${movie_modify_view.movieContent }</textarea>
					</td>
				</tr>
				<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
				<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
				<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="수정" class="btn">
							<input type="reset" value="취소" class="btn">
							<input type="button" value="목록" class="btn"
								onclick="location='${conPath}/movieList.do?pageNum=${param.pageNum }'">
						</td>
					</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>