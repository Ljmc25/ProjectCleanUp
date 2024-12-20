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
    <link rel="stylesheet" href="<c:url value='/resources/css/center/inquiry/inquiryUpdate.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header>
        <jsp:include page="/common/header.jsp" />
    </header>
    <main>
        <div class="container-fluid" style="margin-bottom: 170px; margin-left:100px">
            <div class="row">
           	 	<!-- 사이드바 -->
            	<jsp:include page="/common/sidebar.jsp"/>

                <!-- 수정 폼 영역 -->
                <div class="col-lg-6 col-md-8 mx-auto">
                    <div class="form-container">
                        <h2>수정 하기</h2>
                        <!-- 폼 영역 -->
                        <form id="updateForm" method="post">
                            <input type="hidden" name="inid" value="${inquiryVO.inid}">
                            <textarea name="contents"style="width: 95%; height: 200px; margin-left:20px">${inquiryVO.contents}</textarea>
                            <button type="button" id="update-bn" class="btn btn-primary" style="margin-left: 82%; margin-top: 20px;">수정</button>                     
                            <button type="button" id="back-bn" class="btn btn-secondary" style="margin-left: 82%; margin-top: 20px;">뒤로가기</button>                     
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
    <script src="<c:url value='/resources/js/center/inquiry/inquiryUpdate.js'/>"></script>
</body>

</html>