<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Screen</title>
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
	<body>
		<h1>Register Form</h1>
		<form:form method="POST" modelAttribute="userForm">
			<table style="width: 50%">
			
				<tr>
					<td>First Name:</td>
					<td colspan=2><form:input name="firstName" /></td>
				</tr>
			
				<tr>
					<td>Last Name:</td>
					<td colspan=2><form:input type="text" name="lastName" /></td>
				</tr>
				<spring:bind path="userName">
					<tr>
						<td>Email:</td>
						<td colspan=2><form:input type="text" path="userName" name="userName" placeholder="Username"/></td>
						<form:errors path="userName"></form:errors>
					</tr>
				</spring:bind>
				<tr>
					<td>Location:</td>
					<td><form:input type="text" name="location" /></td>
					<td>
						<select name="states">
							<option value="HYD">HYD</option>
						</select>
					</td>
				</tr>
				<spring:bind path="password">
					<tr>
						<td>Password:</td>
						<td colspan=2><form:input type="password" name="password" /></td>
						<form:errors path="passwordConfirm"></form:errors>
					</tr>
				</spring:bind>
				<spring:bind path="confirmPassword">
					<tr>
						<td>Password Conf:</td>
						<td colspan=2><form:input type="password" name="confirmPassword" placeholder="Confirm your password"/></td>
						<form:errors path="passwordConfirm"></form:errors>
					</tr>
				</spring:bind>
					<tr>
						<td></td>
						<td colspan=2 align="right"><form:input type="submit" value="Register" /></td>
					</tr>
			</table>
			
		</form:form>
	</body>
</html>