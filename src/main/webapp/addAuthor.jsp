<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="addAuthor"/>
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
	<form action="main?action=addAuthor" method="post">
		<fmt:message key="firstName"/> <input type="text" name="firstName" value="${author.firstName}">
		<label style="color:red">${errors.firstname}</label><br>
		<fmt:message key="lastName"/> <input type="text" name="lastName" value="${author.lastName}">
		<label style="color:red">${errors.lastname}</label>
		<br><br>
		<input type="submit" value="<fmt:message key="button"/>">
	</form>
</body>
</html>