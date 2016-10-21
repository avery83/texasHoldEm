<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="generator" content="HTML Tidy, see www.w3.org">
</head>
<body>
<h2> Hi ${requestScope.myDeal.handsTotal} </h2>
<% out.print(2*5); %>
<%= request.getAttribute("myDeal") %>
<c:out value="${requestScope.myDeal.deck}"/>
</body>
</html>
