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
	</div>
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>