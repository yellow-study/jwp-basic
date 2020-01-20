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
    $("form[name='answer']").find("input[type='submit']").on("click", addAnswer);
    $(".qna-comment .link-delete-article").on("click", deleteAnswer);
});

function deleteAnswer(e) {
    e.preventDefault();

    var $this = jQuery(this);
    var answerId = $this.siblings("input[name=answerId]").val();

    $.ajax({
        url: "/qna/deleteAnswer"
        , data: {"answerId": answerId}
        , method: "POST"
        , dataType: "json"
        , success: function (result) {
            if (result.status === true) {
                $this.closest(".article").remove();
                alert("삭제성공");
            } else {
                alert("답변 삭제 문제 발생");
            }
        }
        , error: function () {
            alert("답변 삭제 문제 발생");
        }
    })
}

function addAnswer(e) {
    e.preventDefault();

    var data = $("form[name='answer']").serialize();

    $.ajax({
        url: "/qna/addAnswer"
        , data: data
        , method: "POST"
        , dataType: "json"
        , success: onAddSuccess
        , error: onAddError
    })
}

function onAddSuccess(result) {
    var articleTemplate = jQuery("#answerTemplate").html();
    var template = articleTemplate.format(result.writer, new Date(result.createdDate), result.contents, result.answerId);

    $(".qna-comment-slipp-articles").prepend(template);
    $(".qna-comment .link-delete-article").first().on("click", deleteAnswer);
}

function onAddError() {
    alert("답변 추가 중 문제 발생");
}