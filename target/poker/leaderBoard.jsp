<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 12/11/16
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="generator" content="HTML Tidy, see www.w3.org">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>LeaderBoard</title>
</head>
<body>
<h1>LeaderBoard</h1>
<table>
    <c:forEach var="user" items="${users}">
        <tr><td><c:out value="${user.getUserName()}"/></td>
            <td><form action="${pageContext.request.contextPath}/deleteUser" method="get">
                <input type="hidden" name="deleteUser" value="${user.getUserName()}"/>
                <button type="submit" value="Submit">Delete User</button>
            </form></td></tr>
    </c:forEach>
</table>
</body>
</html>
