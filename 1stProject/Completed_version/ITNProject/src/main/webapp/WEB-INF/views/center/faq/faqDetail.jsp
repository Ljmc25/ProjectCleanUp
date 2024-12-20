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
    <link rel="stylesheet" href="<c:url value='/resources/css/center/faq/faqDetail.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header>
        <jsp:include page="/common/header.jsp" />
    </header>
    <main>
        <div class="container-fluid" style="margin-left:100px">
            <div class="row">
           	 	<!-- 사이드바 -->
            	<jsp:include page="/common/sidebar.jsp"/>
                <!-- 게시판 영역 -->
                <div class="col-lg-9 col-md-8" style="width: 800px; margin-bottom:150px; margin-top:50px;">
                    <div class="content">
                        <h2 class="text-center mt-5">자주 묻는 질문</h2>
                        <div class="card">
                            <div class="card-header d-flex justify-content-between align-items-center" style="background-color: rgb(93, 159, 175);">
                                <h5 class="mb-0 text-center flex-grow-1" style="color:white; font-size: 20px;">
                                    <c:out value="${faqVO.title}" />
                                </h5>
                                <a href="javascript:void(0);" onclick="faqPage(); return false" class="btn btn-light">돌아가기</a>
                            </div>
                            <div class="card-body">
                                <h6 class="text-secondary fw-bold">작성자:
                                    <c:out value="${faqVO.writer}" />
                                </h6>
                                <h6>작성일:
                                    <c:out value="${faqVO.insertTime}" />
                                </h6>
                                <hr>
                                <p>
                                    <c:out value="${faqVO.contents}" />
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 게시판 끝 -->
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
    <script src="<c:url value='/resources/js/center/faq/faqDetail.js'/>"></script>
</body>

</html>