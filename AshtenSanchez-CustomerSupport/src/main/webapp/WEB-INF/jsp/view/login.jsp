<%--
  Created by IntelliJ IDEA.
  User: ashte
  Date: 10/17/2024
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Ticket Login</title>
    </head>
    <body>
        <h2>Login</h2>
        You must log in to view Ticket Tracker
        <br><br>

        <c:if test="${loginFailed == true}">

            <b><c:out value="The username or password you enterd are not correct, please try again."/> </b>

        </c:if>

        <br><br>

        <form method="POST" action="<c:url value='/login'/>">

            Username: <input type="text" name="username"><br><br>
            Password: <input type="password" name="password"><br><br>

            <input type="submit" value="Log In">
        </form>
    </body>
</html>
