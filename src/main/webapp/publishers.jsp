<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="publishers"/>
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
			<th><fmt:message key="name"/></th>
			<th><fmt:message key="action"/></th>
		</tr>
		<c:forEach var="publisher" items="${publishers}">
			<tr>
				<td><c:out value="${publisher.name}" /></td>
				<td><a href="main?action=preparePublisher&pId=${publisher.id}"><fmt:message key="button"/></a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>