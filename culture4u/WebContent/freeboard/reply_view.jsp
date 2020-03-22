<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변글 작성하기</title>
<meta name="viewport" content="width = device-width, initial-scale = 1">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container" id="content_form">
		<!-- 파라미터 : bid, pageNum -->
		<!-- request의 attribute : reply_view(원글의 dto) -->
		<form class="col s12" action="${conPath }/free_reply.do" method="post" enctype="multipart/form-data">
			<!-- reply.do시 필요한 정보 원글 : bGroup, bStep, bIndent
		                       지금저장할 답변글 : bName, bTitle, bContent, pageNum -->
			<input type="hidden" name="fGroup" value="${reply_view.fGroup }">
			<input type="hidden" name="fStep" value="${reply_view.fStep }">
			<input type="hidden" name="fIndent" value="${reply_view.fIndent }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<!-- 
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
			 -->
			<div class="row">
			<h5 class="header center-on-small-only">${reply_view.fId }번글 답변</h5>
            <div class="input-field col s9">		
                <div class="input-field">
                	<input disabled value="${member.mName }(${member.mId })" id="disabled" type="text" class="validate">
          			<label for="disabled">작성자</label>
            	</div>
            </div>
            <div class="input-field col s9">		
                <div class="input-field">
                	<i class="material-icons prefix">mode_edit</i>
              		<input type="text" name="fTitle" id="fTitle" required="required" value="[답]${reply_view.fTitle }">
            	</div>
            </div>
            <div class="col s9">
            	<textarea id="summernote" name="fContent"></textarea>
            </div>
            <div class="col s3">
                <div class="file-field input-field">
                    <div class="btn">
                        <span>파일첨부</span>
                        <input type="file" name="fFileName"/>
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" placeholder="Upload file" />
                    </div>
                </div>
				<!-- 
                <a class="waves-effect waves-light btn" onclick="submit();"><i class="material-icons left">save</i>저장</a>
                <input type="reset" class="btn white-text grey" value="취소">
                <input type="button" value="목록" class="waves-effect btn" onclick="location.href='${conPath}/free_list.do'">
                 -->
                파일 크기는 최대 10M입니다.
                <div class="row"></div>
                <div class="row"></div>
                <input type="submit" value="글쓰기" class="btn">
				<input type="reset" value="취소" class="btn"> 
				<input type="button" value="목록" class="waves-effect btn" onclick="location.href='${conPath}/free_list.do'">
            </div>
        </div>
		</form>
	</div>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : '내용을 입력하세요...',
			height: 350,
	        minHeight: null,
	        maxHeight: null,
	        lang : 'ko-KR',
	        onImageUpload: function(files, editor, welEditable) {
	                sendFile(files[0], editor, welEditable);
	            }
		});
	});
	$('.dropdown-toggle').dropdown();
</script>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>