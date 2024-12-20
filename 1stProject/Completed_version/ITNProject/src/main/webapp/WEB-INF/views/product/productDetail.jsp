<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/product/productDetail.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
    <header>
        <jsp:include page="/common/header.jsp"></jsp:include>
    </header>
    <main>
        <!-- main -->
        <!-- 본문 -->
        <div class="first-divided" style="background-image:url('<c:out value="${productVO.imageMainUrl}"/>');">
        <form id="detailForm" name="detailForm" method="post">
            <input type="hidden" name="pdid" value="${productVO.pdid}" />
            <!--          부서번호 -->
            <div class="container mt-10">
                <div class="movie-header" style="background-image:url('<c:out value="${productVO.imageBackgroundUrl}" />');" >
                <div class="col-md-6 .ms-auto">
                    <img src="<c:out value="${productVO.imageSmallUrl}" />" class="img-fluid movie-image" alt="영화
                    1"/>
                    <br />
                    <button type="button" class="btn btn-primary btn-lg mt-3" onclick="reservePage('${productVO.title}')">예매하기</button>
                    <button type="button" class="btn btn-danger btn-lg mt-3">좋아요♥</button>
                </div>
            </div>
            </div>
            <!-- </div> -->
            <!-- 버튼  -->
            <div class="container">
                <div class="category-menu">
                    <button type="button" id="bn1" class="category-button">
                        주요정보
                    </button>
                    <button id="bn2" class="category-button">
                        예고편
                    </button>
                    <button id="bn3" class="category-button">
                        관람평
                    </button>
                </div>
            </div>
            <!-- 주요정보 -->
            <div class="box" id="box1">
                <div class="container">
                    <div class="col-md-6 color">
                        <h1 class="movie-title ">
                            <c:out value="${productVO.title}" />
                        </h1>

                        <div class="color3">
                            <h5>주요정보</h5>
                        </div>
                        <p class="lead movie-description">
                            <c:out value="${productVO.contents}" />
                        </p>
                        <div class="container mt-4">
                            <div class="row">
                                <div class="col-md-4">
                                    <h5>출연진:</h5>
                                    <c:out value="${productVO.movieCast}" />
                                </div>
                                <div class="col-md-4">
                                    <h5>감독</h5>
                                    <p>
                                        <c:out value="${productVO.director}" />
                                    </p>
                                </div>
                                <div class="col-md-4">
                                    <h5>개봉일</h5>
                                    <p>
                                        <c:out value="${productVO.openingDate}" />
                                    </p>

                                </div>
                                <div class="col-md-4">
                                    <h5>장르:</h5>
                                    <p>
                                        <c:out value="${productVO.genre}" />
                                    </p>
                                </div>
                                <div class="col-md-4">
                                    <h5>등급</h5>
                                    <p>
                                        <c:out value="${productVO.rate}" />
                                    </p>
                                </div>
                                <div class="col-md-4">
                                    <h5>관람시간</h5>
                                    <p>
                                        <c:out value="${productVO.runningTime}" />
                                    </p>
                                </div>
                            </div>
                        </div>
                        <hr class="my-4" />
                        <div class="color2">
                        </div>
                    </div>
                </div>
            </div>
            <!-- 예고편 -->
            <div class="box" id="box2" style="display:none">
                <div class="container">
                    <div class="color">
                        <h3>예고편</h3>
                    </div>
                    <div class="embed-responsive embed-responsive-16by9 mb-4">
                        <iframe id="mainTrailer" class="embed-responsive-item"
                            src="<c:out value='${productVO.trailerMainUrl}' />" width="1280" height="720">
                        </iframe>
                    </div>
                </div>
                <!-- 예고편1 -->
                <div class="container">
                    <div class="video-row">
                        <button style="background-color: black;"
                            onclick="changeTrailer('<c:out value='${productVO.trailerMainUrl}' />')">

                            <div class="video-item" style="width: 350px; 
                        height: 210px ;
                        background-color: black;"> <!-- Adjust width and height -->
                                <div class="color3">
                                    <h3></h3>
                                </div>

                                <div class="embed-responsive mb-1">
                                    <iframe class="embed-responsive-item"
                                        src="<c:out value='${productVO.trailerMainUrl}' />"
                                        style="pointer-events: none; width: 100%; height: 100%;"></iframe>
                                    <!-- Full width and height -->
                                </div>
                            </div>
                        </button>
                        <!-- 예고편2 -->
                        <button style="background-color: black;" onclick="changeTrailer('<c:out value='${productVO.trailer1Url}' />')">
                            <div class="video-item" style="width: 350px; height: 210px ; background-color: black;"> <!-- Adjust width and height -->
                                <div class="color3">
                                    <h3></h3>
                                </div>
                                <div class="embed-responsive mb-1">
                                    <iframe class="embed-responsive-item" src="<c:out value='${productVO.trailer1Url}' />"
                                        style="pointer-events: none; width: 100%; height: 100%;"></iframe>
                                    <!-- Full width and height -->
                                </div>
                            </div>
                        </button>
                        <!-- 예고편3 -->
                        <button style="background-color: black;" onclick="changeTrailer('<c:out value='${productVO.trailer2Url}' />')">
                            <div class="video-item" style="width: 350px; height: 210px ; background-color: black;"> <!-- Adjust width and height -->
                                <div class="color3">
                                    <h3></h3>
                                </div>
                                <div class="embed-responsive mb-1">
                                    <iframe class="embed-responsive-item" src="<c:out value='${productVO.trailer2Url}' />" 
                                    style="pointer-events: none; width: 100%; height: 100%;"></iframe>
                                    <!-- Full width and height -->
                                </div>
                            </div>
                        </button>
                    </div>
                </div>
                <!-- 스틸컷  -->
                <div class="container mt-5">
                    <div class="text-center mb-4">
                        <h3 class="color">스틸컷</h3>
                    </div>
                    <div class="row">
                        <div class="col-md-4 mb-4">
                            <div class="card shadow-sm">
                                <a href="<c:out value=" ${productVO.imageThril1Url}" />" target="_blank">
                                <img src="<c:out value=" ${productVO.imageThril1Url}" />" class="card-img-top" alt="스틸컷
                                1">
                                </a>
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="card shadow-sm">
                                <a href="<c:out value=" ${productVO.imageThril2Url}" />" target="_blank">
                                <img src="<c:out value=" ${productVO.imageThril2Url}" />" class="card-img-top" alt="스틸컷
                                2">
                                </a>
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="card shadow-sm">
                                <a href="<c:out value=" ${productVO.imageThril3Url}" />" target="_blank">
                                <img src="<c:out value=" ${productVO.imageThril3Url}" />" class="card-img-top" alt="스틸컷
                                3">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 실관람평 -->
            <div class="box" id="box3">
                <div class="container">
                    <div class="color2">
                        <h3>실관람평</h3>
                    </div>
                    <div class="review-section">
                        <div id="reviews">
                            <!-- 리뷰 카드 생성됨 -->
                        </div>
                        <div class="color4">
                            <h5 class="mt-4">등록</h5>
                        </div>
                        <div class="color3">
                            <div class="mb-3 color5">
                                <label for="review" class="form-label">관람평</label>
                            </div>
                            <textarea class="form-control" id="review" name="review" required></textarea>
                        </div>
                        <br>
                        <button type="button" class="btn btn-primary" id="review-bn" style="margin-bottom: 30px">관람평 등록</button>
                        <!-- 	    테이블  -->
                        <div>
                            <table class="table color">
                                <thead>
                                    <tr>
                                        <th scope="col">글번호</th>
                                        <th scope="col">ID</th>
                                        <th scope="col">리뷰</th>
                                        <th scope="col">등록일자</th>
                                    </tr>
                                </thead>
                                <tbody>
                                
                                        
                                    <c:forEach var="data" items="${productReviewVO}">
                                        <tr>
                                        	<c:set var="id" value="${data.prid}"/>
                                        	<c:set var="date" value="${data.insertTime}"/>
                                            <!-- 	 		    자동완성 : ctrl + space  -->
                                            <td>
                                            	RW${fn:split(id,'R')[1]}
                                            </td>
                                            <td>
                                                <c:out value="${data.writer}" />
                                            </td>
                                            <td>
                                                <c:out value="${data.review}" />
                                            </td>
                                            <td>
                                            	<c:out value="${data.insertTime}" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        </div>
    </main>
    <footer>
        <jsp:include page="/common/footer.jsp"></jsp:include>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
    </script>
    <script src="<c:url value='/resources/js/common/common.js'/>"></script>
    <script src="<c:url value='/resources/js/common/header.js'/>"></script>
    <script src="<c:url value='/resources/js/product/productDetail.js'/>"></script>
</body>

</html>