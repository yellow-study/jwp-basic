<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file="/include/header.jspf" %>
</head>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
        <div class="panel panel-default content-main">
            <form name="question" method="post"
                    <c:choose>
                        <c:when test="${isModify}"> action= "qna/modify" </c:when>
                        <c:otherwise> action= "/qna/create"</c:otherwise>
                    </c:choose>
            >
                <div class="form-group">
                    <label for="writer">글쓴이</label>
                    <input class="form-control" id="writer" name="writerId" value="${writerId}" readonly/>
                </div>
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="제목"
                           <c:if test="${isModify}">value="${question.title}"</c:if>/>
                </div>
                <div class="form-group">
                    <label for="contents">내용</label>
                    <textarea name="contents" id="contents" rows="5" class="form-control"><c:if
                            test="${isModify}">${question.contents}</c:if></textarea>
                </div>
                <button type="submit" class="btn btn-success clearfix pull-right">
                    <c:choose>
                        <c:when test="${isModify}">수정하기</c:when>
                        <c:otherwise>질문하기</c:otherwise>
                    </c:choose>
                </button>
                <div class="clearfix"/>
            </form>
        </div>
    </div>
</div>

<%@ include file="/include/footer.jspf" %>
</body>
</html>