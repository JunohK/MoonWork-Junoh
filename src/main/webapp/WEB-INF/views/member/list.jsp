<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
</head>
<body>
	<h1>Member List</h1>
	<input type="button" value="회원등록" onclick="location.href='write'">
	<table border="1" width="600px">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
		</tr>
		
		<c:forEach var="row" items="#{items}">
		<tr>
			<td>${row.id}</td>
			<td><a href="${pageContext.request.contextPath}/member/view?id=${row.id}">
				${row.name}</a></td>
			<td>${row.email}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>