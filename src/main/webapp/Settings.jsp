<html>
<head>
    <title>Settings</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/pokerServlet" method="POST" style="text-align: center">
    <fieldset>
        <legend>Poker Settings</legend>
        Number of Players:<br />
        <input type="number" name="numberOfPlayers" min="2" max="9">
        <br />
        Starting Chips:<br />
        <input type="text" name="startingChips">
        <br />
        <input type="submit" name="" value="Enter" />
    </fieldset>
</form>
</body>
</html>
