<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<style>
/*
body {
	background-color: #EAEAEA;
}

footer {
	height: 80px;
	background-color: #1b3440;
}

footer #footer_conts, footer #footer_conts a {
	color: white;
	font-weight: blod;
	font-size: 0.9em;
	text-align: center;
}

footer #footer_conts p:first-child {
	font-weight: bold;
	height: 30px;
	line-height: 30px;
}
*/
* {
	font-family: 'Noto Sans KR', sans-serif;
}
</style>
</head>
<body>
	<!-- 
	<footer>
		<div id="footer_conts">
			<p>(주)더조은803</p>
			<p>
				서울특별시 종로구 수표로 육의전빌딩 8,9F | <b><a
					href="${conPath }/adminloginView.do">관리자 모드</a></b>
			</p>
			<p>Copyright© 2020 tj . All rights reserved.</p>
		</div>
	</footer>
	 -->
	<!-- footer -->
	<footer class="pagefooter teal lighten-1">
		<div class="container center ">
			<div class="row">
				<div class="col s12">
					<p>널 위한 문화예술</p>
					<p>
					서울특별시 종로구 수표로 육의전빌딩 8,9F | <b><a
						href="${conPath }/adminloginView.do" style="color: black;">관리자 모드</a></b>
					</p>
				</div>
			</div>
		</div>
		<div class="footer copyright teal lighten-3">
			<div class="container center ">&copy; 널 위한 문화예술</div>
		</div>
	</footer>

	<!-- Compiled and minified JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>