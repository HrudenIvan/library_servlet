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
		<input type="text" name="title" value="${book.title}">
		<label style="color:red">${errors.title}</label><br>
		Quantity:
		<input type="text" name="total" value="${book.quantity}">
		<label style="color:red">${errors.quantity}</label><br>
		Author:
		<select name="aId">
			<c:set var="aId" value="${aId}"/>
			<c:forEach var="author" items="${authors}">
				<c:set var="curAId" value="${author.id}"/>
				<option value="${author.id}"
					<c:if test="${aId == curAId}">
						selected
					</c:if>
				>${author.firstName} ${author.lastName}</option>
			</c:forEach>
		</select><br>
		Publisher:
		<select name="pId">
			<c:set var="pId" value="${pId}"/>
			<c:forEach var="publisher" items="${publishers}">
				<c:set var="curPId" value="${publisher.id}"/>
				<option value="${publisher.id}"
					<c:if test="${pId == curPId}">
						selected
					</c:if>
				>${publisher.name}</option>
			</c:forEach>
		</select><br>
		Release date: <input type="text" name="releaseDate" value="${book.releaseDate}">
		<label style="color:red">${errors.releaseDate}</label><br><br>
		<input type="submit" value="Submit">
	</form>
</body>
</body>
</html>