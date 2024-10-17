<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <a href="<c:url value='/login'>

        <c:param name='logout'/>

        </c:url>">Logout</a>

    <h2>Subject: <u><c:out value = "${ticket.subject}"/></u></h2>

    <h3>Customer Name: <c:out value = "${ticket.customerName}"/></h3>

    <p>Body: <c:out value = "${ticket.body}"/></p>

    <c:if test = "${ticket.hasAttachments()}">

        <a href = "<c:url value = '/ticket'>

            <c:param name='action' value='download'/>

            <c:param name='ticketid' value='${ticketid}'/>

            <c:param name='attachment' value='${ticket.attachment.name}'/>

                </c:url>">

            <c:out value="${ticket.attachment().name()}"/>

        </a>
    </c:if>

    <br>

    <a href = "ticket">Return to Ticket List</a>

    </body>
</html>
