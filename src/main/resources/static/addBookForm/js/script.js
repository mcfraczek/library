var authorCount = 1;

$("#addAuthor").on("click", function () {
    var inputHtml = '<tr>' +
        '<td><input name="book.authorList[' + authorCount + '].name" /></td>' +
        '<td><input name="book.authorList[' + authorCount + '].surname" /></td>' +
        '</tr>';
    authorCount++;
    $("#authors").append(inputHtml);
});