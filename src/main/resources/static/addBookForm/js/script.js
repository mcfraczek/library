var authorCount = 1;
var copy1 = "<div class='form-group col-md-6'><label for='author";
var copy2 = "</label><input class='form-control' type='text' id='author";
var copy3 = " placeholder='Alan Alexander Milne' th:field='*{title}'></div>";
var copyTo = $("#copyTo");
var addAuthor = $("#addAuthor").on("click", function () {
    authorCount++;
    var copied = copy1 + authorCount + "'" + ">Author " + authorCount + copy2 + authorCount + "'" + copy3;
    copyTo.append(copied);
});