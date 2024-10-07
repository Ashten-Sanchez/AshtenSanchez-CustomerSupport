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
    <title>Ticket Form</title>
</head>
<body>
    <h1>Create a Ticket</h1>

        <form action="createTicket" method="post">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>
            <br><br>
            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>
            <br><br>
            <label for="attachment">Attachment:</label>
            <input type="file" id="attachment" name="attachment">
            <br><br>
            <input type="submit" value="Submit">
        </form>

</body>
</html>
