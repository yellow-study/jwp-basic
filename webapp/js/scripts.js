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
    $(".qna-comment").find("button[type='submit']").click(deleteAnswer);
});

function deleteAnswer(event) {
    event.preventDefault();

    var answerId = $(this).closest(".form-delete").find("input[name='answerId']").val();
    var data = {"answerId" : answerId}

    $.ajax({
        url: "/api/qna/deleteAnswer"
        , data : data
        , method : "POST"
        , dataType : "json"
        , success : deleteSuccess
    })
}

function addAnswer(event) {
    event.preventDefault();

    var answerFormData = $("form[name='answer']").serialize();

    //TODO remove
    console.log("serialized data : ", answerFormData);

    $.ajax({
        url: "/api/qna/addAnswer"
        , data: answerFormData
        , method : "POST"
        , dataType : "json"
        , success : onSuccess
        , error : onError
    })
}

function deleteSuccess(result) {

    if(result.status) {

    }
}

function onSuccess(result) {
    //TODO remove
    console.log("response data : ", result);

    var articleTemplate = jQuery("#answerTemplate").html();
    var template = articleTemplate.format(result.writer, new Date(result.createdDate), result.contents, result.answerId);

    debugger
    $(".qna-comment-slipp-articles").prepend(template)
}

function onError() {
  //TODO
}