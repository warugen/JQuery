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
<link rel="stylesheet" href="${conPath }/css/header.css" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Bangers&display=swap" rel="stylesheet">
</head>
<body>
	<c:if test="${empty member and empty admin}">
		<!-- 로그인전 -->
		<div id="header">
			<div id="gnb">
				<div class="logo">
					<p>
						<a href="${conPath }/main.do"><img src="${conPath }/img/logo.png"
							alt="홈페이지 로고" />Cinema Tour</a>
					</p>
				</div>
				<div class="sign">
					<a href="${conPath}/joinView.do">JOIN</a>
					<a href="${conPath}/loginView.do">LOGIN</a>
					<span>방문자님,환영합니다.</span>
				</div>
			</div>
			<div id="lnb">
				<div class="board_menu">
					<ul>
						<li><a href="${conPath }/faqListView.do">공지사항</a></li>
						<li><a href="${conPath }/movieList.do">영화정보</a></li>
						<li><a href="">자유게시판</a></li>
					</ul>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty member and empty admin}">
		<!-- 사용자 로그인후 -->
		<div id="header">
			<div id="gnb">
				<div class="logo">
					<p>
						<a href="main.jsp"><img src="${conPath }/img/logo.png"
							alt="홈페이지 로고" />Cinema Tour</a>
					</p>
				</div>
				<div class="sign">
					<a href="${conPath}/logout.do">LOGOUT</a>
					<a href="${conPath}/modifyView.do">MODIFY</a>
					<span>${member.mName }&nbsp;님</span>
				</div>
			</div>
			<div id="lnb">
				<div class="board_menu">
					<ul>
						<li><a href="${conPath }/faqListView.do">공지사항</a></li>
						<li><a href="${conPath }/movieList.do">영화정보</a></li>
						<li><a href="#">자유게시판</a></li>
					</ul>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${empty member and not empty admin }"> <!-- 관리자로그인 -->
		<div id="header">
			<div id="gnb">
				<div class="logo">
					<p>
						<a href="main.jsp"><img src="${conPath }/img/logo.png"
							alt="홈페이지 로고" />Cinema Tour</a>
					</p>
				</div>
				<div class="sign">
					<a href="${conPath}/logout.do">LOGOUT</a>
					<a href="${conPath}/memberList.do">MEMBER</a>
					<span>${admin.aName }&nbsp;님</span>
				</div>
			</div>
			<div id="lnb">
				<div class="board_menu">
					<ul>
						<li><a href="${conPath}/faqListView.do">공지사항</a></li>
						<li><a href="${conPath }/movieList.do">영화정보</a></li>
						<li><a href="#">자유게시판</a></li>
					</ul>
				</div>
			</div>
		</div>
	</c:if>
</html>