<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="updateUser"/>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
</style>
<meta charset="UTF-8">
<title><fmt:message key="title"/></title>
</head>
<body>
	${navigation}
	<hr>
	<h1><fmt:message key="header"/></h1>
	<form action="main?action=updateUser" method="post">
		<input type="hidden" name="uId" value="${user.id}">
		<fmt:message key="login"/> <input type="text" name="login" value="${user.login}">
		<label style="color:red">${errors.login}</label><br>
		<fmt:message key="password"/> <input type="password" name="password">
		<label style="color:red">${errors.password}</label><br>
		<fmt:message key="password1"/> <input type="password" name="password1"><br>
		<fmt:message key="firstName"/> <input type="text" name="firstName"	value="${user.firstName}">
		<label style="color:red">${errors.firstname}</label><br>
		<fmt:message key="lastName"/> <input type="text" name="lastName" value="${user.lastName}">
		<label style="color:red">${errors.lastname}</label><br>
		<fmt:message key="userType"/> 
		<select name="tId">
			<c:set var="curUtId" value="${user.userType.id}"/>
			<c:forEach var="ut" items="${userTypes}">
				<option value="${ut.id}"
					<c:set var="utId" value="${ut.id}"/>
					<c:if test="${curUtId eq utId}">
						selected
					</c:if>
				>${ut.type}</option>
			</c:forEach>
		</select><br>
		<fmt:message key="penalty"/> <input type="text" name="penalty" value="${user.penalty}">
		<label style="color:red">${errors.penalty}</label><br>
		<fmt:message key="isBlocked"/> <select name="isBlocked">
			<option value="${user.isBlocked}" selected>${user.isBlocked}</option>
			<option value="false">false</option>
			<option value="true">true</option>
		</select><br>
		<input type="submit" value="<fmt:message key="button"/>">
	</form>
</body>
</html>