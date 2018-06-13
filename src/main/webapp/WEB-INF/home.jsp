<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Booking Seat App</title>
<script src="js/jquery-2.2.1.min.js"></script>
<script src="js/seating-handler.js"></script>
</head>
<body>


<body>

	<h1>Welcome User ${username}</h1>


	Select Section:

	<form:select id="sections" name='role' path="sectionId" multiple="true">

			<c:forEach items="${sections}" var="section">

				<option value="${section.id}">${section.name}</option>

			</c:forEach>

		</form:select>

Select seat:

		<form:select id="seatContainer" path="seatId" multiple="true">

		</form:select>

<br/>	

		<button id="bookTickets" class="btn btn-lg btn-primary btn-block"

			type="submit">Book Ticket</button>

<br/>

	<h2>Booking History</h2>

	<div>

		<c:forEach items="${bookingHistory}" var="bookedSection">

			<span>Section: ${bookedSection.sectionName} Seat:

				${bookedSection.seatName}</span>

			<br />

		</c:forEach>

	</div>

	<div id="messageStatus"></div>


</body>

</html>