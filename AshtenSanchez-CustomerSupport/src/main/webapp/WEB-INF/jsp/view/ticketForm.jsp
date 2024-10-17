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

        <a href="<c:url value='/login'>

            <c:param name='logout'/>

            </c:url>">Logout</a>

        <h2>Create a Ticket</h2>

            <form method="POST" action="ticket" enctype="multipart/form-data">

                <input type="hidden" name="action" value="create">

                Subject:<br>
                <input type="text" name="title"><br><br>

                Customer Name:<br>
                <input type="text" name="customerName"><br><br>

                Body:<br>

                <textarea name="body" rows="25" cols="100"></textarea><br><br>

                <b>Attachment</b><br>

                <input type="file" name="file1"><br><br>

                <input type="submit" value="Submit">
            </form>
    </body>
</html>
