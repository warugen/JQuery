<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>널 위한 문화예술</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<style>
	#content_form {
		width: 800px; height:500px; line-height:500px;
		margin: 0 auto; text-align: center; font-size: 3em;
	}
	
	.carousel{
		height: 500px !important;
	}
	
</style>
</head>
<body>
<c:if test="${not empty adminLoginResult }">
		<script>
			alert('${adminLoginResult}');
		</script>
	</c:if>
	<c:if test="${not empty adminLoginError }">
		<script>
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	
	<div class="carousel carousel-slider center" >
	<c:if test="${SliderList.size() != 0 }">
		<c:forEach items="${SliderList }" var="dto" >
		<a class="carousel-item black-text" href="${conPath }/magazine_content_view.do?zId=${dto.zId}" >
			<!-- 
			<img src="${conPath }/magazineUp/${dto.zFileName}">
			 -->
			<img src="https://source.unsplash.com/category/art/weekly">
			
			<h4 class="white-text">${dto.zTitle }</h4>
			
		</a>
		</c:forEach>
	</c:if>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	
	<script>
	$(document).ready(function () {
		$('.carousel.carousel-slider').carousel({
			indicators: true,
		});
		
		setInterval(function () {
			$('.carousel').carousel('next');			
		}, 2000);
	});
	</script>
	
	<jsp:include page="../main/footer.jsp"/>
	
	
</body>
</html>