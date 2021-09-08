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
		<input type="text" name="title" value="${book.title}">
		<label style="color:red">${errors.title}</label><br>
		Quantity:
		<input type="text" name="quantity" value="${book.quantity}">
		<input type="hidden" name="oldQuantity" value="${book.quantity}">
		<label style="color:red">${errors.quantity}</label><br>
		Available:
		<input type="text" name="available" value="${book.available}" readonly><br>
		Author:
		<select name="aId">
			<c:set var="aId" value="${book.author.id}"/>
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
			<option value="${book.publisher.id}" selected="selected">${book.publisher.name}</option>
			<c:set var="pId" value="${book.publisher.id}"/>
			<c:forEach var="publisher" items="${publishers}">
				<c:set var="curPId" value="${publisher.id}"/>
				<option value="${publisher.id}"
					<c:if test="${pId == curPId}">
						selected
					</c:if>
				>${publisher.name}</option>
			</c:forEach>
		</select><br>
		<input type="text" name="releaseDate" value="${book.releaseDate}">
		<label style="color:red">${errors.releaseDate}</label><br><br>
		<input type="submit" value="Update">
	</form>
</body>
</html>