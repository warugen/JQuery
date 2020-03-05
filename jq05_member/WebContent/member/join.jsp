<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='../css/join.css' rel='stylesheet'>
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
        showOtherMonths : true,
        onSelect : function(dateText) {
        	$('#birth').val(dateText);
        }
    });
	
	$('#id').keyup(function() {
		var id = $('#id').val().trim();
		var sendData = 'id='+id;
		
		$.ajax({
			url : '../ckId.do',
			dataType : 'html',
			data : sendData,
			success : function(data, status) {
				$('#showId').html(data);
			}
		});
	});
	
	$('#pwChk').keyup(function(){
		var pw = $('#pw').val();
		var pwChk = $(this).val();
		if(pw == pwChk){
			$('#showPw').html('비밀번호 일치');
		}else{
			$('#showPw').html('비밀번호 불일치');
		}
	});
	
	$('#email').keyup(function() {
		var email = $('#email').val().trim();
		var sendData = 'email='+email;
		
		$.ajax({
			url : '../chEmail.do',
			dataType : 'html',
			data : sendData,
			success : function(data, status) {
				$('#showEmail').html(data);
			}
		});
	});
	
	$('#frm').submit(function() {
		var pw = $('#pw').val().trim();
		var pwChk = $('#pwChk').val().trim();
		var idChResult = $('#showId').text().trim();
		var emailChResult = $('#showEmail').text().trim();
		var pwResult = $('#pwChk').text.trim();
		
		console.log('pw = ' + pw);
		console.log('pwChk = ' + pwChk);
		console.log('idChResult = ' + idChResult);
		console.log('emailChResult = ' + emailChResult);
		
		if(pwResult != '비밀번호 일치'){
			alert('비밀번호가 불일치 합니다. 다시 입력해주세요.');
			$('#pw').val("");
			$('#pwChk').val("");
			$('#pw').focus();
			return false;
		}
		
		if(idChResult != '사용 가능한 ID입니다.'){
			alert('아이디를 제대로 입력해라');
			$('#id').val('');
			$('#id').focus();
			return false;
		}
		
		if(emailChResult != '가입 가능한 메일입니다.'){
			alert('이메일을 제대로 입력해라');
			$('#email').val('');
			$('#email').focus();
			return false;
		}
	});
	
});
</script>
</head>
<body>
	<div id="joinForm_wrap">
		<form action="${conPath }/join.do" method="post" id="frm">
			<div id="join_title">회원가입</div>
			<table>
				<tr>
					<th><label for="id">아이디</label></th>
					<td>
					<input type="text" name="id" id="id" class="id">
					<div id="showId">&nbsp; &nbsp;</div>
					</td>
				</tr>
				<tr>
					<th><label for="pw">비밀번호</label></th>
					<td><input type="password" name="pw" id="pw" class="pw"></td>
				</tr>
				<tr>
					<th><label for="pwChk">비밀번호확인</label></th>
					<td>
					<input type="password" name="pwChk" id="pwChk" class="pwChk">
					<div id="showPw"> &nbsp; &nbsp; </div>
					</td>
				</tr>
				<tr>
					<th><label for="name">이름</label></th>
					<td><input type="text" name="name" id="name" class="name"></td>
				</tr>
				<tr>
					<th><label for="birth">생년월일</label></th>
					<td>
					<input type="text" name="birth" id="birth" class="birth">
					<div id="datepicker"></div>
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" name="gender" value="m" checked="checked" id="m"><label for="m">남자</label>
						<input type="radio" name="gender" value="f" id="f"><label for="f">여자</label>
					</td>
				</tr>
				<tr>
					<th><label for="email">이메일</label></th>
					<td>
					<input type="email" name="email" id="email" class="email">
					<div id="showEmail">&nbsp; &nbsp;</div>
					</td>
				</tr>
				<tr>
					<th><label for="tel">전화번호</label></th>
					<td>
						<input type="tel" name="tel" id="tel" class="tel">
					</td>
				</tr>
								<tr>
					<th><label for="address">주소</label></th>
					<td>
						<input type="text" name="address" id="address" class="address">
					</td>
				</tr>
				<tr>
					<td colspan="2"> </td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="가입하기" class="joinBtn_style">
						<input type="reset" value="다시하기" class="joinBtn_style">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>