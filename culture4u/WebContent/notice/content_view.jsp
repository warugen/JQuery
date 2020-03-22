<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width = device-width, initial-scale = 1">
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!--Import materialize.css-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!--  <link href="${conPath }/css/style.css" rel="stylesheet">-->
<style>
*{
	font-family: 'Noto Sans KR', sans-serif;
}
</style>
</head>
<body>

	<c:set var="SUCCESS" value="1" />
	<c:set var="FAIL" value="0" />
	<c:if test="${NoticeModifyResult eq SUCCESS }">
		<script>
			alert('글수정 성공')
		</script>
	</c:if>
	<c:if test="${NoticeModifyResult eq FAIL }">
		<script>
			alert('글수정 실패')
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div class="container">
		<div class="row">
		<div class="row"></div>
		<div class="row"></div>
        <form class="col s12" action="${conPath}/notice_boradModify_view.do"  method="post">
        	<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="nId" value="${notice_view.nId }">
			
            <section class="teal lighten-4" style="padding:1px;">
                <div class="container">
                    <div class="row row-noclear">
                    <div class="section">
					    <h3 class="center">${notice_view.nTitle }</h3>
					    <p class="right"><fmt:formatDate value="${notice_view.nRdate }" pattern="yyyy년MM월dd일(E)일 작성" /></p>
					    <p class="right">관리자&nbsp;&nbsp;</p>
				  	</div>
				  	<!-- 
                        	<h2 class="header center-on-small-only">${free_view.fTitle }</h2>
                         
                            <h5 class="light text-lighten-4 center-on-small-only">${free_view.mName}(${free_view.mId})님 </h5>
                            <h5 class="light text-lighten-4 center-on-small-only"><fmt:formatDate value="${free_view.fRdate }" pattern="yyyy년MM월dd일(E)일 작성" /> </h5>
                          -->   
                        
                        <h6 class="light text-lighten-4 center-on-small-only">
                        <c:if test="${not empty notice_view.nFileName }">
							첨부파일 : <a class="red-text" href="${conPath }/adminUp/${notice_view.nFileName }"	target="_blank">${notice_view.nFileName}</a>
						</c:if> 
						<c:if test="${empty notice_view.nFileName }">
							첨부파일없음
						</c:if> </h6>

                        <article class="card col s12 m12" style="animation-duration: 0.5s;" class="animated bounceInUp">
                            <section class="card-content">
                                <div class="card-content">
                                    <pre>${notice_view.nContent}</pre>
                                </div>
                            </section>
                        </article>
                        <c:if
							test="${not empty admin }">
							<input type="submit" value="수정" class="btn">
							<input type="button" value="삭제" class="btn"
								onclick="location='${conPath}/notice_delete.do?nId=${notice_view.nId }&pageNum=${param.pageNum }'">
						</c:if>  
						<input type="button" value="목록" class="btn"
						onclick="location='${conPath}/notice_list.do?pageNum=${param.pageNum }'">

                    </div>
                </div>

            </section>
        </form>
        </div>
	</div>
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>