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
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container">
	<div class="row"></div>
    <div class="row"></div>
    <div class="row"></div>
		<form action="${conPath }/free_boradModify.do" method="post"	enctype="multipart/form-data">
			<input type="text" name="pageNum" value="${param.pageNum }">
			<input type="text" name="fId" value="${modify_view.fId }">
			<input type="text" name="dbFileName" value="${modify_view.fFileName }">
			<!-- 
			<table>
				<caption>${modify_view.fId }번글 수정</caption>
				<tr>
					<th>작성자</th>
					<td><input type="text" required="required" size="30"
						value="${modify_view.mName }(${modify_view.mId })"
						readonly="readonly"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="fTitle" required="required"
						size="30" value="${modify_view.fTitle }"></td>
				</tr>
				<tr>
					<th>본문</th>
					<td><textarea rows="5" cols="32" name="fContent">${modify_view.fContent }</textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="fFileName" class="btn">
						원첨부파일: <c:if test="${not empty modify_view.fFileName }">
							<a href="${conPath }/fileboardUp/${modify_view.fFileName}"
								target="_blank">${modify_view.fFileName}</a>
						</c:if> <c:if test="${empty modify_view.fFileName }">
						 		첨부파일없음
						 	</c:if></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="수정" class="btn">
						<input type="button" value="목록" class="btn"
						onclick="location='${conPath}/list.do?pageNum=${param.pageNum }'">
						<input type="reset" value="취소" class="btn"
						onclick="history.back()"></td>
				</tr>
			</table>
			 -->
			<div class="row">
			<h5 class="header center-on-small-only">${modify_view.fId }번글 수정</h5>
            <div class="input-field col s9">		
                <div class="input-field">
                	<input disabled value="${modify_view.mName }(${modify_view.mId })" id="disabled" type="text" class="validate">
          			<label for="disabled">작성자</label>
            	</div>
            </div>
            <div class="input-field col s9">		
                <div class="input-field">
                	<i class="material-icons prefix">mode_edit</i>
              		<input type="text" name="fTitle" id="fTitle" required="required" value="${modify_view.fTitle }">
            	</div>
            </div>
            <div class="col s9">
            	<textarea id="summernote" name="fContent">${modify_view.fContent }</textarea>
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
                    원첨부파일: <c:if test="${not empty modify_view.fFileName }">
							<a href="${conPath }/freeboardFiles/${modify_view.fFileName}" target="_blank">${modify_view.fFileName}</a>
						</c:if> 
						<c:if test="${empty modify_view.fFileName }">
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
				<input type="button" value="목록" class="waves-effect btn" onclick="location.href='${conPath}/free_list.do'">
            </div>
        </div>
		</form>
	</div>
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