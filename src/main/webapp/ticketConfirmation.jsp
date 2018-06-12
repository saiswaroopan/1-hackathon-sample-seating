<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ticket Confirmation Screen</title>
</head>
	<body>
		<font color="">${errorMessage}</font>
		<h1>Summary of Purchase</h1>
		<form method="POST" action="">
			${noOfTickets} Tickets purchased - Price per ticket : ${ticketCost}
				<br /> Total cost of seats purchased : ${totalTicketsCost}
				<br />
		</form>
	</body>
</html>