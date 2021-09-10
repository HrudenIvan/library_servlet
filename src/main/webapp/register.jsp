<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="register"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="register.title"/></title>
</head>
<body>
${navigation}
<hr>
<h1><fmt:message key="register.header"/></h1>
	<form action="main?action=addUser" method="POST">
		<fmt:message key="register.form.login"/> <input type="text" name="login" value="${user.login}">
		<label style="color:red">${errors.login}</label><br>
		<fmt:message key="register.form.password"/> <input type="password" name="password">
		<label style="color:red">${errors.password}</label><br>
		<fmt:message key="register.form.password1"/> <input type="password" name="password1"><br>
		<fmt:message key="register.form.firstName"/> <input type="text" name="firstName" value="${user.firstName}">
		<label style="color:red">${errors.firstname}</label><br>
		<fmt:message key="register.form.lastName"/> <input type="text" name="lastName" value="${user.lastName}">
		<label style="color:red">${errors.lastname}</label><br>
		<br>
		<input type="submit" value="<fmt:message key="register.form.button"/>">
	</form>
</body>
</html>