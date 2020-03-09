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
<link href="${conPath }/css/style.css" rel="stylesheet" />
</head>
<body>

	<c:set var="SUCCESS" value="1" />
	<c:set var="FAIL" value="0" />
	<c:if test="${modifyResult eq SUCCESS }">
		<script>
			alert('글수정 성공')
		</script>
	</c:if>
	<c:if test="${modifyResult eq FAIL }">
		<script>
			alert('글수정 실패')
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath}/boradModify_view.do" method="post">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="fId" value="${content_view.fId }">
			<table>
				<caption>${content_view.fId }글상세보기</caption>
				<tr>
					<td>작성자</td>
					<td>${content_view.mName}(${content_view.mId})님</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>${content_view.fTitle }</td>
				</tr>
				<tr>
					<td>본문</td>
					<td><pre>${content_view.fContent}</pre></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><c:if test="${not empty content_view.fFileName }">
							<a href="${conPath }/fileboardUp/${content_view.fFileName}"
								target="_blank">${content_view.fFileName}</a>
						</c:if> <c:if test="${empty content_view.fFileName }">
						 		첨부파일없음
						 	</c:if></td>
				</tr>
				<tr>
					<td colspan="2"><c:if
							test="${member.mId eq content_view.mId }">
							<input type="submit" value="수정" class="btn">
						</c:if> <c:if test="${member.mId eq content_view.mId or not empty admin}">
							<input type="button" value="삭제" class="btn"
								onclick="location='${conPath}/delete.do?fId=${content_view.fId }&pageNum=${param.pageNum }'">
						</c:if> <c:if test="${not empty member }">
							<input type="button" value="답변" class="btn"
								onclick="location='${conPath}/reply_view.do?fId=${content_view.fId}&pageNum=${param.pageNum}'">
						</c:if> <input type="button" value="목록" class="btn"
						onclick="location='${conPath}/list.do?pageNum=${param.pageNum }'">
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>