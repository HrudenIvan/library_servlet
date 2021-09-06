<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Publishers list</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Update publisher</h1>
	<table>
		<tr>
			<td>Name</td>
			<td>Action</td>
		</tr>
		<c:forEach var="publisher" items="${publishers}">
			<tr>
				<td><c:out value="${publisher.name}" /></td>
				<td><a href="main?action=preparePublisher&pId=${publisher.id}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>