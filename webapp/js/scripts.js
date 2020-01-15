String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};

$(function () {
    $("form[name='answer']").find("input[type='submit']").click(addAnswer);
});

function addAnswer() {
    event.preventDefault();
    var data = $("form[name='answer']").serialize();

    console.log(data);

    $.ajax({
        url: "/qna/addAnswer"
        , data: data
        , method : "POST"
        , dataType : "json"
        , success : onSuccess
        , error : onError
    })
}

function onSuccess(result) {
    var articleTemplate = jQuery("#answerTemplate").html();
    var template = articleTemplate.format(result.writer, new Date(result.createdDate), result.contents, result.answerId);

    $(".qna-comment-slipp-articles").prepend(template)
}

function onError() {

}