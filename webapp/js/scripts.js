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
        // console.log("success");
      }
  })
}

jQuery(".qna-comment-slipp-articles").on("click", "link-delete-article", function(event) {
    var target = jQuery(event.target).parent();
    var id = target.serialize();

    jQuery.ajax({
        type : "delete"
        , url : "/api/qna/deleteAnswer?" + id
        , dataType : "json"
        , error : function () {
            console.log("error");
        }
        , success : function() {
            target.closest(".article").remove();
            console.log("delete success");
        }
    })

});
