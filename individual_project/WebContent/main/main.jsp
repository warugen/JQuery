<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#content{
	width:2000px;
	height:1000px;
	overflow: hidden;
	margin: 0 auto;
}
#content #mainimage {
	width:100%;
	height:1000px;
	background-image: url('${conPath}/img/actor.jpg');
	background-repeat: no-repeat;
	overflow: hidden;
	margin: 0 auto;
}
#content #mainimage #post{
	float: right;	
	display: block; 
	margin-right: 30px;
}
#content #mainimage a span{
	margin:100px 100px 0 0;
	color: white;
	font-size: 3em;
	font-weight: bold;
	float: right;
}
a{text-decoration: none;}
</style>
</head>
<body>
	<c:if test="${empty member and not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty modifyResult }">
		<script>
			alert('${modifyResult}');
		</script>
	</c:if>
	<c:if test="${empty modifyResult and not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<jsp:include page="header.jsp"/>
	<div id="content">
		<div id="mainimage">
			<div id="post">
				<a href="#"><span>베를린에서 첫 공개된&lt;사냥의 시간&gt;은 어떤 영화?</span></a>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>