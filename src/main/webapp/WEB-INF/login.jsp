<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 11/22/16
  Time: 1:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container theme-showcase" role="main">

    <div class="col-sm4 col-sm-offset-4">
        <form method="POST" action="<%= response.encodeURL(request.getContextPath() + "/j_security_check") %>">
            <div class="form-group">
                <label class="control-label col-sm-2" for="j_username">User name</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="j_username"
                           name="j_username"
                           data-error="Please enter your username (user name)."
                           required>
                </div>
                <div class="help-block with-errors"></div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="j_password">Password</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="j_password"
                           name="j_password"
                           data-error="Please enter your password."
                           required>
                </div>
                <div class="help-block with-errors"></div>
            </div>

            <button type="submit" class="btn btn-default col-sm-offset-3"
                    data-disable="true">Log In
            </button>
            <button type="reset" class="btn btn-default">Clear</button>
        </FORM>
        <a href="http://localhost:8080/createPlayer.jsp">New Player?</a>
    </div>
</div>

</body>
</html>
