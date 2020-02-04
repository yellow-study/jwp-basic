<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file="/include/header.jspf" %>
</head>
<body>
<%@ include file="/include/navigation.jspf" %>

<c:choose>
    <c:when test="${auth.status}">
        <div class="container" id="main">
            <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
                <div class="panel panel-default content-main">
                    <form class="updateQuestionForm" method="post" action="#">
                        <div class="form-group">
                            <input class="form-control" type="hidden" name="questionId" value="${question.questionId}"/>
                        </div>
                        <div class="form-group">
                            <label for="writer">글쓴이</label>
                            <input class="form-control" id="writer" name="writer" placeholder="글쓴이" value="${question.writer}" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="title">제목</label>
                            <input type="text" class="form-control" id="title" name="title" value="${question.title}"/>
                        </div>
                        <div class="form-group">
                            <label for="contents">내용</label>
                            <textarea name="contents" id="contents" rows="5" class="form-control">${question.contents}</textarea>
                        </div>
                        <button type="submit" class="btn btn-success clearfix pull-right">수정하기</button>
                        <div class="clearfix" />
                    </form>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <script type="text/javascript">
            alert("작성자만 수정할 수 있습니다");
            history.back();
        </script>
    </c:otherwise>
</c:choose>
<%@ include file="/include/footer.jspf" %>
</body>
</html>