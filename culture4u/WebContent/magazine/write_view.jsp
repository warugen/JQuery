<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성하기</title>
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
		<form class="col s12" action="${conPath }/magazine_write.do" method="post" enctype="multipart/form-data">
		<div class="row">
            <div class="input-field col s9">		
                <div class="input-field">
                	<i class="material-icons prefix">mode_edit</i>
              		<input type="text" name="fTitle" id="fTitle" required="required" placeholder="제목을 입력하세요">
            	</div>
            </div>
            <div class="col s9">
            	<textarea id="summernote" name="fContent"></textarea>
            </div>
            <div class="col s3">
                <div class="file-field input-field">
                    <div class="btn">
                        <span>커버이미지선택</span>
                        <input type="file" name="fFileName" />
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
                <div class="row"></div>
                <div class="row"></div>                
                <input type="submit" value="글쓰기" class="btn">
				<input type="reset" value="취소" class="btn"> 
				<input type="button" value="목록" class="waves-effect btn" onclick="location.href='${conPath}/magazine_list.do'">
            </div>
        </div>
        <!-- 
			<table>
				<caption>글쓰기 폼</caption>
				<tr>
					<td>제목</td>
					<td><input type="text" name="fTitle" required="required"
						size="30"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea name="fContent" rows="3" cols="32"></textarea></td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td><input type="file" name="filName"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="글쓰기" class="btn">
						<input type="reset" value="취소" class="btn"> <input
						type="button" value="목록" class="btn"
						onclick="location.href='${conPath}/list.do'">
			</table>
			 -->
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