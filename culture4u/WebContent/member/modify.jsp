<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
<!--  <link href="${conPath }/css/style.css" rel="stylesheet">-->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<script>
	$(function() {
		$( "#datepicker" ).datepicker({
			dateFormat : 'yy-mm-dd',
	    	prevText: '이전 달',
	        nextText: '다음 달',
	        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	        showMonthAfterYear: true,
	        yearSuffix: '년',
	        showOtherMonths : true
	        
	        
	    });
	});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<div id="content_form">
	<form action="${conPath }/modify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="dbmPhoto" value="${member.mPhoto }">
		<table>
			<tr><th>아이디</th>
					<td><input type="text" name="mId" value="${member.mId }"
									readonly="readonly">
					</td>
					<td rowspan="4">
						<img src="${conPath }/memberPhotoUp/${member.mPhoto}" width="150">
					</td>
			</tr>
			<tr><th>비밀번호</th>
					<td><input type="password" name="mPw" value="${member.mPw }"
									required="required">
					</td>
			</tr>
			<tr><th>이름</th>
					<td><input type="text" name="mName" value="${member.mName }"
									required="required">
					</td>
			</tr>
			<tr><th>메일</th>
					<td><input type="email" name="mEmail" value="${member.mEmail }">
					</td>
			</tr>
			<tr><th>사진</th>
					<td colspan="2"><input type="file" name="mPhoto"></td>
			</tr>
			<tr><th>생년월일</th>
					<td colspan="2">
						<input type="date" name="mBirth" id="datepicker" value="${member.mBirth }">
					</td>
			</tr>
			<tr><th>주소</th>
					<td colspan="2">
						<input type="text" name="mAddress" value="${member.mAddress }">
					</td>
			</tr>
			<tr><td colspan="3">
						<input type="submit" value="정보수정">
						<input type="reset" value="취소">
						<input type="reset" value="이전" onclick="history.go(-1)">	
					</td>
			</tr>
		</table>
	</form>
	</div>
	<!--JavaScript at end of body for optimized loading-->
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>