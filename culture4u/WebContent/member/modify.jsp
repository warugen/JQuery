<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

</head>
<body>
	<jsp:include page="../main/header.jsp" />

	<div class="container center">
		<div class="row">
			<div class="col s12 m4 l2"></div>
			<div class="col s12 m4 l8 ">
				<form action="${conPath }/modify.do" method="post" enctype="multipart/form-data">
					<div class="card-panel grey lighten-3 z-depth-5">
						<h5>정보수정</h5>

						<div class="file-field input-field">
							<div id="View_area"
								style='position: relative; width: 120px; height: 150px; color: black; border: 0px solid black; dispaly: inline;'>
								<img id="prev_View_area"
									src="${conPath }/membersPhoto/${member.mPhoto}"
									style="width: 120px; height: 150px;">
							</div>
							<div class="btn">
								<span>사진선택</span> <input type="file" name="mPhoto" id="mPhoto"
									onchange="previewImage(this,'View_area')">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text"
									placeholder="${member.mPhoto}">
							</div>
						</div>


						<div class="input-field">
							<i class="material-icons prefix">account_box</i> <input
								type="text" name="mId" required="required" id="mId" value="${member.mId }"
									readonly="readonly"> <label
								for="mId">ID</label> <span id="showId"> &nbsp; &nbsp; </span>
						</div>


						<div class="input-field">
							<i class="material-icons prefix">vpn_key</i> <input id="mPw"
								type="password" name="mPw" class="validate"> <label
								for="mPw">비밀번호</label>
						</div>
						<div class="input-field">
							<i class="material-icons prefix">vpn_key</i> <input id="mPwChk"
								type="password" name="mPwChk" class="validate"> <label
								for="mPwChk">비밀번호확인</label>
						</div>

						<div class="input-field">
							<i class="material-icons prefix">local_offer</i> <input
								type="text" name="mName" required="required" id="mName" value="${member.mName }" /> <label
								for="mName">이름</label>
						</div>
						<div class="input-field">
							<i class="material-icons prefix">email</i> <input type="email"
								name="mEmail" id="mEmail" value="${member.mEmail }"> <label for="mEmail">Email</label>
						</div>

						<div class="input-field">
							<i class="material-icons prefix">cake</i> <input type="text"
								name="mBirth" id="datepicker" class="mBirth" value="${member.mBirth }" placeholder="생년월일" />
						</div>

						<div class="input-field">
							<i class="material-icons prefix">home</i> <input type="text"
								name="mAddress" id="mAddress" value="${member.mAddress }" /> <label for="mAddress">주소</label>
						</div>




						<div class="row">
							<button type="submit" name="btn_modify"
								class="z-depth-5 btn btn-large waves-effect waves-light teal">정보수정</button>

						</div>

					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- 
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
	 -->
	<!--JavaScript at end of body for optimized loading-->
	<!-- Compiled and minified JavaScript -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script>
		$(function() {
			$("#datepicker").datepicker({
				firstDay : true,

				yearRange : 50,
				format : "yyyy 년 mm 월 dd 일",
				formatSubmit : "yyyy/mm/dd",
				selectMonths : true,
				selectYears : 140,
				showMonthsShort : false,
				showWeekdaysFull : false,
				close : '닫기',
				clear : false,
				today : '오늘',
				format : 'yyyy-mm-dd',
				formatSubmit : 'yyyy-mm-dd',
				max : true, // 이 옵션이 ture면 오늘까지밖에 날짜 선택을 못한다
				closeOnSelect : true,
				onSet : function(e) {
					if (e.select) {
						this.close();
					}
				},
				i18n : {
					today : "오늘",
					months : [ "1월", "2월", "3월", "4월", "5월",
							"6월", "7월", "8월", "9월", "10월",
							"11월", "12월" ],
					monthsFull : [ "1월", "2월", "3월", "4월",
							"5월", "6월", "7월", "8월", "9월",
							"10월", "11월", "12월" ],
					monthsShort : [ "1월", "2월", "3월", "4월",
							"5월", "6월", "7월", "8월", "9월",
							"10월", "11월", "12월" ],
					weekdaysFull : [ "일요일", "월요일", "화요일",
							"수요일", "목요일", "금요일", "토요일" ],
					weekdaysShort : [ "일", "월", "화", "수", "목",
							"금", "토" ],
					weekdaysAbbrev : [ "일", "월", "화", "수", "목",
							"금", "토" ]
				}

			});

			// submit 하기전 체크하기
			$('#frm').submit(function() {

				var idChResult = $('#showId').text().trim();
				console.log('idChResult = ' + idChResult);

				if (idChResult != '사용 가능한 ID입니다.') {
					alert('중복이니까 다른 아이디를 입력해');
					$('#mId').val('');
					$('#mId').focus();
					return false;
				}

			});

		});
		// 프로필 사진 미리보기
		function previewImage(targetObj, View_area) {
			var preview = document.getElementById(View_area); //div id
			var ua = window.navigator.userAgent;

			//ie일때(IE8 이하에서만 작동)
			if (ua.indexOf("MSIE") > -1) {
				targetObj.select();
				try {
					var src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
					var ie_preview_error = document
							.getElementById("ie_preview_error_" + View_area);

					if (ie_preview_error) {
						preview.removeChild(ie_preview_error); //error가 있으면 delete
					}

					var img = document.getElementById(View_area); //이미지가 뿌려질 곳

					//이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
					img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"
							+ src + "', sizingMethod='scale')";
				} catch (e) {
					if (!document.getElementById("ie_preview_error_"
							+ View_area)) {
						var info = document.createElement("<p>");
						info.id = "ie_preview_error_" + View_area;
						info.innerHTML = e.name;
						preview.insertBefore(info, null);
					}
				}
				//ie가 아닐때(크롬, 사파리, FF)
			} else {
				var files = targetObj.files;
				for (var i = 0; i < files.length; i++) {
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