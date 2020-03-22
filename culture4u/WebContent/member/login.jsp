<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!--  <link href="${conPath }/css/style.css" rel="stylesheet">-->
</head>
<body>
	<c:if test="${not empty errorMsg}">
		<script>
			alert("${errorMsg}");
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty joinResult }">
		<script>
			alert("${joinResult}");
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div class="container center-align">
		<div class="row"></div>
		<div class="row"></div>
		<div class="z-depth-1 grey lighten-4 row "
			style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">


			<form action="${conPath }/login.do" class="col s12"
				method="post">
				<div class='row'>
					<div class='col s12'></div>
				</div>

				<div class='row'>
					<h4>USER LOGIN</h4>
					<div class='input-field col s12'>
						<input class="validate" type="text" name="mId" id="mId"
							value="${mId }" /> <label for="mId">ID</label>
					</div>
				</div>

				<div class='row'>
					<div class='input-field col s12'>
						<input class="validate" type="password" name="mPw" id="mPw" /> <label
							for="mPw">Enter your password</label>
					</div>
				</div>


				<div class="row">
					<button type="submit" name="btn_login"
						class="col s12 btn btn-large waves-effect waves-light teal">로그인</button>

				</div>
				<div class="row">
				<a href="${conPath}/joinView.do" class="col s12 btn btn-large waves-effect waves-light teal">회원가입</a>

				</div>

			</form>
		</div>
	</div>
	<!--JavaScript at end of body for optimized loading-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>
















