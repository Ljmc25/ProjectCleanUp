<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exam</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/exam/common.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/exam/addition.css'/>">
</head>
<body>
    <main>
        <form class="w-50 h-50" id="addForm">
            <h1 class="mb-3">등록</h1>
            <div class="mb-3">
                <label for="title" class="form-label">TITLE</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="제목 입력">
            </div>
            <div class="mb-3">
                <label for="contents" class="form-label">CONTENTS</label>
                <textarea class="form-control" id="contents" name="contents" rows="5"></textarea>
            </div>
            <button type="button" class="btn btn-danger" id="cancel" style="float: right; margin-left: 2%">취소</button>
            <button type="button" class="btn btn-primary" id="add" style="float: right;">등록</button>
        </form>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
		sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
	</script>
	<script src="<c:url value='/resources/js/exam/common.js'/>"></script>
	<script src="<c:url value='/resources/js/exam/addition.js'/>"></script>
</body>
</html>