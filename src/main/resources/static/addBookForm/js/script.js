var authorCount = 1;
var divRowOpen = '<div class="form-row">';

function divColOpen(col) {
    return '<div class="col-md-' + col + '">';
};
var divColOpen6 = divColOpen(6);
var divClose = "</div>";

$("#addAuthor").on("click", function () {
    var inputHtml = divRowOpen + divColOpen6 + '<label for="author' + authorCount + 'Name" class="text-light">Author\'s ' + (authorCount + 1) + ' Name/Names</label>' +
        '<input type="text" id="author' + authorCount + 'Name" placeholder="Alan Alexander" name="book.authorList[' + authorCount + '].name" class="form-control" />' +
        divClose + divColOpen6 + '<label for="author' + authorCount + 'Surname" class="text-light">Author\'s ' + (authorCount + 1) + ' Surname</label>'
        + '<input type="text" id="author' + authorCount + 'Surname" placeholder="Milne" name="book.authorList[' + authorCount + '].surname" class="form-control"/>'
        + divClose + divClose;
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
    typesCopyTo.append(divRowOpen + newstr + divClose);
});