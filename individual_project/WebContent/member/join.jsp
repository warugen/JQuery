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
<link href="${conPath }/css/style.css" rel="stylesheet">
<style>
#content_form .btn{
	width: 30%;
	height:60px;
	background-color: black;
	color: white;
	margin: 0 5px;
	border: 0;
}
</style>
<link href="${conPath }/css/style.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
	$(document).ready(function() {
		$('#mId').keyup(function() {
			var mId = $('#mId').val();
			$.ajax({
				url : '${conPath}/joinIdChk.do',
				type : 'get',
				dataType : 'html',
				data : "mId="+mId,
				success : function(data, status) {
							$('#mIdChkMsg').html(data);
						},
				error : function(code){
					alert(code);
				}
			});
		});
		$('#mPwChk').keyup(function() {
			var mPw = $('#mPw').val();
			var mPwChk = $('#mPwChk').val();
			if(mPw==mPwChk){
				$('#pwChkResult').html('비밀번호일치');
			}else{
				$('#pwChkResult').html('비밀번호가 일치하지 않습니다.');
			}
		});
		$('form').submit(function() {
			var mIdChkMsg = $('#mIdChkMsg').text().trim();
			alert(mIdChkMsg);
			var pwChkResult = $('#pwChkResult').text();
			if(mIdChkMsg!='사용가능한 아이디입니다.'){
					return false;
			}else if(pwChkResult!='비밀번호일치'){
				$('#mPw').focus();
				return false;
			}
		});
	});
</script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker(
				{
					dateFormat : 'yy-mm-dd',
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
					showMonthAfterYear : true,
					yearSuffix : '년',
					showOtherMonths : true,
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ]
				});
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<form action="${conPath }/join.do" method="post">
		<h2>회원가입</h2>
		<hr>
		<table>
			<tr><th>ID</th>
				<td><input type="text" name="mId" required="required" id="mId">
					<div id="mIdChkMsg">&nbsp;&nbsp;&nbsp;</div>	
				</td>
			</tr>
			<tr><th>Password</th>	
				<td><input type="password" name="mPw" required="required" id="mPw"></td>
			</tr>
			<tr><th>Password Check</th>	
				<td><input type="password" name="mPwChk" required="required" id="mPwChk">
					<div id="pwChkResult">&nbsp;&nbsp;&nbsp;</div>
				</td>
			</tr>
			<tr><th>Name</th>
				<td><input type="text" name="mName" required="required"></td>
			<tr><th>Birth</th>
				<td><input type="text" name="mBirth" id="datepicker"></td>
			</tr>
			<tr><th>Email</th>
				<td><input type="text" name="mEmail"></td>
			</tr>
			<tr><th>TEL</th>
				<td><input type="text" name="mTel"></td>
			</tr>
			<tr><th>Address</th>
				<td><input type="text" name="mAddress"></td>
			</tr>
			<tr><td colspan="2">
				<input type="submit" value="회원가입" class="btn">
				</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>