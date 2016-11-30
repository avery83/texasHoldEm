<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 11/29/16
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Welcome Page</title>
</head>
<body>
<div class="container-fluid">
<h1>Create Your Player</h1>
</div>
<div>
    <form id="signUpForm" role="form" data-toggle="validator" class="form-horizontal"
          action="/createPlayer"
          method="post">

        <div class="form-group">
            <label class="control-label col-sm-2" for="userName">User
                Name</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="userName"
                       name="userName"
                       data-error="Please enter your user name." required>
            </div>
            <div class="help-block with-errors"></div>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password
            </label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password"
                       name="password" data-error="Please provide a password."
                       required>
                <div class="help-block with-errors"></div>
            </div>

        </div>
        <div class="form-group">
            <label class="control-label col-sm-2"
                   for="confirmPassword">Retype
                Password</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="confirmPassword"
                       name="confirmPassword" equalTo="#password"
                       data-match="#password"
                       data-match-error="Confirmation password does not match"
                       required data-error="Please retype your password.">
                <div class="help-block with-errors"></div>
            </div>
        </div>

        <button type="submit" class="btn btn-default col-sm-offset-3"
                data-disable="true">Submit
        </button>
        <button type="reset" class="btn btn-default">Clear</button>

    </form>
</div>

</body>
</html>
