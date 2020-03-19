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
#content_form {
	border: 0;
}

#content_form .btn {
	height: 30px;
	width: 150px;
	background-color: #F5A9BC;
	border: 0;
	font-weight: bold;
	color: white;
}

#content_form #reviewWrite table {
	height: 150px;
	border: 1px solid #cccccc;
	box-sizing: border-box;
}

#content_form #reviewWrite #memberWrite table tr td {
	height: 100px;
	overflow: hidden;
}

#content_form #reviewWrite table tr td h5 {
	float: left;
	margin-left: 50px;
}

#content_form #reviewWrite tr td input.btn {
	width: 80px;
	height: 60px;
	margin-right: 50px;
	float: right;
}

#content_form #reviewList table {
	margin-top: 30px;
	height: 70px;
	border-bottom: 1px solid #cccccc;
}
#content_form #reviewList table tr{
	overflow: hidden;
}
#content_form #reviewList table tr td:nth-child(1){
	float:left;
	font-weight: bold;
	border-right: 2px solid #cccccc;

}
#content_form #reviewList table tr td:nth-child(2){
	float: left;
	font-size: 0.5;
	border: 1px solid red;
}
#content_form #reviewList table tr td:nth-child(2){
	font-size: 0.5;
	border: 1px solid red;
	box-sizing: border-box;
}

#content_form .paging b {
	color: white;
}

#content_form #ReviewListMoreCover {
	width: 80px;
}

#content_form #ReviewListMoreCover #ReviewListMore {
	margin: 0 auto;
	text-align: center;
	font-weight: bold;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
	$(document).ready(function() {
		if (Number('${totCnt}') < 6) {
			$('#ReviewListMore').css('display', 'none');
		}
		$('#ReviewListMore').click(function() {
			$(this).css('cursor', 'pointer');
			var pageNum = Number($('#pageNum').val()) + 1;
			$('#pageNum').val(pageNum);
			$.ajax({
				url : '${conPath}/appendReviewList.do',
				type : 'get',
				data : 'pageNum='+ pageNum+ '&movieNo=${movie_content_view.movieNo }',
				dataType : 'html',
				success : function(data) {
					$('#appendReviewList').append(data);
				},
				error : function(code) {
					alert(code.status);
				}
			});
		});
	});
</script>
</head>
<c:if test="${not empty review_write_result }">
	<script>
		alert('${review_write_result}');
	</script>
</c:if>
<c:if test="${not empty errorMsg }">
	<script>
		alert('${errorMsg}');
	</script>
</c:if>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/movie_modify_view.do" method="post">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="movieNo"
				value="${movie_content_view.movieNo }">
			<table>
				<tr>
					<td rowspan="3" width="100"><c:if
							test="${not empty movie_content_view.movieData }">
							<img src="${conPath }/movieData/${movie_content_view.movieData }"
								alt="영화포스터">
						</c:if></td>
				</tr>
				<tr>
					<th height="30">${movie_content_view.movieName }</th>
				</tr>
				<tr>
					<td>${movie_content_view.movieContent }</td>
				</tr>
				<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
				<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
				<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
				<c:if test="${empty member and not empty admin }">
					<tr>
						<td colspan="2"><input type="submit" value="Modify"
							class="btn"> <input type="button" value="Delete"
							class="btn"
							onclick="location='${conPath}/movie_delete.do?movieNo=${movie_content_view.movieNo }&pageNum=${param.pageNum }'">
							<input type="button" value="List" class="btn"
							onclick="location='${conPath}/movieList.do?pageNum=${param.pageNum }'">
						</td>
					</tr>
				</c:if>
				<c:if test="${empty admin }">
					<tr>
						<td colspan="3"><input type="button" value="List" class="btn"
							onclick="location.href='${conPath}/movieList.do'"></td>
					</tr>
				</c:if>
			</table>
		</form>
		<!-- 댓글입력창 -->
		<div id="reviewWrite">
			<form action="${conPath }/review_write.do" method="post">
				<input type="hidden" name="movieNo"
					value="${movie_content_view.movieNo }">
				<c:if test="${empty member }">
					<table>
						<tr>
							<td><input type="text" placeholder="댓글을 작성하려면 로그인 해주세요"
								style="height: 50px;"></td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty member }">
					<div id="memberWrite">
						<table>
							<tr>
								<td><c:if test="${not empty member }">
										<h5>${member.mName }</h5>
									</c:if> <br> <textarea rows="5" cols="3" name="rContent"></textarea><br>
									<input type="submit" value="등록" class="btn"></td>
							</tr>
						</table>
					</div>
				</c:if>
			</form>
		</div>
		<!-- 댓글목록 -->
		<div id="reviewList">
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${reviewlist }" var="dto">
					<table>
						<tr>
							<td rowspan="2" class="left">${dto.mName }</td>
							<td rowspan="2" class="left">${dto.rContent }</td>
						</tr>
						<tr><td>${dto.rRdate }</td></tr>
					</table>
				</c:forEach>
			</c:if>
		</div>
		<div id="appendReviewList">
			
		</div>
		<input type="hidden" id="pageNum" value="1">
		<div id="ReviewListMoreCover">
			<span id="ReviewListMore">더보기</span>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>