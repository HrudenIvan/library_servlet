<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Authors</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Authors list</h1>
	<table>
		<tr>
			<td>First name</td>
			<td>Last name</td>
			<td>Action</td>
		</tr>
		<c:forEach var="author" items="${authors}">
			<tr>
				<td><c:out value="${author.firstName}" /></td>
				<td><c:out value="${author.lastName}" /></td>
				<td><a href="main?action=prepareAuthor&aId=${author.id}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>