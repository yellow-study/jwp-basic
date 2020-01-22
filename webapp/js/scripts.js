String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

jQuery("#addAnswer").on("click", addAnswer);

function addAnswer(e) {
  e.preventDefault();

  var queryString = jQuery("form[name=answer]").serialize();

  jQuery.ajax({
      type : "post"
      , url : "/api/qna/addAnswer"
      , data : queryString
      , dataType : "json"
      , error : function () {
          console.log("error");
      }
      , success : function(json, status) {
        var answerTemplate = jQuery("#answerTemplate").html();
        var template = answerTemplate.format(json.writer, new Date(json.createdDatex), json.contents, json.answerId);

        jQuery(".qna-comment-slipp-articles").prepend(template);
      }
  })
}

jQuery(".qna-comment-slipp-articles").on("click", ".form-delete", function(event) {
    event.preventDefault();

    var answerId = jQuery(this).serialize();

    jQuery.ajax({
        type : "POST"
        , url : "/api/qna/deleteAnswer"
        , dataType : "json"
        , data : answerId
        , error : function () {
            console.log("error");
        }
        , success : function() {
            jQuery(event.target).closest(".article").remove();
            console.log("delete success");
        }
    })

});
