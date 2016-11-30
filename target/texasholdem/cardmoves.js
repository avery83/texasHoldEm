/**
 * Created by student on 11/11/16.
 */

(function($) {
    $(document).ready(function() {
        console.log("hi");




        $("img").click(function () {
            $(this).attr('src', "images/CardBack.gif");
        })
    })
})(jQuery)
