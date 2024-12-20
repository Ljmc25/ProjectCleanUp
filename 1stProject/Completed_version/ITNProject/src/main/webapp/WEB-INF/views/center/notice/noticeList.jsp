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
    <link rel="stylesheet" href="<c:url value='/resources/css/center/notice/noticeList.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header>
		<jsp:include page="/common/header.jsp"/>
	</header>
    <main>
        <div class="container-fluid" style="margin-bottom:230px; margin-left:50px">
            <div class="row">
           	 	<!-- 사이드바 -->
            	<jsp:include page="/common/sidebar.jsp"/>
                <!-- 게시판 영역 -->
                <div class="col-lg-9 col-md-8 board" style="width:800px; margin-left: 50px; margin-top: 150px;">
                    <h1>
                        <i class="fa fa-bullhorn" style="color: #0047AB;"></i> 공지사항
                    </h1>

                    <!-- 검색과 페이징을 폼으로 묶기 -->
                    <form id="searchForm" name="searchForm" method="get">
                        <div class="search-bar">
                            <input type="text" class="form-control d-inline" name="searchKeyword"
                                placeholder="검색어를 입력해주세요..." style="width: 200px; margin-top: 5px; margin-left: 470px;" />
                            <button type="submit" class="btn btn-dark" style="margin-bottom: 5px;"onclick="fn_egov_selectList()">
                         		<i class="fa fa-search"></i> 
                         	</button>
                        </div>

                        <!-- 게시판 테이블 -->
                        <table class="table table board-table">
                            <thead>
                                <tr>
                                    <th>번호</th>
                                    <th>제목</th>
                                    <th>등록일</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="data" items="${noticeList}">
                                    <tr>
                                    	<c:set var="id" value="${data.noid}"/>
                                        <td>${fn:split(id,'O')[1]}</td> <!-- 공지사항 ID (번호) -->
                                        <td><Strong><a href="javascript:void(0);" onclick="detailPage('${data.noid}'); return false">${data.title}</a></Strong>
                                        </td> <!-- 제목 -->
                                        <td>${data.insertTime}</td> <!-- 등록일 -->
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- 페이징 영역 (전자정부 프레임워크) -->
                        <div id="paging" class="text-center">
                            <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
                            <input type="hidden" id="pageIndex" name="pageIndex" />
                        </div>
                    </form>
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
    <script src="<c:url value='/resources/js/center/notice/noticeList.js'/>"></script>
</body>

</html>
