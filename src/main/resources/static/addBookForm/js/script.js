var authorCount = 1;
var copy1 = "<div class='form-group col-md-6'><label for='author";
var copy2 = "</label><input class='form-control' type='text' id='author";
var copy3 = " placeholder='Alan Alexander Milne' name='title' value></div>";
var copyTo = $("#copyTo");
var addAuthor = $("#addAuthor").on("click", function () {
    authorCount++;
    var copied = copy1 + authorCount + "'" + ">Author " + authorCount + copy2 + authorCount + "'" + copy3;
    copyTo.append(copied);
});

var copy_2 = $("#copy2").html();
$("#addGenre").on("click", function () {
    $("#copyTo2").append(copy_2);
});