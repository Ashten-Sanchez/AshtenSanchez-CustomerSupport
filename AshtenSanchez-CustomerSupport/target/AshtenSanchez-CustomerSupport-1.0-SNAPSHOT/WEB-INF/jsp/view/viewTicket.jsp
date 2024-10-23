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

        <title>Ticket #<c:out value = "${ticketid}"/></title>

    </head>

    <body>

    <a href="<c:url value='/logout'/>">Logout</a>

    <h2>Subject: <u><c:out value = "${ticket.subject}"/></u></h2>

    <h3>Customer Name: <c:out value = "${ticket.customerName}"/></h3>

    <p>Body: <c:out value = "${ticket.body}"/></p>

    <c:if test = "${ticket.hasAttachments()}">

            <a href="<c:url value='/ticket/${ticketId}/attachment/${ticket.attachmentName}' />">
                <c:out value="${ticket.attachmentName}"/></a>

    </c:if>

    <br>

    <a href = "<c:url value='/ticket/list'/>">Return to Ticket List</a>

    </body>
</html>
