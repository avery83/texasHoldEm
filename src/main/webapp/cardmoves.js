/**
 * Created by student on 11/11/16.
 */


var pot = $("#potSpan").text();
var bet;

(function($) {
    $(document).ready(function() {
        console.log("hi");
        bet = 50;




        console.log(+$("#usersChips").text() + +4);



        $("img").click(function () {
           $(this).attr('src', "images/CardBack.gif");
        });

        $("#foldButton").on('click', function () {
            console.log("fold");
            $(".usersCards").attr('src', "images/CardBack.gif");
        });

        $("#betButton").on('click', function () {
            //do something
            $("#pot").replaceWith($("#amount").val);
        });

    })

    function yeah() {

    }
})(jQuery)
