<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome screen</title>
</head>
<body>
<div>

        <h2>Welcome ${pageContext.request.userPrincipal.name}</h2>

</div>
</body>
</html>
