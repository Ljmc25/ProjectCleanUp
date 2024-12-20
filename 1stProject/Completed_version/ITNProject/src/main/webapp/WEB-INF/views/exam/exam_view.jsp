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
    <link rel="stylesheet" href="<c:url value='/resources/css/exam/view.css'/>">
</head>
<body>
    <main>
        <form class="w-50 h-50" id="viewForm">
            <h1 class="mb-3">정보</h1>
            <div class="mb-5">
                <p class="insertDate">등록일 : <c:out value="${examVO.insertTime}"/><p>
            </div>
            <div class="mb-3">
                <label class="form-label">TITLE</label>
                <p class="form-control">
                	<c:out value="${examVO.title}"/>
                </p>
            </div>
            <div class="mb-3">
                <label class="form-label">CONTENTS</label>
                <p class="form-control">
                	<c:out value="${examVO.contents}"/>
                </p>
            </div>
            <button type="button" class="btn btn-secondary" id="listView" style="float: right;">목록보기</button>
        </form>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
		sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
	</script>
	<script src="<c:url value='/resources/js/exam/common.js'/>"></script>
	<script src="<c:url value='/resources/js/exam/view.js'/>"></script>
</body>
</html>