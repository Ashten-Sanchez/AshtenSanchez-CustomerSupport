<%--
  Created by IntelliJ IDEA.
  User: ashte
  Date: 10/14/2024
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
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

                Ticket # &nbsp; <c:out value="${ticket.id}"/>

                    <a href="<c:url value='/ticket/view/${ticket.id}'/>">

                    <c:out value="${ticket.subject}"/></a><br>

            </c:forEach>

        </c:otherwise>

    </c:choose>

    </body>
</html>
