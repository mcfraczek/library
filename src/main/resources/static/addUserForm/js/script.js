/*erase button*/
$("#userAdded").on("click", function () {
    $(this).fadeOut(500, function () {
        $(this).remove();
    });
});
$("#error").on("click", function () {
    $(this).fadeOut(500, function () {
        $(this).remove();
    });
});