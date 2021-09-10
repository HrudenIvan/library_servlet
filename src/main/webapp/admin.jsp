<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="admin"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${currentUser.firstName} ${currentUser.lastName}</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>
		<c:if test="${isBlocked}"><label style="color:red"><fmt:message key="blocked"/></label></c:if>
	</h1>
	<h1><fmt:message key="header"/></h1>
	<a href="main?action=changeLocale&language=ru&country=RU">RU</a>
	<a href="main?action=changeLocale&language=en&country=US">Eng</a>
</body>
</html>