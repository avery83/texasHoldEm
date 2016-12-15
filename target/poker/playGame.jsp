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
    <link href="css/style.css" rel="stylesheet">;
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript" charset="utf-8">
    </script>
</head>
<body>
<div class="container-fluid"><h1>Good Luck ${UsersName}!</h1></div>
<div class="container-fluid" id ="middleDiv">
<div  id="gameDiv">
    <canvas id="myCanvas">
        <script>
            var numberOfPlayers = ${numberOfPlayers}
            var player2Card1OriginalImage = "images/${player2FirstHoleCard}";
            var player2Card2OriginalImage = "images/${player2SecondHoleCard}";

            var player3Card1OriginalImage = "images/${player3FirstHoleCard}";
            var player3Card2OriginalImage = "images/${player3SecondHoleCard}";

            var player4Card1OriginalImage = "images/${player4FirstHoleCard}";
            var player4Card2OriginalImage = "images/${player4SecondHoleCard}";

            var player5Card1OriginalImage = "images/${player5FirstHoleCard}";
            var player5Card2OriginalImage = "images/${player5SecondHoleCard}";

            var player6Card1OriginalImage = "images/${player6FirstHoleCard}";
            var player6Card2OriginalImage = "images/${player6SecondHoleCard}";

            var player7Card1OriginalImage = "images/${player7FirstHoleCard}";
            var player7Card2OriginalImage = "images/${player7SecondHoleCard}";

            var player8Card1OriginalImage = "images/${player8FirstHoleCard}";
            var player8Card2OriginalImage = "images/${player8SecondHoleCard}";

            var player9Card1OriginalImage = "images/${player9FirstHoleCard}";
            var player9Card2OriginalImage = "images/${player9SecondHoleCard}";

            var player10Card1OriginalImage = "images/${player10FirstHoleCard}";
            var player10Card2OriginalImage = "images/${player10SecondHoleCard}";
            console.log(numberOfPlayers);
            var canvas = document.getElementById('myCanvas');
            var context =  canvas.getContext('2d');
            canvas.width = window.innerWidth;
            canvas.height = window.innerHeight;
            var imageObj = new Image();

            imageObj.onload = function() {
                context.drawImage(imageObj, 0, 0, canvas.width, canvas.height);
            };
            imageObj.src = "images/POKER-TABLE.jpeg";
        </script>
    </canvas>
    <script src="cardmoves.js" type="text/javascript" charset="utf-8"></script>
    <div id="potDiv">
        <p id="pot">$<span id="potSpan">${firstRoundOfBets}</span></p>
    </div>
    <div id="CommunityCards">
<div class="CCards" id="1st">
    <img  id="firstCard" src="images/${firstCard}" class="ccimage"/>
</div>
<div class="CCards" id="2nd">
    <img id="secondCard" src="images/${secondCard}" class="ccimage" />
</div>
<div class="CCards" id="3rd">
    <img id="thirdCard" src="images/${thirdCard}" class="ccimage"/>
</div>
<div class="CCards" id="4th">
    <img id="fourthCard" src="images/${fourthCard}" class="ccimage"/>
</div>
<div class="CCards" id="5th">
    <img id="fifthCard" src="images/${fifthCard}" class="ccimage"/>
