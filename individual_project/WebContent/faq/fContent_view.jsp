<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
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
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/faq_modify_view.do" method="post">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="fNo" value="${fContent_view.fNo }">
			<h2>Notice</h2>
			<hr>
			<table>
				<tr>
					<th>TITLE</th>
					<td>${fContent_view.fTitle }</td>
				</tr>
				<tr>
					<th>CONTENT</th>
					<td>${fContent_view.fContent }</td>
				</tr>
				<c:if test="${empty member and not empty admin }">
					<tr>
						<td colspan="2">
							<input type="submit" value="수정" class="btn">
							<input type="button" value="삭제" class="btn" onclick="location='${conPath}/faq_delete.do?fNo=${fContent_view.fNo }&pageNum=${param.pageNum }'">
							<input type="button" value="목록" class="btn"
								onclick="location='${conPath}/faqListView.do?pageNum=${param.pageNum }'">
						</td>
					</tr>
				</c:if>
				<c:if test="${empty admin }">
					<tr>
						<td colspan="2">
							<input type="button" value="목록" class="btn"
							onclick="location.href='${conPath}/faqListView.do'">
						</td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>