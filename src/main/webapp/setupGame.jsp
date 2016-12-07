<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 11/29/16
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="generator" content="HTML Tidy, see www.w3.org">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript" charset="utf-8">

    </script>
    <title>Game Setup</title>
</head>
<body>
<div class="container-fluid" style="text-align: center;">
    <h1 style="text-align: center; color:lightcoral;">Hi <span id="name" style="color:lightblue;">${name}</span>, Lets Play Some Poker!</h1>
</div>
<div class="container-fluid">
    <h3>Set up your game</h3>
</div>
<div>
    <form id="signUpForm" role="form" data-toggle="validator" class="form-horizontal"
          action="/createGame"
          method="post">

        <div class="form-group">
            <label class="control-label col-sm-2" for="numberOfPlayers">Number Of Players</label>
            <div class="col-sm-4">
                <input type="number" class="form-control" id="numberOfPlayers"
                       name="numberOfPlayers"
                       data-error="Please enter number of players." required>
            </div>
            <div class="help-block with-errors"></div>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2" for="startingChips">Starting Chips
            </label>
            <div class="col-sm-4">
                <input type="number" class="form-control" id="startingChips"
                       name="startingChips" data-error="Please provide starting chips."
                       required>
                <div class="help-block with-errors"></div>
            </div>


        </div>
        <input type="hidden" name="name" value="${name}">

        <button type="submit" class="btn btn-default col-sm-offset-3"
                data-disable="true">Play
        </button>
        <button type="reset" class="btn btn-default">Clear</button>

    </form>
</div>

</body>
</html>
