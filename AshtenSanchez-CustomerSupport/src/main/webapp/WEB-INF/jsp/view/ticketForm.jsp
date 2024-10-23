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
        <title>Create a new ticket</title>
    </head>
    <body>

        <a href="<c:url value='/logout'/>">Logout</a>

        <h2>Create a Ticket</h2>

            <form:form method="POST" action="create" modelAttribute="ticket" enctype="multipart/form-data">

                <form:label path="subject">Subject:</form:label><br>
                    <form:input path="subject"/><br><br>

                <form:label path="customerName">Customer Name:</form:label><br>
                    <form:input path="customerName"/><br><br>

                <form:label path="body">Body:</form:label><br>
                    <form:textarea path="body"  rows="25" cols="100"/><br><br>

                <b>Attachment</b><br>
                    <form:input path="attachment" type="file"/><br><br>

                    <input type="submit" value="Submit">

            </form:form>

    </body>
</html>
