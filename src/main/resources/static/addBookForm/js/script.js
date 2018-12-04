var authorCount = 1;
var divRowOpen = '<div class="form-row">';
var divRowOpenClass = '<div class="form-row mt-3">';
var divRowOpenClass2 = '<div class="form-row w-100 px-0 mb-3">';
var divColOpenClass12 = '<div class="col-md-12 px-0">'
var button = ' <div class="col-md-2 d-flex justify-content-center align-items-end"><button type="button" class="btn btn-light btn-sm mb-1 deleteAuthor" ><i class="fas fa-user-minus"></i></button></div>';

function divColOpen(col) {
    return '<div class="col-md-' + col + '">';
};
var divClose = "</div>";

$("#addAuthor").on("click", function () {
    var inputHtml = divRowOpenClass + divColOpen(5) + '<label for="author' + authorCount + 'Name" class="text-light">Author\'s ' + (authorCount + 1) + ' Name/Names</label>' +
        '<input type="text" id="author' + authorCount + 'Name" placeholder="Alan Alexander" name="book.authorList[' + authorCount + '].name" class="form-control" required maxlength="45"/>' +
        divClose + divColOpen(5) + '<label for="author' + authorCount + 'Surname" class="text-light">Author\'s ' + (authorCount + 1) + ' Surname</label>'
        + '<input type="text" id="author' + authorCount + 'Surname" placeholder="Milne" name="book.authorList[' + authorCount + '].surname" class="form-control" required maxlength="45"/>'
        + divClose + button + divClose;
    authorCount++;
    $("#authors").append(inputHtml);
});

/*Adding genre*/
var typesFrom = $("#copyFrom").html();
var typesCopyTo = $("#types");
var typesCount = 1;
$("#addGenre").on("click", function () {
    var str = typesFrom;
    var re = /([\d+])/g;
    var newstr = str.replace(re, typesCount++);
    typesCopyTo.append(divRowOpenClass2 + divColOpenClass12 + newstr + divClose + divClose);
});
/*erase Authors*/

$("#authors").on("click", ".deleteAuthor", function () {
    authorCount--;
    $(this).parent().parent().remove();
});

/*erase button*/
$("#added").on("click", function () {
    $(this).fadeOut(500, function () {
        $(this).remove();
    });
});
/*
$('input[type="submit"]').on("click", function () {
    var html;
    for (var i = 0; i < authorCount; i++) {
        html = $("#authors .form-row:eq(" + i + ")").html();
        $("#authors .form-row:eq(" + i + ")").html(change2(html, i + 1));
    }
    alert(html);
});*/
/*$("#submit").on("click", function () {
    alert($("input"));

    var html;
    for (var i = 0; i < authorCount; i++) {
        html = $("#authors .form-row:eq(" + i + ")").html();
        $("#authors .form-row:eq(" + i + ")").html(change2(html, i + 1));
    }
});
function change2(input, number) {
    var reg = /\[(\d+)\]/g;
    return input.replace(reg, '[' + number + ']');
}*/

