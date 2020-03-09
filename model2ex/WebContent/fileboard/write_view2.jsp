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
<!-- include libraries(jQuery, bootstrap) -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="/js/summernote-ko-KR.js"></script>
<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : '내용을 입력하세요...',
			minHeight : 350,
			maxHeight : null,
			focus : true,
			lang : 'ko-KR'
		});
	});
	$('.dropdown-toggle').dropdown()
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/write.do" method="post"
			enctype="multipart/form-data">
			<table>
				<caption>글쓰기 폼</caption>
				<tr>
					<td>제목</td>
					<td><input type="text" name="fTitle" required="required"
						size="30"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea id="summernote" name="fContent"></textarea></td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td><input type="file" name="filName"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="글쓰기" class="btn">
						<input type="reset" value="취소" class="btn"> <input
						type="button" value="목록" class="btn"
						onclick="location.href='${conPath}/list.do'"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>