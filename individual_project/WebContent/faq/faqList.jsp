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
#content_form .admin{
		width:150px;
		margin-bottom: 10px;
}
#content_form .admin input{
	height: 30px;
	width:150px;
	background-color: #F5A9BC;
	border: 0;
	font-weight: bold;
	color: white;
}
</style>
<script  src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
	$(document).ready(function() {
		$('tr').hover(function() {
			$(this).css('cursor','pointer');
		});
		$('tr').click(function() {
			var fNo = Number($(this).children(0).eq(0).text()); 
				if (!isNaN(fNo)) {
					location.href = '${conPath}/fContent_view.do?fNo='+fNo+'&pageNum=${pageNum}';
				}
		});
	});
</script>
</head>
<body>
	<c:if test="${not empty errorMsg }">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${empty errorMsg and not empty fWriteResult}">
		<script>
			alert('${fWriteResult}');
		</script>
	</c:if>
	<c:if test="${not empty fdeleteResult }">
		<script>
			alert('${fdeleteResult}');
		</script>
	</c:if>
	<c:if test="${not empty fModifyResult }">
		<script>
			alert('${fModifyResult}');
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<table>
			<tr><th>번호</th><th>작성자</th><th>제목</th><th>등록일</th><th>조회수</th></tr>
			<c:if test="${totCnt==0 }">
				<tr>
					<td colspan="5">등록된글이 없습니다</td>
				</tr>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${faqlist }" var="dto">
					<tr>
						<td>${dto.fNo }</td>
						<td>${dto.aName }</td>
						<td class="left"> 
							${dto.fTitle }
						</td>
						<td><fmt:formatDate value="${dto.fRdate }" pattern="yy.M.d" /></td>
						<td>${dto.fHit }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div class="paging">
			<c:if test="${startPage >BLOCKSIZE}">
				<a href="${conPath }/faqList.do?pageNum=${startPage - 1}">&lt;&lt; 이전 </a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
					[ <a href="${conPath }/faqList.do?pageNum=${i}"> ${i } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
		  		 <a href="${conPath }/faqList.do?pageNum=${endPage + 1}">다음 &gt;&gt;</a> 
			</c:if>
		</div>
		<br><br>
		<c:if test="${empty member and not empty admin }">
			<div class="admin">
					<input type="button" value="공지사항 등록" onclick="location.href='${conPath}/faq_write_view.do'">
			</div>
		</c:if>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>