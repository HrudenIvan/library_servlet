<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add book</title>
</head>
<body>
${navigation}
<hr>
<h1>Add book</h1>
<form action="main?action=addBook" method="post">
		Title:
		<input type="text" name="title"><br>
		Quantity:
		<input type="text" name="quantity"><br>
		Available:
		<input type="text" name="available"><br>
		Author:
		<select name="aId">
			<c:forEach var="author" items="${authors}">
				<option value="${author.id}">${author.firstName} ${author.lastName}</option>
			</c:forEach>
		</select><br>
		Publisher:
		<select name="pId">
			<c:forEach var="publisher" items="${publishers}">
				<option value="${publisher.id}">${publisher.name}</option>
			</c:forEach>
		</select><br>
		Release date: <input type="text" name="releaseDate"><br><br>
		<input type="submit" value="Submit">
	</form>
</body>
</body>
</html>