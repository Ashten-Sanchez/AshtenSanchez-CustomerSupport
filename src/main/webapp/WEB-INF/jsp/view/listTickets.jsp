<%--
  Created by IntelliJ IDEA.
  User: ashte
  Date: 10/7/2024
  Time: 9:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Ticket List</title>
</head>

<body>
    <h1>All Tickets</h1>
    <ul>
        <c:forEach var="ticket" items="${ticketDB}">
            <li>
                <a href="viewTicket.jsp?ticketId=${ticket.id}">${ticket.title}</a>
            </li>
        </c:forEach>
    </ul>

    <a href="ticketForm.jsp">Create a new Ticket</a>
</body>
</html>

