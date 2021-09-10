<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="orderBook"/>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title"/></title>
</head>
<body>
	${navigation}
	<hr>
	<h1><fmt:message key="header"/></h1>
	<form action="main?action=addBookOrder" method="post">
		<input type="hidden" name="bId" value="${book.id}">
		<input type="hidden" name="uId" value="${currentUserId}">
		<fmt:message key="title"/> <input type="text" value="${book.title}" readonly><br>
		<fmt:message key="author"/> <input type="text" value="${book.author.firstName} ${book.author.lastName}" readonly><br>
		<fmt:message key="orderType"/> <select name="otId">
			<c:forEach var="orderType" items="${orderTypes}">
				<option value="${orderType.id}">${orderType.type}</option>
			</c:forEach>
		</select><br>
		<fmt:message key="releaseDate"/> <input type="text" value="${book.releaseDate}" readonly><br><br>
		<input type="submit" value="<fmt:message key="button"/>">
	</form>
</body>
</html>