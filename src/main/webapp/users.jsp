<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="users"/>
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
	<table>
		<tr>
			<th><fmt:message key="login"/></th>
			<th><fmt:message key="firstName"/></th>
			<th><fmt:message key="lastName"/></th>
			<th><fmt:message key="userType"/></th>
			<th><fmt:message key="action"/></th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.login}" /></td>
				<td><c:out value="${user.firstName}" /></td>
				<td><c:out value="${user.lastName}" /></td>
				<td><c:out value="${user.userType.type}" /></td>
				<td><a href="main?action=prepareUser&uId=${user.id}"><fmt:message key="button"/></a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>