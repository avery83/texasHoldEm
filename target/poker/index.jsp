<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 12/9/16
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="generator" content="HTML Tidy, see www.w3.org">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/component.css" />
    <script src="js/modernizr.custom.js"></script>
    <title>My Awesome Poker Game</title>
</head>
<body style="background-color:#3c3c3c">
<div class="container-fluid">
<a href = "${pageContext.request.contextPath}/setupGame.jsp" >Sign in or Sign up</a>
<a href = "${pageContext.request.contextPath}/leaderBoard" >LeaderBoard</a>
</div>
<div class="container">
    <!-- Top Navigation -->

    <ul id="elasticstack" class="elasticstack">
        <li><img src="img/1.gif" alt="01"/></li>
        <li><img src="img/2.gif" alt="02"/></li>
        <li><img src="img/3.gif" alt="03"/></li>
        <li><img src="img/4.gif" alt="04"/></li>
    </ul>
<!-- /container -->
    <script src="js/draggabilly.pkgd.min.js"></script>
    <script src="js/elastiStack.js"></script>
    <script>
        new ElastiStack( document.getElementById( 'elasticstack' ) );
    </script></div>
<div class="container-fluid">
    <h1 style="text-align: center; color:white;">Welcome To All In Texas Holdem! Sign up or Sign in to play!</h1>
</div>
</body>
</html>
