<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users list</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Users list</h1>
	<table>
		<tr>
			<td>Login</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>User type</td>
			<td>Action</td>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.login}" /></td>
				<td><c:out value="${user.firstName}" /></td>
				<td><c:out value="${user.lastName}" /></td>
				<td><c:out value="${user.userType.type}" /></td>
				<td><a href="main?action=prepareUser&uId=${user.id}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>