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
<body>
<div class="container-fluid">
    <canvas id="myCanvas" width="1024" height="644">
        <script>
            var canvas = document.getElementById('myCanvas');
            var context =  canvas.getContext('2d');
            var imageObj = new Image();

            imageObj.onload = function() {
                context.drawImage(imageObj, 0, 0, 1024, 644);
            };
            imageObj.src = "images/POKER-TABLE.jpeg";
        </script>
    </canvas>
    <div id="potDiv"  style="position:absolute; margin-top:55%; margin-left:54%; top: -37%; left:-07%;">
        <p id="pot" style="color:GoldenRod; font-size:28px;  text-shadow: 1px 1px black;">$<span id="potSpan">12,500</span></p>
    </div>
    <div id="CommunityCards" style="overflow: hidden; margin-top:45%; margin-left:39%; position:absolute; top: -37%; left:-07%;">
<div class="Cards" id="1st" style="overflow:hidden;float:left; padding: .5em;">
    <img src="images/${firstCard}" style="width:65px; height:90px;"/>
</div>
<div class="Cards" id="2nd" style="overflow:hidden;float:left; padding: .5em;">
    <img src="images/${secondCard}" style="width:65px; height:90px;"/>
</div>
<div class="Cards" id="3rd" style="overflow:hidden;float:left; padding: .5em;">
    <img src="images/${thirdCard}" style="width:65px; height:90px;"/>
</div>
<div class="Cards" id="4th" style="overflow:hidden;float:left; padding: .5em;">
    <img src="images/${fourthCard}" style="width:65px; height:90px;"/>
</div>
<div class="Cards" id="5th" style="overflow:hidden;float:left; padding: .5em;">
    <img src="images/${fifthCard}" style="width:65px; height:90px;"/>
