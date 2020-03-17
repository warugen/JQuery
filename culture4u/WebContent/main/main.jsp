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
<title>Insert title here</title>
<style>
	#content_form {
		width: 800px; height:500px; line-height:500px;
		margin: 0 auto; text-align: center; font-size: 3em;
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
	<div id="content_form">
		main
		<%
		SimpleDateFormat format1 = new SimpleDateFormat ( "yy-MM");
		Date time = new Date();
		String time1 = format1.format(time);
		System.out.println(time1);
		%>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>