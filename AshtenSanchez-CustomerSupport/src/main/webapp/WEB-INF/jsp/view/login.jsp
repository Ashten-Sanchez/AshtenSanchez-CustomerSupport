<%--
  Created by IntelliJ IDEA.
  User: ashte
  Date: 10/17/2024
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
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

        <form:form method="POST" action="login" modelAttribute="loginForm">

            <form:label path="username">Username:&nbsp</form:label>
                <form:input path="username"/><br><br>

            <form:label path="password">Password:&nbsp</form:label>
                <form:input path="password"/><br><br>

            <input type="submit" value="Log In">

        </form:form>

    </body>
</html>
