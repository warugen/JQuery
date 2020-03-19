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
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!--  <link href="${conPath }/css/style.css" rel="stylesheet" />  -->
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
	<caption><h5>자유게시판</h5></caption>
	<tr>
	<c:if test="${empty member}"> <%-- 로그인 전 화면 --%>
		<td> <h6>글쓰기는 사용자 로그인 이후에만 가능합니다. </h6></td>
	</c:if>
	<c:if test="${not empty member}"> <%-- 로그인 후 화면 --%>
	<!-- 
		<td><a href="${conPath }/free_write_view.do"> 글 쓰 기 </a></td>
		<td><a href="${conPath }/free_write_view2.do"> 글 쓰 기2 </a></td>
		 -->
		 <td>
		 <a href="${conPath }/free_write_view.do" class="col s1 offset-s10 waves-effect waves-teal btn lighten-1">글쓰기</a>
		 </td>
	</c:if>
	</tr>
</table>
<table class="highlight" >
	<tr>
		<th>글번호</th><th>작성자</th><th>글제목</th><th>조회수</th><th>날짜</th><th>IP</th>
	</tr>
	<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="6">해당 페이지의 글이 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${list.size() != 0 }">
		<c:forEach items="${list }" var="dto" >
		<tr>
			<td>${dto.fId }</td>
			<td>${dto.mName }</td>
			<td class="left" >
			<c:forEach var="i" begin="1" end="${dto.fIndent }" >
			<!-- 답변글 들여쓰기 -->
			<c:if test="${i != dto.fIndent }">
				&nbsp;
			</c:if>
			<c:if test="${i eq dto.fIndent }">
				<i class="material-icons">subdirectory_arrow_right</i>
			</c:if>
			</c:forEach>
			<a href="${conPath }/free_content_view.do?fId=${dto.fId}&pageNum=${pageNum}" class="black-text" >${dto.fTitle }</a>
			
			</td>
			<td>${dto.fHit }</td>
			<td><fmt:formatDate value="${dto.fRdate }" pattern="yy년MM월dd일(E)" /> </td>
			<td>${dto.fIp }</td>
		</tr>		
		</c:forEach>
	</c:if>
</table>
<!-- 
<div class="paging">
	<c:if test="${startPage > BLOCKSIZE }">
	[ <a href="${conPath }/list.do?pageNum=${startPage-1}"> 이전 </a> ]
	</c:if>
	<c:forEach var="i" begin="${startPage }" end="${endPage }" >
		<c:if test="${i eq pageNum }">
		[ <b> ${i } </b> ]
		</c:if>
		<c:if test="${i != pageNum }">
		[ <a href="${conPath }/list.do?pageNum=${i}"> ${i } </a> ]
		</c:if>
	</c:forEach>
	<c:if test="${endPage < pageCnt }">
	[ <a href="${conPath }/list.do?pageNum=${endPage+1}"> 다음 </a> ]
	</c:if>
</div>
 -->
 <div class="row"></div>
  <ul class="pagination">
  	<c:if test="${startPage > BLOCKSIZE }">
		<li class="waves-effect"><a href="${conPath }/free_list.do?pageNum=${startPage-1}"><i class="material-icons">chevron_left</i></a></li>
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }" >
		<c:if test="${i eq pageNum }">
		<li class="active"><a href="">${i }</a></li>
		</c:if>
		<c:if test="${i != pageNum }">
		<li class="waves-effect"><a href="${conPath }/free_list.do?pageNum=${i}"> ${i } </a></li>
		</c:if>
	</c:forEach>
    <c:if test="${endPage < pageCnt }">
		<li class="waves-effect"><a href="${conPath }/free_list.do?pageNum=${startPage-1}"><i class="material-icons">chevron_right</i></a></li>
	</c:if>
  </ul>

<div class="row"></div>
</div>
<!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>