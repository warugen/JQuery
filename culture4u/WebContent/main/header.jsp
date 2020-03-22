<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--Import Google Icon Font-->
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
    <!--Import materialize.css-->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"
    />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <style>
      .header {
        width: 100%;
        height: 150px;
        padding: 0px 30px;
        padding-top: 50px;
      }

      .sticky-nav {
        position: fixed;
        top: 0;
        z-index: 999;
      }
      nav #fadeInLogo {
      display: none;
    }
    </style>
</head>
<body>
	<div class="header grey lighten-5 center">
	<!-- 
		<a href="${conPath }/main/main.jsp" class="brand-logo"> <img src="${conPath }/img/logo.png" alt="" />
		 -->
		<a href="${conPath }/main.do" class="brand-logo"> <img src="${conPath }/img/logo.png" alt="" />
		</a>
	</div>
<c:if test="${empty member and empty admin}"> <%-- 로그인 전 화면 --%>
	<nav id="nav-menu" class="teal">
		<div class="nav-warpper ">
			<div class="container teal ">
				<a href="${conPath }/main.do" id="fadeInLogo" class="fadeInLogo brand-logo animated slideInUp"> 
					<img src="${conPath }/img/small-logo.png" alt="" style="width: 70px;" />
				</a>
				<ul class="right">
					<li><a href="${conPath }/notice_list.do">공지사항</a></li>
					<li><a href="${conPath }/monthly_list.do">공연알림</a></li>
					<li><a href="${conPath }/magazine_list.do">매거진</a></li>
					<li><a href="${conPath }/free_list.do">자유게시판</a></li>
					<li><a href="${conPath }/joinView.do">회원가입</a></li>
					<li><a href="${conPath }/loginView.do">로그인</a></li>
				</ul>
			</div>
		</div>
	</nav>
</c:if>
<c:if test="${not empty member and empty admin}"> <%-- 사용자 모드 로그인 화면--%>
	<nav id="nav-menu" class="teal">
		<div class="nav-warpper ">
			<div class="container teal ">
				<a href="${conPath }/main/main.jsp" id="fadeInLogo" class="fadeInLogo brand-logo animated slideInUp"> 
					<img src="${conPath }/img/small-logo.png" alt="" style="width: 70px;" />
				</a>
				<ul class="right">
					<li><a href="${conPath }/notice_list.do">공지사항</a></li>
					<li><a href="${conPath }/monthly_list.do">공연알림</a></li>
					<li><a href="${conPath }/magazine_list.do">매거진</a></li>
					<li><a href="${conPath }/free_list.do">자유게시판</a></li>
					<li><a href="${conPath }/modifyView.do">정보수정</a></li>
					<li><a href="${conPath }/logout.do">로그아웃</a></li>
				</ul>
			</div>
		</div>
	</nav>
</c:if>
<c:if test="${empty member and not empty admin}"> <%-- 관리자 모드 로그인 화면--%>
	<nav id="nav-menu" class="teal">
		<div class="nav-warpper ">
			<div class="container teal ">
				<a href="${conPath }/main/main.jsp" id="fadeInLogo" class="fadeInLogo brand-logo animated slideInUp"> 
					<img src="${conPath }/img/small-logo.png" alt="" style="width: 70px;" />
				</a>
				<ul class="right">
					<li><a href="${conPath }/notice_list.do">공지사항</a></li>
					<li><a href="${conPath }/monthly_list.do">공연알림</a></li>
					<li><a href="${conPath }/magazine_list.do">매거진</a></li>
					<li><a href="${conPath }/free_list.do">자유게시판</a></li>
					<li><a href="${conPath }/logout.do">관리자모드나가기</a></li>
				</ul>
			</div>
		</div>
	</nav>
</c:if>


	<!-- Compiled and minified JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script>
	$(document).ready(function () {
		$(window).scroll(function () {
	        var scrolltop = $(window).scrollTop();
	        if ($(window).scrollTop() > 150) {
	          $("#nav-menu").addClass("sticky-nav");
	        } else {
	          $("#nav-menu").removeClass("sticky-nav");
	        }
	      });
		
		$(window).scroll(function () {
	        var scrollPosition = $(this).scrollTop();
	        var $fadeInLogo = $(".fadeInLogo");
	        if (scrollPosition > 150) {
	          $fadeInLogo.fadeIn(200);
	        } else {
	          $fadeInLogo.fadeOut(200);
	        }
	      });
	});
	</script>
</body>
</html>