String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};

function deleteAnswer(event) {
    event.preventDefault();

    var deleteButton = $(this);
    var deleteFormData = deleteButton.closest(".form-delete").serialize();

    $.ajax({
        url: "/api/qna/deleteAnswer"
        , data : deleteFormData
        , method : "POST"
        , dataType : "json"
        , success : function (result) {
            if(result.status) {
                deleteButton.closest(".article").remove();
            }
        }
        , error : function () {
            alert("댓글 삭제에 실패 했습니다.")
        }
    })
}

function addAnswer(event) {
    event.preventDefault();

    var answerFormData = $("form[name='answer']").serialize();

    $.ajax({
        url: "/api/qna/addAnswer"
        , data: answerFormData
        , method : "POST"
        , dataType : "json"
        , success : onSuccess
        , error : function () {
            alert("댓글 등록에 실패 했습니다.");
        }
    })
}

function deleteSuccess(result, targetElement) {

    if(result.status) {
        targetElement.closest(".article").remove();
    }
}

function onSuccess(result) {
    var articleTemplate = jQuery("#answerTemplate").html();
    var template = articleTemplate.format(result.writer, new Date(result.createdDate), result.contents, result.answerId);

    $(".qna-comment-slipp-articles").prepend(template)
}

$(function () {
    $("form[name='answer']").find("input[type='submit']").click(addAnswer);
    $(".qna-comment").on("click", ".form-delete", deleteAnswer);

});