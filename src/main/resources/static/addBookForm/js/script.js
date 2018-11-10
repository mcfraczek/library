var authorCount = 2;
var divRowOpen = "<div class='form-row'>";
var divColOpen = '<div class="col-md-6">';
var divClose = "</div>";
$("#addAuthor").on("click", function () {
    var inputHtml = divRowOpen + divColOpen + '<label for="author' + authorCount + 'Name">Author\'s ' + authorCount + ' Name/Names</label>' +
        '<input type="text" id="author' + authorCount + 'Name" placeholder="Alan Alexander" name="book.authorList[' + authorCount + '].name" class="form-control" />' +
        divClose + divColOpen + '<label for="author' + authorCount + 'Surname">Author\'s ' + authorCount + ' Surname</label>\'  '
        + '<input type="text" id="author' + authorCount + 'Surname" placeholder="Milne" name="book.authorList[' + authorCount + '].surname" class="form-control"/>'
        + divClose + divClose;
    authorCount++;
    $("#authors").append(inputHtml);
});