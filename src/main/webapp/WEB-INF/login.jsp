<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Screen</title>
<link href="${contextPath}/resources/static/css/common.css" rel="stylesheet">
</head>
	<body>
				
		<form method="POST" action="${contextPath}/login" class="form-signin">
		<h2>Login Form</h2>
			<table style="width: 50%">
			<tr>
					<td colspan=2><span>${message}</span></td>
				</tr>
            
				<tr>
					<td>Email:</td>
					<td><input type="text" name="userName"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" /></td>
				</tr>
				 <tr>
					<td colspan=2><span><span>${error}</span></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="Login" /></td>
				</tr>
				<tr>
					<td></td>
					<td><h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4></td>
				</tr>
			</table>
		</form>
	</body>
</html>