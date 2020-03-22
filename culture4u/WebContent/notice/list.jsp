<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!--  <link href="${conPath }/css/style.css" rel="stylesheet" />  -->
<style>
*{
	font-family: 'Noto Sans KR', sans-serif;
}
</style>
</head>
<body>
<c:set var="SUCCESS" value="1" />
<c:set var="FAIL" value="0" />
<c:if test="${writeResult eq SUCCESS }">
	<script>alert('글쓰기 성공')</script>
</c:if>
<c:if test="${writeResult eq FAIL }">
	<script>alert('글쓰기 실패')</script>
</c:if>

<c:if test="${modifyResult eq SUCCESS }">
	<script>alert('글수정 성공')</script>
</c:if>
<c:if test="${modifyResult eq FAIL }">
	<script>alert('글수정 실패')</script>
</c:if>

<c:if test="${deleteResult eq SUCCESS }">
	<script>alert('글삭제 성공')</script>
</c:if>
<c:if test="${deleteResult eq FAIL }">
	<script>alert('글삭제 실패')</script>
</c:if>

<c:if test="${replyResult eq SUCCESS }">
	<script>alert('답변글 달기 성공')</script>
</c:if>
<c:if test="${replyResult eq FAIL }">
	<script>alert('답변글 달기 실패')</script>
</c:if>
<!-- 
<c:if test="${not empty modifyResult  }">
	<script>alert('${modifyResult}');</script>
</c:if>
 -->
<jsp:include page="../main/header.jsp"/>
<div class="container center">
<table>
	<caption><h5>공지사항</h5></caption>
	<tr>
	<c:if test="${not empty admin}"> <%-- 관리자 화면 --%>
		<td>
			<a href="${conPath }/noticeWrite_view.do" class="col s1 offset-s10 waves-effect waves-teal btn lighten-1">글쓰기</a>
		</td>
	</c:if>
	</tr>
</table>
<table class="highlight" >
	<tr>
		<th class="center">글번호</th><th>작성자</th><th class="center">글제목</th><th>조회수</th><th class="center">날짜</th>
	</tr>
	<c:if test="${noticeList.size() == 0 }">
		<tr>
			<td colspan="6">해당 페이지의 글이 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${noticeList.size() != 0 }">
		<c:forEach items="${noticeList }" var="dto" >
		<tr>
			<td class="center">${dto.nId }</td>
			<td>관리자</td>
			<td class="left" >
			<a href="${conPath }/noticeContent_view.do?nId=${dto.nId}&pageNum=${pageNum}" class="black-text" >${dto.nTitle }</a>
			
			</td>
			<td>${dto.nHit }</td>
			<td class="center"><fmt:formatDate value="${dto.nRdate }" pattern="yy년MM월dd일(E)" /> </td>
		</tr>		
		</c:forEach>
	</c:if>
</table>

 <div class="row"></div>
  <ul class="pagination">
  	<c:if test="${startPage > BLOCKSIZE }">
		<li class="waves-effect"><a href="${conPath }/notice_list.do?pageNum=${startPage-1}"><i class="material-icons">chevron_left</i></a></li>
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }" >
		<c:if test="${i eq pageNum }">
		<li class="active"><a href="">${i }</a></li>
		</c:if>
		<c:if test="${i != pageNum }">
		<li class="waves-effect"><a href="${conPath }/notice_list.do?pageNum=${i}"> ${i } </a></li>
		</c:if>
	</c:forEach>
    <c:if test="${endPage < pageCnt }">
		<li class="waves-effect"><a href="${conPath }/notice_list.do?pageNum=${startPage-1}"><i class="material-icons">chevron_right</i></a></li>
	</c:if>
  </ul>

<div class="row"></div>
</div>
<!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>