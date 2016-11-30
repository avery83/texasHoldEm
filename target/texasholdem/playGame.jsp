<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="generator" content="HTML Tidy, see www.w3.org">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript" charset="utf-8">

    </script>
    <script src="cardmoves.js" type="text/javascript" charset="utf-8"></script>
</head>
<body style="margin-bottom: 30px;">
<div class="container-fluid"><h1 style="width:100%; text-align:center;">My Awesome Poker Game</h1></div>
<script src="js/bootstrap.min.js"></script>
<div class="container-fluid" id="game" style="background: url(images/POKER-TABLE.jpg); background-size: contain;">
<div id="header"></div>
<div id="CommunityCards" style="overflow: hidden;">
<div class="Cards" id="1st" style="width: 73px; height: 97px; overflow:hidden;float:left;">
    <img src="images/${firstCard}"/>
</div>
<div class="Cards" id="2nd" style=overflow:hidden;float:left;>
    <img src="images/${secondCard}"/>
</div>
<div class="Cards" id="3rd" style=overflow:hidden;float:left;>
    <img src="images/${thirdCard}"/>
</div>
<div class="Cards" id="4th" style=overflow:hidden;float:left;>
    <img src="images/${fourthCard}"/>
</div>
<div class="Cards" id="5th" style=overflow:hidden;float:left;>
    <img src="images/${fifthCard}"/>
</div>
</div>
<div class="Cards"><img src="images/${player1FirstHoleCard}"/> <img src="images/${player1SecondHoleCard}"/></div>
<div class="Cards"><img src="images/${player2FirstHoleCard}"/> <img src="images/${player2SecondHoleCard}"/></div>
<div class="Cards"><img src="images/${player3FirstHoleCard}"/> <img src="images/${player3SecondHoleCard}"/></div>
<div class="Cards"><img src="images/${player4FirstHoleCard}"/> <img src="images/${player4SecondHoleCard}"/></div>

<h2>${winner} ${winningScore}</h2>

<input type="button" value="Deal New Hand" onclick="window.location.reload()">

</div>
<footer id="footer" style="margin-top: 2em;
    position: absolute;
    bottom: 0;
    width: 100%;
    height: 30px;">
    <div class="container"><p>hi</p></div>
</footer>


</body>
</html>
