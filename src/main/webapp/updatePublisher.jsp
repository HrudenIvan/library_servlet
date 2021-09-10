<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="updatePublisher"/>
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
	<form action="main?action=updatePublisher" method="post">
		<input type="hidden" name="pId" value="${publisher.id}">
		<fmt:message key="name"/> <input type="text" name="name" value="${publisher.name}">
		<label style="color:red">${errors.name}</label><br><br>
		<input type="submit" value="<fmt:message key="button"/>">
	</form>
</body>
</html>