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
	<c:if test="${zmodifyResult eq SUCCESS }">
		<script>
			alert('글수정 성공')
		</script>
	</c:if>
	<c:if test="${zmodifyResult eq FAIL }">
		<script>
			alert('글수정 실패')
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div class="container">
		<div class="row">
		<div class="row"></div>
		<div class="row"></div>
        <form class="col s12" action="${conPath}/magazine_boradModify_view.do"  method="post">
        	<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="zId" value="${magazinew_view.zId }">
			
            <section class="teal lighten-4" style="padding:1px;">
                <div class="container">
                    <div class="row row-noclear">
                    <div class="section">
					    <h3 class="center">${magazinew_view.zTitle }</h3>
					    <p class="right"><fmt:formatDate value="${magazinew_view.zRdate }" pattern="yyyy년MM월dd일(E)일 작성" /></p>
					    
				  	</div>
                        <article class="card col s12 m12" style="animation-duration: 0.5s;" class="animated bounceInUp">
                            <section class="card-content">
                                <div class="card-content">
                                    <pre>${magazinew_view.zContent}</pre>
                                </div>
                            </section>
                        </article>
                        
						<c:if test="${not empty admin}">
							<input type="submit" value="수정" class="btn">
							<input type="button" value="삭제" class="btn"
								onclick="location='${conPath}/magazine_delete.do?zId=${magazinew_view.zId }&pageNum=${param.pageNum }'">
						</c:if> 

						<input type="button" value="목록" class="waves-effect waves-light btn"
						onclick="location='${conPath}/magazine_list.do?pageNum=${param.pageNum }'">
						<!-- 
                        <a class="waves-effect waves-light btn"><i class="material-icons left">save</i>수정</a>
                        <input type="button" class="btn" value="삭제">
                        <input type="button" class="btn white-text grey" value="목록">
                         -->
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