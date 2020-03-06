<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
	
	// ID 중복체크
	$('#mId').keyup(function() {
		var id = $('#mId').val().trim();
		var sendData = 'mId='+id;
		
		$.ajax({
			url : '${conPath }/ckId.do',
			dataType : 'html',
			data : sendData,
			success : function(data, status) {
				$('#showId').html(data);
			}
		});
	});
	
	// submit 하기전 체크하기
	$('#frm').submit(function() {

		var idChResult = $('#showId').text().trim();
		console.log('idChResult = ' + idChResult);
		
		if(idChResult != '사용 가능한 ID입니다.'){
			alert('중복이니까 다른 아이디를 입력해');
			$('#mId').val('');
			$('#mId').focus();
			return false;
		}

	});
});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/join.do" method="post" id="frm" enctype="multipart/form-data">
			<table>
				<caption>회원가입</caption>
				<tr>
					<th>아이디</th>
					<td>
					<input type="text" name="mId" required="required" id="mId">
					<div id="showId"> &nbsp; &nbsp; </div>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="mPw" required="required"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="mName" required="required"></td>
				</tr>
				<tr>
					<th>메일</th>
					<td><input type="email" name="mEmail"></td>
				</tr>
				<tr>
					<th>사진</th>
					<td><input type="file" name="mPhoto"></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input type="text" name="mBirth" id="datepicker" class="mBirth"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="mAddress"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="회원가입"> <input
						type="button" value="로그인"
						onclick="location.href='${conPath}/loginView.do'">
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>
