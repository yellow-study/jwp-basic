String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};

jQuery(function () {
    debugger;
    $("form[name='answer']").find("input[type='submit']").click(addAnswer);
    $("button.link-delete-article").click(deleteAnswer);
});

function addAnswer() {
    event.preventDefault();
    var data = $("form[name='answer']").serialize();

    console.log(data);

    $.ajax({
        url: "/qna/addAnswer"
        , data: data
        , method: "POST"
        , dataType: "json"
        , success: addAnswerSuccess
        , error: onError
    })
}

function addAnswerSuccess(result) {
    console.log(result);
    var articleTemplate = jQuery("#answerTemplate").html();
    var template = articleTemplate.format(result.answer.writer, new Date(result.answer.createdDate), result.answer.contents, result.answer.answerId);

    $(".qna-comment-slipp-articles").prepend(template);
}

function deleteAnswer() {
    event.preventDefault();
    var deleteButton = $(this);
    var answerId = deleteButton.siblings("input[name='answerId']").val();

    console.log(answerId);

    $.ajax({
        url: "/qna/deleteAnswer"
        , data: {"answerId": answerId}
        , method: "POST"
        , dataType: "json"
        , success: function (isSuccess) {
            if(!isSuccess){
                onError();
                return;
            }

            deleteButton.closest(".article").remove();
            alert("답변을 삭제했습니다.")
        }
        , error: onError
    })
}

function onError() {
    alert("다시 시도해주세요");
}