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

function addAnswer(event) {
    event.preventDefault();

    var answerFormData = $("form[name='answer']").serialize();

    //TODO remove
    console.log("serialized data : ", answerFormData);

    $.ajax({
        url: "/api/qna/answer"
        , data: answerFormData
        , method : "POST"
        , dataType : "json"
        , success : onSuccess
        , error : onError
    })
}

function onSuccess(result) {
    //TODO remove
    console.log("response data : ", result);

    var articleTemplate = jQuery("#answerTemplate").html();
    var template = articleTemplate.format(result.writer, new Date(result.createdDate), result.contents, result.answerId);

    $(".qna-comment-slipp-articles").prepend(template)
}

function onError() {

}