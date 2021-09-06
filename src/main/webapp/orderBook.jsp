<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book order</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Book order</h1>
	<form action="main?action=bookOrder" method="post">
		<input type="hidden" name="bId" value="${book.id}">
		<input type="hidden" name="uId" value="${currentUserId}">
		Book: <input type="text" value="${book.title}" readonly><br>
		Author: <input type="text" value="${book.author.firstName} ${book.author.lastName}" readonly><br>
		Order type: <select name="otId">
			<c:forEach var="orderType" items="${orderTypes}">
				<option value="${orderType.id}">${orderType.type}</option>
			</c:forEach>
		</select><br>
		Release date: <input type="text" value="${book.releaseDate}" readonly><br><br>
		<input type="submit" value="Order">
	</form>
</body>
</html>