<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="books"/>
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
<title><fmt:message key="books.title"/></title>
</head>
<body>
	${navigation}
	<hr>
	<a href="main?action=changeLocale&language=ru&country=RU">RU</a>
	<a href="main?action=changeLocale&language=en&country=US">Eng</a>
	<h1><fmt:message key="books.header"/></h1>
	<br>
	<form action="main" method="get">
		<input type="hidden" name="action" value="getAllBooks">
		<fmt:message key="books.form.sort.sortOder"/> <select name="sortOrder">
			<c:forEach var="comparator" items="${comparators}">
				<option value="${comparator.name}"
					<c:set var="cn" value="${comparator.name}"/> 
					<c:if test="${cn eq sortOrder}">
						selected
					</c:if>
				>${comparator.title}</option>
			</c:forEach>
		</select>
		<input type="submit" value="<fmt:message key="books.form.sort.button"/>">
	</form>
	<form action="main" method="get">
		<input type="hidden" name="action" value="findBooksByTitle">
		<fmt:message key="books.form.find.findeByTitle"/> <input type="text" name="title" value="${title}">
		<input type="submit" value="<fmt:message key="books.form.find.button"/>">
	</form>
	<br>
	<table>
		<tr>
			<th><fmt:message key="books.form.main.title"/></th>
			<th><fmt:message key="books.form.main.available"/></th>
			<th><fmt:message key="books.form.main.aLastName"/></th>
			<th><fmt:message key="books.form.main.aFirstName"/></th>
			<th><fmt:message key="books.form.main.publisher"/></th>
			<th><fmt:message key="books.form.main.releaseDate"/></th>
			<th class="bookEdit"><fmt:message key="books.form.main.edit"/></th>
			<th class="bookOrder"><fmt:message key="books.form.main.order"/></th>
		</tr>
		<c:forEach var="book" items="${books}">
			<tr>
				<td><c:out value="${book.title}" /></td>
				<td><c:out value="${book.available}" /></td>
				<td><c:out value="${book.author.lastName}" /></td>
				<td><c:out value="${book.author.firstName}" /></td>
				<td><c:out value="${book.publisher.name}" /></td>
				<td><c:out value="${book.releaseDate}"/></td>
				<td class="bookEdit"><a href="main?action=prepareBook&bId=${book.id}"><fmt:message key="books.form.main.edit"/></a></td>
				<td class="bookOrder"><a href="main?action=prepareBookOrder&bId=${book.id}"><fmt:message key="books.form.main.order"/></a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>