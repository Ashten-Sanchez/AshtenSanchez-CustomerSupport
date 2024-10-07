<%--
  Created by IntelliJ IDEA.
  User: ashte
  Date: 10/7/2024
  Time: 9:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Ticket</title>
</head>
<body>
    <h1>Ticket Details</h1>
    <p>Ticket ID: ${ticketId}</p>
    <p>Title: ${ticket.title}</p>
    <p>Description: ${ticket.description}</p>

    <c:choose>
        <c:when test="${not empty ticket.attachments}">
            <h2>Attachments:</h2>
            <ul>
                <c:forEach var="attachment" items="${ticket.attachments}">
                    <li>${attachment}</li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>No attachments available</p>
        </c:otherwise>
    </c:choose>

    <a href="listTickets.jsp">Back to Ticket List</a>
</body>
</html>
