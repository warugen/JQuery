<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link href="${conPath }/css/main.css" rel="stylesheet">
<link href="${conPath }/css/noticeList.css" rel="stylesheet">
<style>

</style>			
</head>
<body>
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty noticeModiFyResult}">
		<script>
			alert('${noticeModiFyResult}');
		</script>
	</c:if>
	<c:if test="${not empty noticewriteResult}">
		<script>
			alert('${noticewriteResult}');
		</script>
	</c:if>
	<c:if test="${not empty noticeDeleteResult}">
		<script>
			alert('${noticeDeleteResult}');
		</script>
	</c:if>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
				<h2>Notice</h2>
				<div class="list">
					<table class="table">
						<tr>
							<th class="th1">
								NO
							</th>
							<th class="th2" >
								SUBJECT
							</th>
							<th class="th1">
								NAME
							</th>
							<th class="th1">
								DATE
							</th>
						</tr>
					<c:if test="${empty noticeList }">
						<tr>
							<td colspan="4">
								글이없습니다.
							</td>
						</tr>
					</c:if>
					<c:forEach items="${noticeList}" var="nLists">
						<tr>
							<td>
								${nLists.nId}
							</td>
							<td style="text-align: left; padding-left: 50px;">
								<a href="${conPath }/noticeContent_view.do?nNo=${nLists.nId}&pageNum=${pageNum}">
								${nLists.nTitle }</a>
							</td>
							<td>
								관리자
							</td>
							<td>
								${nLists.nRdate}
							</td>
						
						</tr>
					</c:forEach>
					</table>
					
					<c:if test="${not empty admin }">
						<p><a href="${conPath }/noticeWrite_view.do?pageNum=${pageNum}"><img alt="write" src="${conPath }/img/btnimg/btn_board_write.jpg"> </a></p>
					</c:if>
					
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
							 	<a href="${conPath }/noticeList.do?pageNum=${startPage-1}"> 
							 		이전
							 	</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<span><b>  ${i } </b></span>
							</c:if>
							<c:if test="${i != pageNum }">
								 <span><a href="${conPath }/noticeList.do?pageNum=${i}"> ${i } </a></span>
							</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
						   		<a href="${conPath }/noticeList.do?pageNum=${endPage+1}">  
						   			다음
						   		</a>
						</c:if>
					</div>			
				</div>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>