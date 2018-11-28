var sumOfFine = 0;

$("#back").on("click", function () {
    window.history.back();
});
dateToRed();

function dateToRed() {
    var array = document.getElementsByClassName("date");
    for (var i = 0; i < array.length; i++) {
        var arrayContent = array[i].textContent;
        var dateFromHtml = Date.parse(arrayContent);
        var dateNow = new Date();
        /*converting ms to day*/
        var day = 3600000 * 24;
        var numberOfDays = Math.floor((dateNow - dateFromHtml) / day);
        /*5zł za dzień*/
        var fine = numberOfDays * 5;
        sumOfFine += fine;
        if (numberOfDays > 1) {
            array[i].parentElement.classList.add("red");
        }
    }
}

$("#countfine").on("click", function () {
    $(this).text(sumOfFine + ' zł').attr("disabled", "disabled");
});



