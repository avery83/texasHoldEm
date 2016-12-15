/**
 * Created by Jason Avery on 11/11/16.
 */
var bet;
var userBet;

(function($) {
    $(document).ready(function() {
        console.log("hi");
        bet = 50;
        //hide all players divs
        $(".AIDiv").hide();
        //hide winning div
        $("#winningLine").hide();


        //show player divs
        for (i = 0; i < numberOfPlayers -1; i++) {
            createPlayerDiv();
        }


        $("img").click(function () {
           $(this).attr('src', "images/CardBack.gif");
            //
        });

        $("#foldButton").on('click', function () {
            console.log("fold");
            $(".usersCards").attr('src', "images/CardBack.gif");
        });

        $("#betButton").on('click', function () {
            //do something
            console.log($("#dollarAmount").val())
            userBet = $("#dollarAmount").val();
            var pot = +$("#potSpan").text() + +userBet;
            $("#potSpan").replaceWith(pot);
            $("#usersChips").replaceWith(+$("#usersChips").text() - +userBet);

            $("#player2FirstHoleCard").attr("src", player2Card1OriginalImage);
            $("#player2SecondHoleCard").attr("src", player2Card2OriginalImage);

            $("#player3FirstHoleCard").attr("src", player3Card1OriginalImage);
            $("#player3SecondHoleCard").attr("src", player3Card2OriginalImage);

            $("#player4FirstHoleCard").attr("src", player4Card1OriginalImage);
            $("#player4SecondHoleCard").attr("src", player4Card2OriginalImage);

            $("#player5FirstHoleCard").attr("src", player5Card1OriginalImage);
            $("#player5SecondHoleCard").attr("src", player5Card2OriginalImage);

            $("#player6FirstHoleCard").attr("src", player6Card1OriginalImage);
            $("#player6SecondHoleCard").attr("src", player6Card2OriginalImage);

            $("#player7FirstHoleCard").attr("src", player7Card1OriginalImage);
            $("#player7SecondHoleCard").attr("src", player7Card2OriginalImage);

            $("#player8FirstHoleCard").attr("src", player8Card1OriginalImage);
            $("#player8SecondHoleCard").attr("src", player8Card2OriginalImage);

            $("#player9FirstHoleCard").attr("src", player9Card1OriginalImage);
            $("#player9SecondHoleCard").attr("src", player9Card2OriginalImage);

            $("#player10FirstHoleCard").attr("src", player10Card1OriginalImage);
            $("#player10SecondHoleCard").attr("src", player10Card2OriginalImage);

            $("#winningPot").replaceWith(pot);
            $("#winningLine").show();
        });
    })
    //show player divs
    function createPlayerDiv() {
        console.log("we made it to the javascript file");
        for (i = 0; i < numberOfPlayers - 1 ; i++) {
        $(".AIDiv").eq(i).show();
        }

    }
})(jQuery)
