var authorCount = 1;
var divRowOpen = '<div class="form-row">';
var button = ' <div class="col-md-2 d-flex justify-content-center align-items-end"><button type="button" class="btn btn-light btn-sm mb-1 deleteAuthor" ><i class="fas fa-user-minus"></i></button></div>';

function divColOpen(col) {
    return '<div class="col-md-' + col + '">';
};
var divClose = "</div>";

$("#addAuthor").on("click", function () {
    var inputHtml = divRowOpen + divColOpen(5) + '<label for="author' + authorCount + 'Name" class="text-light">Author\'s ' + (authorCount + 1) + ' Name/Names</label>' +
        '<input type="text" id="author' + authorCount + 'Name" placeholder="Alan Alexander" name="book.authorList[' + authorCount + '].name" class="form-control" />' +
        divClose + divColOpen(5) + '<label for="author' + authorCount + 'Surname" class="text-light">Author\'s ' + (authorCount + 1) + ' Surname</label>'
        + '<input type="text" id="author' + authorCount + 'Surname" placeholder="Milne" name="book.authorList[' + authorCount + '].surname" class="form-control"/>'
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
    typesCopyTo.append(divRowOpen + divColOpen(7) + divClose + divColOpen(5) + newstr + divClose + divClose);
});
/*erase Authors*/

$("#authors").on("click", ".deleteAuthor", function () {
    authorCount--;
    $(this).parent().parent().remove();
});

$('input[type="submit"]').on("click", function () {
    for (var i = 0; i < authorCount; i++) {
        var html = $("#authors .form-row:eq(" + i + ")").html();
        $("#authors .form-row:eq(" + i + ")").html(change2(html, i + 1));
    }
});
function change2(input, number) {
    var reg = /\[(\d+)\]/g;
    return input.replace(reg, '[' + number + ']');
}
