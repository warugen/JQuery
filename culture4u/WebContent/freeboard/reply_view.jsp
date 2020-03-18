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
			lang: "ko-KR"
			/*,
			callbacks: {	//여기 부분이 이미지를 첨부하는 부분
				onImageUpload : function(files) {
					console.log(files);
					uploadSummernoteImageFile(files[0],this);
				}
			}
		*/
		});
	});
	$('.dropdown-toggle').dropdown();
	
	/**
	* 이미지 파일 업로드
	*/
	function uploadSummernoteImageFile(file, editor) {
		data = new FormData();
		data.append("file", file);
		$.ajax({
			data : data,
			type : "POST",
			//url : "/fileboardUp",
			url : "${conPath }/imgUp.do",
			enctype: 'multipart/form-data',
			cache: false,
			contentType : false,
			processData : false,
			success : function(data) {
            	//항상 업로드된 파일의 url이 있어야 한다.
            	//console.log("img.src = "+img.src);
            	console.log("data.url = "+data);
            	
				//$(editor).summernote("insertImage", data.url);
				$(editor).summernote("insertImage", data);
			},
			error: function (data) {
				console.log(data);
			}
		});
	}
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<!-- 파라미터 : bid, pageNum -->
		<!-- request의 attribute : reply_view(원글의 dto) -->
		<form action="${conPath }/free_reply.do" method="post" enctype="multipart/form-data">
			<!-- reply.do시 필요한 정보 원글 : bGroup, bStep, bIndent
		                       지금저장할 답변글 : bName, bTitle, bContent, pageNum -->
			<input type="hidden" name="fGroup" value="${reply_view.fGroup }">
			<input type="hidden" name="fStep" value="${reply_view.fStep }">
			<input type="hidden" name="fIndent" value="${reply_view.fIndent }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<caption>${reply_view.fId }번글의답변쓰기 폼</caption>
				<tr>
					<td>작성자</td>
					<td>${member.mName }(${member.mId })</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="fTitle" required="required"
						size="30" value="[답]${reply_view.fTitle }"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea id="summernote" name="fContent"></textarea></td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td><input type="file" name="fFilName"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="답변쓰기" class="btn">
						<input type="reset" value="취소" class="btn"> 
						<input type="button" value="목록" class="btn" onclick="location.href='${conPath}/free_list.do'">
					</td>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>