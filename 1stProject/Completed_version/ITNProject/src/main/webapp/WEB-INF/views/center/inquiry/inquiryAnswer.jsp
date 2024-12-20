<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ITN MOVIE</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/style.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/header.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/footer.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/center/inquiry/inquiryAnswer.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
    <header>
        <jsp:include page="/common/header.jsp" />
    </header>
    <main>
        <div class="container-fluid" style="margin-top:50px">
            <div class="row">
           	 	<!-- 사이드바 -->
            	<jsp:include page="/common/sidebar.jsp"/>
                <!-- Reply Form -->
                <div class="col-lg-8 col-md-7 offset-md-1" style="width:600px; margin-top:80px">
                    <div class="content">
                        <form name="addForm" id="addForm" method="post">
                            <input type="hidden" id="inid" name="inid" value="${inid}">
                            <input type="hidden" name="writer" value="<c:out value='${sessionScope.memberVO.email}'/>">
                            <div class="mb-3">
                                <label for="replyContents" class="form-label">답변을 입력하세요</label>
                                <textarea id="replyContents" name="contents" rows="5" class="form-control"
                                    placeholder="답변 내용을 작성해 주세요"></textarea>
                            </div>
                            <div style="display: flex; justify-content: flex-end; gap: 10px;">
                                <button type="button" class="btn btn-primary btn-reply" onclick="fn_save()">저장</button>
                                <a href="javascript:void(0);" onclick="InquiryPage(); return false" style="text-decoration: none;">
                                    <button type="button" class="btn btn-secondary btn-reply">취소</button>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <jsp:include page="/common/footer.jsp" />
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
    </script>
    <script src="<c:url value='/resources/js/common/common.js'/>"></script>
    <script src="<c:url value='/resources/js/common/header.js'/>"></script>
    <script src="<c:url value='/resources/js/center/common/common.js'/>"></script>
    <script src="<c:url value='/resources/js/center/inquiry/inquiryAnswer.js'/>"></script>
</body>

</html>