<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Screen</title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<script src="/js/jquery-2.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</head>
	<body>
		<div class="container">
		<form:form method="POST" modelAttribute="userForm" class="form-signin">
			<h2 class="form-signin-heading">Create your account</h2>
			<spring:bind path="firstName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="firstName" class="form-control"
						placeholder="First Name" autofocus="true"></form:input>
					<form:errors path="firstName"></form:errors>
				</div>
			</spring:bind>
			<spring:bind path="lastName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="lastName" class="form-control"
						placeholder="Last Name" autofocus="true"></form:input>
					<form:errors path="lastName"></form:errors>
				</div>
			</spring:bind>
			<spring:bind path="email">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="email" class="form-control"
						placeholder="Email" autofocus="true"></form:input>
					<form:errors path="email"></form:errors>
				</div>
			</spring:bind>
			<spring:bind path="location">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="location" class="form-control"
						placeholder="Location" autofocus="true"></form:input>
					<form:errors path="location"></form:errors>
				</div>
			</spring:bind>

			<form:select path="country" items="${countries}"/>

			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="password" path="password" class="form-control"
						placeholder="Password"></form:input>
					<form:errors path="password"></form:errors>
				</div>
			</spring:bind>
			
			<spring:bind path="confirmPassword">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="password" path="confirmPassword"
						class="form-control" placeholder="Pw Conf"></form:input>
					<form:errors path="confirmPassword"></form:errors>
				</div>
			</spring:bind>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>

	</div>
</body>
</html>