</div>
</div>
    <div id ="playersDiv">
        <div class="nameDiv"><p>${UsersName} - $<span class = "chips" id="usersChips">${startingChips}</span></p></div>
        <div class="Cards"><img class = "holeCards" src="images/${UsersFirstHoleCard}"/> <img class = "holeCards" src="images/${UsersSecondHoleCard}"/></div>
        <div class="cardDiv">

            <input type="button" id="foldButton" value ="FOLD" class="btn btn-danger btn btn-primary btn-sm btn btn-primary btn-block" />
            <button type="button" id="betButton" class="btn btn-info btn btn-primary btn-sm btn btn-primary btn-block">BET</button>
            <input type="range" id="amount" min="25"  max="${startingChips}" step="25" oninput="outputUpdate(value)">
                <output for="amount" id="dollarAmount" value="${bet}" style="color:white;">$${bet}</output>
                <input type="hidden" name="bet" id="bet" value="hi" />
            <script>
                function outputUpdate(vol) {
                document.querySelector('#dollarAmount').value = vol;
            }</script>
        </div>
    </div>
    <div class="AIDiv" id="p2">
        <div class="nameDiv"><p>${player2} - $<span class = "chips">${player2playersChips}</span></p></div>
    <div class="Cards"><img class = "holeCards" id="player2FirstHoleCard" src="images/${player2FirstHoleCard}" class="holeCards"/> <img class = "holeCards" id="player2SecondHoleCard" src="images/CardBack.gif"/></div>
        <div class="cardDiv">
            <h4>${player2playersMove}</h4>
        </div>
    </div>

    <div class="AIDiv" id="p3">
        <div class="nameDiv"><p>${player3} - $<span class = "chips">${player3playersChips}</span></p></div>
    <div class="Cards"><img class = "holeCards" id="player3FirstHoleCard" src="images/CardBack.gif"/> <img class = "holeCards" id="player3SecondHoleCard" src="images/CardBack.gif"/></div>
        <div class="cardDiv">
            <h4>${player3playersMove}</h4>
        </div>
    </div>

        <div class="AIDiv" id="p4">
            <div class="nameDiv"><p>${player4} - $<span class = "chips">${player4playersChips}</span></p></div>
    <div class="Cards"><img class = "holeCards" id="player4FirstHoleCard" src="images/CardBack.gif"/> <img class = "holeCards" id="player4SecondHoleCard" src="images/CardBack.gif"/></div>
            <div class="cardDiv">
                <h4>${player4playersMove}</h4>
            </div>
        </div>

            <div class="AIDiv" id="p5">
                <div class="nameDiv"><p>${player5} - $<span class = "chips">${player5playersChips}</span></p></div>
    <div class="Cards"><img class = "holeCards" id="player5FirstHoleCard" src="images/CardBack.gif"/> <img class = "holeCards" id="player5SecondHoleCard" src="images/CardBack.gif"/></div>
                <div class="cardDiv">
                    <h4>${player5playersMove}</h4>
                </div>
            </div>

                <div class="AIDiv" id="p6">
                    <div class="nameDiv"><p>${player6} - $<span class = "chips">${player6playersChips}</span></p></div>
    <div class="Cards"><img class = "holeCards" id="player6FirstHoleCard" src="images/CardBack.gif" style="width:52px; height:75px;"/> <img class = "holeCards" id="player6SecondHoleCard" src="images/CardBack.gif"/></div>
                    <div class="cardDiv">
                        <h4>${player6playersMove}</h4>
                    </div>
                </div>

                    <div class="AIDiv" id="p7">
                        <div class="nameDiv"><p>${player7} - $<span class = "chips">${player7playersChips}</span></p></div>
    <div class="Cards"><img class = "holeCards" id="player7FirstHoleCard" src="images/CardBack.gif"/> <img class = "holeCards" id="player7SecondHoleCard" src="images/CardBack.gif" /></div>
                        <div class="cardDiv">
                            <h4>${player7playersMove}</h4>
                        </div>
                    </div>

                        <div class="AIDiv" id="p8">
                            <div class="nameDiv"><p>${player8} - $<span class = "chips">${player8playersChips}</span></p></div>
    <div class="Cards"><img class = "holeCards" id="player8FirstHoleCard" src="images/CardBack.gif"/> <img class = "holeCards" id="player8SecondHoleCard" src="images/CardBack.gif"/></div>
                            <div class="cardDiv">
                                <h4>${player8playersMove}</h4>
                            </div>
                        </div>

                            <div class="AIDiv" id="p9">
                                <div class="nameDiv"><p>${player9} - $<span class = "chips">${player9playersChips}</span></p></div>
    <div class="Cards"><img class = "holeCards" id="player9FirstHoleCard" src="images/CardBack.gif"/> <img class = "holeCards" id="player9SecondHoleCard" src="images/CardBack.gif"/></div>
                                <div class="cardDiv">
                                    <h4>${player9playersMove}</h4>
                                </div>
                            </div>

                                <div class="AIDiv" id="p10">
                                    <div class="nameDiv"><p>${player10} - $<span class = "chips">${player10playersChips}</span></p></div>
    <div class="Cards"><img class = "holeCards" id="player10FirstHoleCard" src="images/CardBack.gif"/> <img class = "holeCards" id="player10SecondHoleCard" src="images/CardBack.gif"/></div>
                                    <div class="cardDiv">
                                        <h4>${player10playersMove}</h4>
                                    </div>
                                </div>
<div class="container-fluid">
    <input type="hidden" value=""/>
<h2 id="winningLine" >${winner} Wins $<span id="winningPot"></span> with ${winningScore}</h2>

<input type="button" value="Deal New Hand" onclick="window.location.reload()">
    <a href="${pageContext.request.contextPath}/index.jsp" >Return Home</a>
</div>
</div>
</div>
</body>
</html>
