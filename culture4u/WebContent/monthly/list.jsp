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

<!-- 
<c:if test="${not empty modifyResult  }">
	<script>alert('${modifyResult}');</script>
</c:if>
 -->
<jsp:include page="../main/header.jsp"/>
<div class="container center">
<h4 class="center"><span class="teal-text">매거진</span></h4>
<%-- 관리자일때 글작성 --%>
	<c:if test="${not empty admin}"> 
		 <div class="row">
		 <a href="${conPath }/magazine_write_view.do" class="col s1 offset-s10 waves-effect waves-teal btn lighten-1">등록하기</a>
		 </div>
	</c:if>
	
<c:if test="${Mlist.size() == 0 }">
		<h4 class="center"><span class="black-text">해당 페이지의 글이 없습니다.</span></h4>
		
</c:if>
<c:if test="${Mlist.size() != 0 }">


<div class="row">
<div class="z-depth-1 grey lighten-4 row "
            style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">
<c:forEach items="${Mlist }" var="dto" >
<div class="col s12 m3"> <!-- 카드 배치 1열에 4개씩 -->
<a href="${conPath }/magazine_content_view.do?zId=${dto.zId}&pageNum=${pageNum}" class="black-text">
<div class="card small hoverable"> <!-- 카드 크기 -->
<div class="card-image "> 
<!-- 카드 이미지 -->
<img src="${conPath }/magazineUp/${dto.zFileName}">
</div>
<div class="card-content">
<!-- 카드 제목 -->
<p>${dto.zTitle }</p>

</div>

</div>
</a>
</div>
</c:forEach>
<div class="row"></div>
</div>
</div>
				
</c:if>

 <div class="row"></div>
  <ul class="pagination">
  	<c:if test="${startPage > BLOCKSIZE }">
		<li class="waves-effect"><a href="${conPath }/magazine_list.do?pageNum=${startPage-1}"><i class="material-icons">chevron_left</i></a></li>
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }" >
		<c:if test="${i eq pageNum }">
		<li class="active"><a href="">${i }</a></li>
		</c:if>
		<c:if test="${i != pageNum }">
		<li class="waves-effect"><a href="${conPath }/magazine_list.do?pageNum=${i}"> ${i } </a></li>
		</c:if>
	</c:forEach>
    <c:if test="${endPage < pageCnt }">
		<li class="waves-effect"><a href="${conPath }/magazine_list.do?pageNum=${endPage+1}"><i class="material-icons">chevron_right</i></a></li>
	</c:if>
  </ul>

<div class="row"></div>
</div>
<!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>