</div>
</div>
    <div style="width:145px; height: 230px; margin-left:43.5%; margin-top: 7%; text-align:center; position: absolute; top: 55%;">
        <div style="height: 16px; background: rgba(204, 204, 204, 0.2);"><p style="color:white;">${UsersName} - $<span class = "chips" id="usersChips">${startingChips}</span></p></div>
        <div class="Cards" style="padding: .5em; background-color:rgba(204, 204, 204, 0.2);"><img class="usersCards" src="images/${UsersFirstHoleCard}" style="width:52px; height:75px;"/> <img class="usersCards" src="images/${UsersSecondHoleCard}" style="width:52px; height:75px;"/></div>
        <div style=" background: rgba(204, 204, 204, 0.2);">

            <input type="button" id="foldButton" value ="FOLD" class="btn btn-danger btn btn-primary btn-sm btn btn-primary btn-block" />
            <button type="button" id="betButton" class="btn btn-info btn btn-primary btn-sm btn btn-primary btn-block">BET</button>
            <input type="range" id="amount" min="10" value="10" max="2000" step="100" oninput="outputUpdate(value)">
                <output for="amount" id="dollarAmount" style="color:white;">$50</output>
                <input type="hidden" id="bet" >



            <script>
                function outputUpdate(vol) {
                    document.querySelector('#dollarAmount').value = vol;
                }
                </script>
        </div>
    </div>
    <div style="width:145px; height: 115px; margin-left:43.5%; margin-top: 1%; text-align:center; position: absolute; top: 3%;">
        <div style="height: 16px; background: rgba(204, 204, 204, 0.2);"><p style="color:white;">${player2} - $<span class = "chips">${startingChips}</span></p></div>
    <div class="Cards" style="padding: .5em; background-color:rgba(204, 204, 204, 0.2);"><img src="images/${player2FirstHoleCard}" style="width:52px; height:75px;"/> <img src="images/${player2SecondHoleCard}" style="width:52px; height:75px;"/></div>
        <div style=" background: rgba(204, 204, 204, 0.2);">
            <h4 style="color:lightblue; font-weight:bold;">Raise</h4>
        </div>
    </div>

    <div style="width:145px; height: 115px; margin-left:43.5%; margin-top: 1%; text-align:center; position: absolute; top: 3%;">
        <div style="height: 16px; background: rgba(204, 204, 204, 0.2);"><p style="color:white;">${player3} - $<span class = "chips">${startingChips}</span></p></div>
    <div class="Cards" style="padding: .5em; background-color:rgba(204, 204, 204, 0.2);"><img src="images/${player3FirstHoleCard}" style="width:52px; height:75px;"/> <img src="images/${player3SecondHoleCard}" style="width:52px; height:75px;"/></div>
        <div style=" background: rgba(204, 204, 204, 0.2);">
            <h4 style="color:lightblue; font-weight:bold;">Raise</h4>
        </div>
    </div>

        <div style="width:145px; height: 115px; margin-left:43.5%; margin-top: 1%; text-align:center; position: absolute; top: 3%;">
            <div style="height: 16px; background: rgba(204, 204, 204, 0.2);"><p style="color:white;">${player4} - $<span class = "chips">${startingChips}</span></p></div>
    <div class="Cards" style="padding: .5em; background-color:rgba(204, 204, 204, 0.2);"><img src="images/${player4FirstHoleCard}" style="width:52px; height:75px;"/> <img src="images/${player4SecondHoleCard}" style="width:52px; height:75px;"/></div>
            <div style=" background: rgba(204, 204, 204, 0.2);">
                <h4 style="color:lightblue; font-weight:bold;">Raise</h4>
            </div>
        </div>

            <div style="width:145px; height: 115px; margin-left:43.5%; margin-top: 1%; text-align:center; position: absolute; top: 3%;">
                <div style="height: 16px; background: rgba(204, 204, 204, 0.2);"><p style="color:white;">${player5} - $<span class = "chips">${startingChips}</span></p></div>
    <div class="Cards" style="padding: .5em; background-color:rgba(204, 204, 204, 0.2);"><img src="images/${player5FirstHoleCard}" style="width:52px; height:75px;"/> <img src="images/${player5SecondHoleCard}" style="width:52px; height:75px;"/></div>
                <div style=" background: rgba(204, 204, 204, 0.2);">
                    <h4 style="color:lightblue; font-weight:bold;">Raise</h4>
                </div>
            </div>

                <div style="width:145px; height: 115px; margin-left:43.5%; margin-top: 1%; text-align:center; position: absolute; top: 3%;">
                    <div style="height: 16px; background: rgba(204, 204, 204, 0.2);"><p style="color:white;">${player6} - $<span class = "chips">${startingChips}</span></p></div>
    <div class="Cards" style="padding: .5em; background-color:rgba(204, 204, 204, 0.2);"><img src="images/${player6FirstHoleCard}" style="width:52px; height:75px;"/> <img src="images/${player6SecondHoleCard}" style="width:52px; height:75px;"/></div>
                    <div style=" background: rgba(204, 204, 204, 0.2);">
                        <h4 style="color:lightblue; font-weight:bold;">Raise</h4>
                    </div>
                </div>

                    <div style="width:145px; height: 115px; margin-left:43.5%; margin-top: 1%; text-align:center; position: absolute; top: 3%;">
                        <div style="height: 16px; background: rgba(204, 204, 204, 0.2);"><p style="color:white;">${player7} - $<span class = "chips">${startingChips}</span></p></div>
    <div class="Cards" style="padding: .5em; background-color:rgba(204, 204, 204, 0.2);"><img src="images/${player7FirstHoleCard}" style="width:52px; height:75px;"/> <img src="images/${player7SecondHoleCard}" style="width:52px; height:75px;"/></div>
                        <div style=" background: rgba(204, 204, 204, 0.2);">
                            <h4 style="color:lightblue; font-weight:bold;">Raise</h4>
                        </div>
                    </div>

                        <div style="width:145px; height: 115px; margin-left:43.5%; margin-top: 1%; text-align:center; position: absolute; top: 3%;">
                            <div style="height: 16px; background: rgba(204, 204, 204, 0.2);"><p style="color:white;">${player8} - $<span class = "chips">${startingChips}</span></p></div>
    <div class="Cards" style="padding: .5em; background-color:rgba(204, 204, 204, 0.2);"><img src="images/${player8FirstHoleCard}" style="width:52px; height:75px;"/> <img src="images/${player8SecondHoleCard}" style="width:52px; height:75px;"/></div>
                            <div style=" background: rgba(204, 204, 204, 0.2);">
                                <h4 style="color:lightblue; font-weight:bold;">Raise</h4>
                            </div>
                        </div>

                            <div style="width:145px; height: 115px; margin-left:43.5%; margin-top: 1%; text-align:center; position: absolute; top: 3%;">
                                <div style="height: 16px; background: rgba(204, 204, 204, 0.2);"><p style="color:white;">${player9} - $<span class = "chips">${startingChips}</span></p></div>
    <div class="Cards" style="padding: .5em; background-color:rgba(204, 204, 204, 0.2);"><img src="images/${player9FirstHoleCard}" style="width:52px; height:75px;"/> <img src="images/${player9SecondHoleCard}" style="width:52px; height:75px;"/></div>
                                <div style=" background: rgba(204, 204, 204, 0.2);">
                                    <h4 style="color:lightblue; font-weight:bold;">Raise</h4>
                                </div>
                            </div>

                                <div style="width:145px; height: 115px; margin-left:43.5%; margin-top: 1%; text-align:center; position: absolute; top: 3%;">
                                    <div style="height: 16px; background: rgba(204, 204, 204, 0.2);"><p style="color:white;">${player10} - $<span class = "chips">${startingChips}</span></p></div>
    <div class="Cards" style="padding: .5em; background-color:rgba(204, 204, 204, 0.2);"><img src="images/${player10FirstHoleCard}" style="width:52px; height:75px;"/> <img src="images/${player10SecondHoleCard}" style="width:52px; height:75px;"/></div>
                                    <div style=" background: rgba(204, 204, 204, 0.2);">
                                        <h4 style="color:lightblue; font-weight:bold;">Raise</h4>
                                    </div>
                                </div>




<h2 style="">${winner} ${winningScore}</h2>

<input type="button" value="Deal New Hand" onclick="window.location.reload()">

</div>


</body>
</html>
