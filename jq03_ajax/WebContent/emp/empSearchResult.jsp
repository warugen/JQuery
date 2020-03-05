<%@page import="java.util.ArrayList"%>
<%@page import="com.tj.ex.dto.EmpDto"%>
<%@page import="com.tj.ex.dao.EmpDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 90%;
	margin: 10px auto;
}
th {
	background-color: lightblue;
	color: white;
	padding: 10px;
	
}
td {
	background-color: #AAFFFF;
	padding: 10px;
	text-align: center;
}
</style>
</head>
<body>
<%
	String ename = request.getParameter("ename");
	String job = request.getParameter("job");
	
	EmpDao eDao = EmpDao.getInstance();
	EmpDto dto = new EmpDto();
	dto.setEname(ename);
	dto.setJob(job);
	ArrayList<EmpDto> emps = eDao.getEmplist(dto);
	
%>

	<c:set var="emps" value="<%=emps %>"></c:set>
	<table>
		<tr>
			<th>사원번호</th>
			<th>사원명</th>
			<th>직책</th>
			<th>관리자번호</th>
			<th>입사일</th>
			<th>연봉</th>
			<th>보너스</th>
			<th>부서번호</th>
		</tr>
	 	<c:forEach items="${emps }" var="emp">
	 	<tr>
	 		<td>${emp.empno }</td>
	 		<td>${emp.ename }</td>
	 		<td>${emp.job }</td>
	 		<td>${emp.mgr }</td>
	 		<td>${emp.hiredate }</td>
	 		<td>${emp.sal }</td>
	 		<td>${emp.comm }</td>
	 		<td>${emp.deptno }</td>
	 	</tr>
		</c:forEach>
		<tr>
			<td colspan="8">총 직원 : ${emps.size() }명</td>
		</tr>
	</table>
</body>
</html>