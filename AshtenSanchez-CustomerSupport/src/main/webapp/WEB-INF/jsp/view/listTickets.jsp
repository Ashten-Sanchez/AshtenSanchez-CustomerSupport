<%--
  Created by IntelliJ IDEA.
  User: ashte
  Date: 10/14/2024
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>

        <title>Tickets</title>

    </head>

    <body>

    <a href="<c:url value='/logout'/>">Logout</a>

    <h2>Customer Tickets</h2>

    <a href = "<c:url value = '/ticket/create'/>">Create Ticket</a><br><br>

    <c:choose>
        <c:when test="${ticketDatabase.size() == 0}">

            <p>There are no tickets.</p>

        </c:when>

        <c:otherwise>

            <c:forEach var="ticket" items="${ticketDatabase}">

                Ticket # &nbsp; <c:out value="${ticket.key}"/>

                    <a href="<c:url value='/ticket/list/${ticket.key}'/>">

                    <c:out value="${ticket.value.subject}"/></a><br>

            </c:forEach>

        </c:otherwise>

    </c:choose>

    </body>
</html>
