$("#back").on("click", function () {
    window.history.back();
});
dateToRed();

function dateToRed() {
    var dateFromHtml = Date.parse(document.getElementById("date").innerText);
    var dateNow = new Date();
    /*converting ms to day*/
    var day = 3600000 * 24;
    var numberOfDays = Math.floor((dateNow - dateFromHtml) / day);
    if (numberOfDays > 30) {
        $("#date").parent().addClass("red");
    }
}

