<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ITN MOVIE</title>
<!-- jQuery CDN 추가 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Bootstrap CDN 추가 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value='/resources/css/admin/managr.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/admin/style.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/admin/border.css'/>" />     
</head>
<body>
	<main class="container-fluid"
		style="background-color: rgb(238, 238, 238);">
		<div class="row">
			<!-- 메뉴 탭 -->
			<jsp:include page="/common/menu.jsp" />
            <div class="col-md-10 d-flex align-items-start" style="padding-left: 0; padding-right: 0;">
                 <div style="background-color: white; height: 100%; flex-grow: 1; max-width: 100%; margin-left: 10vw;">
                    <h2 class="text-left mt-4 ms-3" style="padding-bottom: 2vh;">자주 묻는 질문 수정하기</h2>
                    <form id="detailForm" name="detailForm" action="<c:url value='/qna/edit'/>" method="post">
                    <input type="hidden" name="faid" value="<c:out value="${qnaVO.faid}" />">
                        <div class="mb-3 ms-3" style="max-width: 50%;">
                            <label for="title" class="form-label"> 제목</label>
                            <input class="form-control" id="title" name="title" value="<c:out value="${qnaVO.title}" />" required>
                        </div>
                        <div class="mb-3 ms-3" style="max-width: 50%;">
			                <label for="contents" class="form-label">내용</label>
			                <textarea class="form-control" id="contents" name="contents" 
			                          placeholder="내용을 입력하세요" rows="4" required>${qnaVO.contents}</textarea>
			            </div>
			            <div class="d-flex justify-content-center" style="margin-bottom: 3vh; margin-top: 5vh;">
			                <button type="submit" class="btn btn-success btn-lg me-5">수정</button>
			                <button type="button" class="btn btn-danger btn-lg" onclick="cancelForm()">취소</button>
			            </div>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <script src="<c:url value='/resources/js/admin/hover.js'/>"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">
    function cancelForm() {
		window.location.href = "<c:url value='/admin/faq' />"; // 공지사항 메인 페이지로 리다이렉트
	}
    </script>
</body>
</html>
