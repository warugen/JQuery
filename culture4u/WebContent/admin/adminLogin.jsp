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
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!--  <link href="${conPath }/css/style.css" rel="stylesheet">-->
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<!-- 
	<div id="content_form">
		<form action="${conPath }/adminlogin.do" method="post">
			<table>
				<caption>관리자 모드 로그인</caption>
				<tr>
					<th>ID</th>
					<td><input type="text" name="mId" value="${mId }"
						required="required"></td>
				</tr>
				<tr>
					<th>PW</th>
					<td><input type="password" name="mPw" required="required"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="로그인">
			</table>
		</form>
	</div>
	 -->
	 <div class="container center-align">
        <div class="section"></div>
        <div class="section"></div>
        <div class="z-depth-1 grey lighten-4 row "
            style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">


            <form action="${conPath }/adminlogin.do" class="col s12" method="post">
                <div class='row'>
                    <div class='col s12'>
                    </div>
                </div>

                <div class='row'>
                    <h4>관리자 로그인</h4>
                    <div class='input-field col s12'>
                        <input class='validate' type='text' name='mId' id='mId' />
                        <label for='mId'>ID</label>
                    </div>
                </div>

                <div class='row'>
                    <div class='input-field col s12'>
                        <input class='validate' type='password' name='mPw' id='mPw' required="required" />
                        <label for='password'>Enter your password</label>
                    </div>
                </div>

                <br />
                
                    <div class='row'>
                        <button type='submit' name='btn_login'
                            class='col s12 btn btn-large waves-effect teal'>Login</button>
                    </div>
                
            </form>
        </div>
    </div>
	 <!--JavaScript at end of body for optimized loading-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>