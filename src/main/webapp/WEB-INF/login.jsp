<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Screen</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-2.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
	<body>
	<div class="container" style="margin: 50px">
	<form:form method="POST" modelAttribute="userForm" class="form-signin">
		<h3>Seating application Login</h3>
		<c:if test="${param.error ne null}">
			<div style="color: red">Invalid credentials.</div>
		</c:if>
		
			<div class="form-group">
			<spring:bind path="email">
					<label for="username">UserName: </label><form:input type="text" path="email" class="form-control"
						placeholder="Email" autofocus="true"></form:input>
					<form:errors path="email"></form:errors>
			</spring:bind>
			
				<!-- <label for="username">UserName: </label><input type="text"
					class="form-control" id="email" name="email"> -->
			</div>
			<div class="form-group">
				<!-- <label for="pwd">Password:</label> <input type="password"
					style="width: 18%;" class="form-control" id="pwd" name="password"> -->
			<spring:bind path="password">
					<label for="pwd">Password:</label><form:input type="password" path="password" class="form-control"
						placeholder="Password"></form:input>
					<form:errors path="password"></form:errors>
			</spring:bind>
			</div>

			<button type="submit" class="btn btn-success">Submit</button>

			 <br />
			 <a href="/registration"
				style="color: #4285f4; line-height: 1.4286; cursor: pointer;">New
				User</a>
		</form:form>
	</div>
</body>
</html>