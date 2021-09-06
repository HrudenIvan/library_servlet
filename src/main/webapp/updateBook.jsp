<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update book</title>
</head>
<body>
${navigation}
<hr>
<h1>Update book</h1>
<form action="main?action=updateBook" method="post">
		<input type="hidden" name="bId" value="${book.id}">
		Title:
		<input type="text" name="title" value="${book.title}"><br>
		Quantity:
		<input type="text" name="quantity" value="${book.quantity}"><br>
		Available:
		<input type="text" name="available" value="${book.available}"><br>
		Author:
		<select name="aId">
			<option value="${book.author.id}" selected="selected">${book.author.firstName} ${book.author.lastName}</option>
			<c:forEach var="author" items="${authors}">
				<option value="${author.id}">${author.firstName} ${author.lastName}</option>
			</c:forEach>
		</select><br>
		Publisher:
		<select name="pId">
			<option value="${book.publisher.id}" selected="selected">${book.publisher.name}</option>
			<c:forEach var="publisher" items="${publishers}">
				<option value="${publisher.id}">${publisher.name}</option>
			</c:forEach>
		</select><br>
		<input type="text" name="releaseDate" value="${book.releaseDate}"><br><br>
		<input type="submit" value="Update">
	</form>
</body>
</html>