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
#content_form{
 border: 0;
}
 #content_form td:hover{ 
 	background-color: #cccccc; 
 	opacity: 0.4; 
 	cursor: pointer;
} 
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
#content_form b{color: white; }
</style>
<script  src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
	function movie_content_view(movieNo){
		location.href = '${conPath}/movie_content_view.do?movieNo='+movieNo+'&pageNum=${pageNum}';
	}
</script>
<c:if test="${not empty errorMsg }">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${empty errorMsg and not empty movieResult}">
		<script>
			alert('${movieResult}');
		</script>
	</c:if>
	<c:if test="${not empty movie_modifyResult }">
		<script>
			alert('${movie_modifyResult}');
		</script>
	</c:if>
	<c:if test="${not empty movie_delete_result }">
		<script>
			alert('${movie_delete_result}');
		</script>
	</c:if>
</head>
<body>
	
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<table>
			<c:if test="${totCnt==0 }">
				<tr>
					<td colspan="5">등록된영화가 없습니다</td>
				</tr>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<tr>
					<c:forEach items="${movieList }" var="dto" varStatus="idx">
						
							<td><img src="${conPath }/movieData/${dto.movieData }" alt="영화포스터" id="img" onclick="movie_content_view(${dto.movieNo})"></td>
						
						<c:if test="${idx.index%4 eq 3 }">
							
				</tr>
						<tr>
						
						</c:if>
					</c:forEach>
				</tr>
			</c:if>
		</table>
		<div class="paging">
			<c:if test="${startPage >BLOCKSIZE}">
				<a href="${conPath }/movieList.do?pageNum=${startPage - 1}">◀◀</a>
			</c:if>
	 		<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b>  ○  </b>
				</c:if>
				<c:if test="${i != pageNum }">
					[ <a href="${conPath }/movieList.do?pageNum=${i}"> ${i} </a> ]
				</c:if>
			</c:forEach> 
			<c:if test="${endPage<pageCnt }">
		  		 <a href="${conPath }/movieList.do?pageNum=${endPage + 1}">▶▶</a> 
			</c:if>
		</div>
		<br><br>
		<c:if test="${empty member and not empty admin }">
			<div class="admin">
					<input type="button" value="Movie Upload" onclick="location.href='${conPath}/movie_write_view.do'">
			</div>
		</c:if>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>