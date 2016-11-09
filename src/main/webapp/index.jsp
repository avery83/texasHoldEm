<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="generator" content="HTML Tidy, see www.w3.org">
</head>
<body style="background-color: green;">
<div id="CommunityCards" style="overflow: hidden;">
<div id="1st" style="width: 73px; height: 97px; overflow:hidden;float:left;">
    <img src="images/${firstCard}"/>
</div>
<div id="2nd" style=overflow:hidden;float:left; ">
    <img src="images/${secondCard}"/>
</div>
<div id="3rd" style=overflow:hidden;float:left;">
    <img src="images/${thirdCard}"/>
</div>
<div id="4th" style=overflow:hidden;float:left;">
    <img src="images/${fourthCard}"/>
</div>
<div id="5th" style=overflow:hidden;float:left;">
    <img src="images/${fifthCard}"/>
</div>
</div>
<div><img src="images/${player1FirstHoleCard}"/> <img src="images/${player1SecondHoleCard}"/></div>
<div><img src="images/${player2FirstHoleCard}"/> <img src="images/${player2SecondHoleCard}"/></div>
<div><img src="images/${player3FirstHoleCard}"/> <img src="images/${player3SecondHoleCard}"/></div>
<div><img src="images/${player4FirstHoleCard}"/> <img src="images/${player4SecondHoleCard}"/></div>

<h2>${winner} ${winningScore}</h2>

<input type="button" value="Deal New Hand" onclick="window.location.reload()">
<div style="width:10%;">



</div>

</body>
</html>
