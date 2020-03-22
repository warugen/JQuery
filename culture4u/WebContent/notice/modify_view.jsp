<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 작성하기</title>
<meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container">
	<div class="row"></div>
    <div class="row"></div>
    <div class="z-depth-1 grey lighten-4 row "
            style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">
		<form action="${conPath }/notice_boradModify.do" method="post"	enctype="multipart/form-data">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="nId" value="${modify_view.nId }">
			<input type="hidden" name="dbFileName" value="${modify_view.nFileName }">

			<div class="row">
			<h5 class="header center-on-small-only">${modify_view.nId }번글 수정</h5>
            <div class="input-field col s9">		
                <div class="input-field">
                	<i class="material-icons prefix">mode_edit</i>
              		<input type="text" name="nTitle" id="nTitle" required="required" value="${modify_view.nTitle }">
            	</div>
            </div>
            <div class="col s9">
            	<textarea id="summernote" name="nContent">${modify_view.nContent }</textarea>
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
                    원첨부파일: <c:if test="${not empty modify_view.nFileName }">
							<a href="${conPath }/freeboardFiles/${modify_view.nFileName}" target="_blank">${modify_view.nFileName}</a>
						</c:if> 
						<c:if test="${empty modify_view.nFileName }">
						 		첨부파일없음
						 	</c:if>
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
				<input type="button" value="목록" class="waves-effect btn" onclick="location.href='${conPath}/notice_list.do'">
            </div>
        </div>
		</form>
		</div>
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