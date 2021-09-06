<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.bookEdit {
	display: ${navigation.bookEditVis}
}
.bookOrder {
	display: ${navigation.bookOrderVis}
}
table, th, td {
  border:1px solid black;
}
</style>
<meta charset="UTF-8">
<title>Books list</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Books list</h1>
	<br>
	<form action="main" method="get">
		<input type="hidden" name="action" value="getAllBooks">
		Sort order: <select name="sortOrder">
			<c:forEach var="comparator" items="${comparators}">
				<option value="${comparator.name}">${comparator.title}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Sort">
	</form>
	<form action="main" method="get">
		<input type="hidden" name="action" value="findBooksByTitle">
		Find by title: <input type="text" name="title">
		<input type="submit" value="Find">
	</form>
	<br>
	<table>
		<tr>
			<th>Title</th>
			<th>Available</th>
			<th>Author Last Name</th>
			<th>Author First Name</th>
			<th>Publisher Name </th>
			<th>Release date</th>
			<th class="bookEdit"> Edit book</th>
			<th class="bookOrder"> Order book</th>
		</tr>
		<c:forEach var="book" items="${books}">
			<tr>
				<td><c:out value="${book.title}" /></td>
				<td><c:out value="${book.available}" /></td>
				<td><c:out value="${book.author.lastName}" /></td>
				<td><c:out value="${book.author.firstName}" /></td>
				<td><c:out value="${book.publisher.name}" /></td>
				<td><c:out value="${book.releaseDate}"/></td>
				<td class="bookEdit"><a href="main?action=prepareBook&bId=${book.id}">Edit</a></td>
				<td class="bookOrder"><a href="main?action=prepareBookOrder&bId=${book.id}">Order</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>