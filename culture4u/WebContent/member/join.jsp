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
<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
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
	<div class="container">
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
					<td>
					<div id="View_area" style='position:relative; width: 120px; height: 150px; color: black; border: 0px solid black; dispaly: inline; '>
					<img id="prev_View_area"  src="${conPath }/membersPhoto/default_pf2.png" style="width: 120px; height: 150px;">
					</div>
					<input type="file" name="mPhoto" id="mPhoto" onchange="previewImage(this,'View_area')">
					</td>
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
	<!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script>
 // 프로필 사진 미리보기
	function previewImage(targetObj, View_area) {
	var preview = document.getElementById(View_area); //div id
	var ua = window.navigator.userAgent;

  	//ie일때(IE8 이하에서만 작동)
	if (ua.indexOf("MSIE") > -1) {
		targetObj.select();
		try {
			var src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
			var ie_preview_error = document.getElementById("ie_preview_error_" + View_area);


			if (ie_preview_error) {
				preview.removeChild(ie_preview_error); //error가 있으면 delete
			}

			var img = document.getElementById(View_area); //이미지가 뿌려질 곳

			//이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
			img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
		} catch (e) {
			if (!document.getElementById("ie_preview_error_" + View_area)) {
				var info = document.createElement("<p>");
				info.id = "ie_preview_error_" + View_area;
				info.innerHTML = e.name;
				preview.insertBefore(info, null);
			}
		}
  	//ie가 아닐때(크롬, 사파리, FF)
	} else {
		var files = targetObj.files;
		for ( var i = 0; i < files.length; i++) {
			var file = files[i];
			var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
			if (!file.type.match(imageType))
				continue;
			var prevImg = document.getElementById("prev_" + View_area); //이전에 미리보기가 있다면 삭제
			if (prevImg) {
				preview.removeChild(prevImg);
			}
			var img = document.createElement("img"); 
			img.id = "prev_" + View_area;
			img.classList.add("obj");
			img.file = file;
			img.style.width = '120px'; 
			img.style.height = '150px';
			preview.appendChild(img);
			if (window.FileReader) { // FireFox, Chrome, Opera 확인.
				var reader = new FileReader();
				reader.onloadend = (function(aImg) {
					return function(e) {
						aImg.src = e.target.result;
					};
				})(img);
				reader.readAsDataURL(file);
			} else { // safari is not supported FileReader
				//alert('not supported FileReader');
				if (!document.getElementById("sfr_preview_error_"
						+ View_area)) {
					var info = document.createElement("p");
					info.id = "sfr_preview_error_" + View_area;
					info.innerHTML = "not supported FileReader";
					preview.insertBefore(info, null);
				}
			}
		}
	}
	}
    </script>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>
