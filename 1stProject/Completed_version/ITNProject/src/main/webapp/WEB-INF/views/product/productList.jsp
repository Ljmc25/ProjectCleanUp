<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ITN MOVIE</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/header.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/footer.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/style.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/product/productList.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header>
        <jsp:include page="/common/header.jsp"></jsp:include>
    </header>
    <main>
        <!-- main -->
        <div class="container">
            <form id="listForm" name="listForm" method="get">
                <input type="hidden" name="pdid">
                <!--  -->
                <h1>전체 영화</h1>
                <div class="category-menu" style="display: flex; justify-content: space-between; align-items: center;">
                    <div>
                        <button class="category-button">
                        	<a class="category-button" href="movie_all">무비차트</a>
                        </button>
                    </div>
                    <div class="mb-4">
                        <div class="input-group">
                            <input type="text" id="searchKeyword" name="searchKeyword"
                                class="form-control d-inline-block" placeholder="영화를 검색하세요..." oninput="filterMovies()"
                                onkeypress="fn_egov_selectList()" style="width: 200px; border-radius: 5px 0 0 5px; border: 1px solid #007bff;">
                            <button class="btn btn-primary" type="button" onclick="fn_egov_selectList()"> 검색 </button>
                        </div>
                    </div>
                </div>
                <div class="movie-list">
                    <c:forEach var="data" items="${productList}">
                        <div class="movie-item">
                            <div class="movie-item1">
                                <img src="<c:out value="${data.imageSmallUrl}" /> " class="card-img-top"alt="영화 1">
                                <div class="overlay" style="cursor: pointer;">
                                    <a href="javascript:fn_select('<c:out value="${data.pdid}"/>')" style="text-decoration: none; color: inherit;">
                                    	<c:out value="${data.contents}" />
                                    </a>
                                </div>
                            </div>
                            <div class="movie-title">
                                <img src="<c:out value="${data.rateUrl}" />"class="img-Age" alt="영화 1">
                                <c:out value="${data.title}" />
                            </div>
                            <div class="movie-description">
                                <p class="card-text">개봉일
                                    <c:out value="${data.openingDate}" />
                                </p>
                            </div>
                            <div class="buttons">
                                <button type="button" class="button book" onclick="reservePage('${data.title}');">예매하기</button>
                                <button type="button" class="btn btn-danger">좋아요</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div id="paging" class="text-center">
                    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
                    <input type="hidden" id="pageIndex" name="pageIndex" />
                </div>
            </form>
        </div>

    </main>
    <!-- 꼬리말 -->
    <footer>
        <jsp:include page="/common/footer.jsp"></jsp:include>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
    </script>
    <script src="<c:url value='/resources/js/common/common.js'/>"></script>
    <script src="<c:url value='/resources/js/common/header.js'/>"></script>
    <script src="<c:url value='/resources/js/product/productList.js'/>"></script>
</body>
</html>