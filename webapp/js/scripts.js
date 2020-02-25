// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
jQuery(".answerWrite input[type=submit]").on("click", addAnswer);
jQuery("#deleteAnswer").on("click", deleteAnswer);

function addAnswer(e) {
    e.preventDefault();

    var queryString = $("form[name=answer]").serialize();

    $.ajax({
        type: 'post',
        url: '/api/qna/addAnswer',
        data: queryString,
        dataType: 'json',
        error: onError,
        success: addAnswerSuccess
    });
}

function addAnswerSuccess(json, status) {
    var answer = json.answer;
    var answerTemplate = $("#answerTemplate").html();
    var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId);
    jQuery(".qna-comment-slipp-articles").prepend(template);
    var countOfComment = jQuery("#count-of-comment")[0].innerText;
    jQuery("#count-of-comment")[0].innerText = Number(countOfComment) + 1;

    jQuery("#writer").val("");
    jQuery("#contents").val("");
}

function deleteAnswer() {
    var answerId = this.dataset.answerid;
    var questionId = jQuery("input[name='questionId']").val();
    $.ajax({
        type: 'get',
        url: '/api/qna/deleteAnswer',
        data: {
            questionId: questionId,
            answerId: answerId
        },
        dataType: 'json',
        error: onError,
        success: deleteAnswerSuccess
    });
}

function deleteAnswerSuccess(json, status) {
    var answerId = json.answerId;
    jQuery("#answer_" + answerId).remove();
    var countOfComment = jQuery("#count-of-comment")[0].innerText;
    jQuery("#count-of-comment")[0].innerText = Number(countOfComment) - 1;
}

function updateAnswer(answerId) {
    debugger;
    e.preventDefault();

    $.ajax({
        type: 'post',
        url: '/api/qna/addAnswer',
        data: queryString,
        dataType: 'json',
        error: onError,
        success: onSuccess,
    });
}

function onError() {
    alert("error");
}

String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};