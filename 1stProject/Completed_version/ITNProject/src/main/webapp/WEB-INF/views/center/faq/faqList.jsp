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
    <link rel="stylesheet" href="<c:url value='/resources/css/center/faq/faqList.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
    <header>
        <jsp:include page="/common/header.jsp" />
    </header>
    <main>
        <div class="container-fluid" style="margin-left: 100px; margin-bottom: 150px">
            <div class="row">
           	 	<!-- 사이드바 -->
            	<jsp:include page="/common/sidebar.jsp"/>
                <!-- 게시판 영역 -->
                <div class="col-lg-9 col-md-8">
                    <div class="board-area">
                        <h2 class="mb-4">
                            <i class="fa fa-question-circle" style="color: crimson;" aria-hidden="true"></i> 자주 묻는 질문
                        </h2>
                        <!-- Form for search and pagination -->
                        <form id="faqForm" name="faqForm" method="get" onsubmit="return false;">
                            <!-- Search bar -->
                            <div class="search-bar mb-4">
                                <input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력하세요">
                                <button type="button" class="btn btn-dark" style="width: 100px;" onclick="fn_egov_selectList()">
                                    <i class="fa fa-search"></i> 
                                </button>
                            </div>
                            <!-- FAQ 리스트 -->
                            <div class="faq-list">
                                <c:forEach var="data" items="${faqList}" varStatus="status">
                                    <div class="faq-item" onclick="faqDetailPage('${data.faid}');">
                                        <h3>[
                                            <c:out value="${data.title}" />]
                                        </h3>
                                        <p>Q.
                                            <c:out value="${data.contents}" />
                                        </p>
                                    </div>
                                </c:forEach>
                            </div>

                            <!-- Pagination -->
                            <div id="paging" class="text-center mt-4">
                            	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
                                <input type="hidden" id="pageIndex" name="pageIndex" />
                            </div>
                        </form>
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
    <script src="<c:url value='/resources/js/center/faq/faqList.js'/>"></script>
</body>

</html>