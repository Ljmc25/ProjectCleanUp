<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ITN MOVIE</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/style.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/header.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/footer.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/reserve/reserve.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header>
        <jsp:include page="/common/header.jsp" />
    </header>
    <main>
        <!-- 빠른예매 영역 TITLE -->
        <div class="til-util">
            <h4><b>빠른예매</b></h4>
        </div>
        <!-- 예매 날짜 선택 달력 -->
        <div class="date-selection">
            <ul class="nav justify-content-center nav-fill date-list">
                <!-- 달력 -->
                <li class="nav-item">
                    <div class="custom-date-picker">
                        <input type="text" placeholder="날짜를 선택해주세요." id="dateSelect" readonly onchange="fetchMoviesByDate()" />
                        <div class="calendar-icon" onclick="toggleCalendar()">
                        	<img src="resources/images/reserve/date.png" width="30">
                        </div>
                        <div class="calendar" id="calendar">
                            <div class="calendar-header">
                                <button class="month-button" onclick="prevMonth()">❮</button>
                                <span id="currentMonth"></span>
                                <button class="month-button" onclick="nextMonth()">❯</button>
                            </div>
                            <div class="days-of-week">
                                <span>SUN</span>
                                <span>MON</span>
                                <span>TUE</span>
                                <span>WED</span>
                                <span>THU</span>
                                <span>FRI</span>
                                <span>SAT</span>
                            </div>
                            <div class="days" id="days"></div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>

        <div class="reservation-container">
            <!-- 영화 영역 -->
            <div class="movie-reserve-area">
                <div>
                    <p class="title1">영화</p>
                </div>
                <!-- 영화:버튼 -->
                <div class="movie-all">
                    <div class="movie-select-button" role="group" aria-label="Basic radio toggle button group">
                        <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
                        <label class="btn custom-button" for="btnradio1">
                            <p id="movie-text">전체</p>
                        </label>
                    </div>
                </div>
                <!-- 영화 리스트! -->
                <div class="movie-list" id="movieList">
                    <ul>
                        <c:if test="${empty movies}">
                            <div>영화 목록이 없습니다.</div>
                        </c:if>
                        <!-- AJAX로 추가될 영화 목록 -->
                    </ul>
                </div>
            </div>
            <!-- 극장 선택 영역 -->
            <div class="theater-choice">
                <!-- 극장 TITLE -->
                <div>
                    <p class="title2">극장</p>
                </div>
                <div class="theater-container">
                    <!-- 극장 버튼-->
                    <div class="theater-btn-container">
                        <div class="theater-menu">
                            <input type="radio" class="btn-check" name="theater-radio" id="theater1" autocomplete="off"
                                checked>
                            <label class="btn custom-button" for="theater1" id="theater-bt">
                                <p class="theater-text">전체</p>
                            </label>
                        </div>
                    </div>
                    <div class="theater-list-container">
                        <div class="theater-list">
                            <ul class="list-group list-group-flush">
                                <!-- 극장지역 -->
                                <li class="theater-itemS" data-region="S">서울</li> <!-- 서울 극장 표시 -->
                                <li class="theater-itemI" data-region="I">인천</li> <!-- 인천 극장 표시 -->
                                <li class="theater-itemB" data-region="B">부산</li> <!-- 부산 극장 표시 -->
                            </ul>
                        </div>
                        <!-- 극장 -->
                        <div class="theater-list2" style="display:none;">
                            <ul class="list-group list-group-flush">
                                <li class=" theater-item2" data-theater="item1">강남</li>
                                <li class=" theater-item2" data-theater="item2">신촌</li>
                                <li class=" theater-item2" data-theater="item3">판교</li>
                            </ul>
                        </div>
                        <!-- 극장 -->
                        <div class="theater-list3" style="display:none;">
                            <ul class="list-group list-group-flush">
                                <li class=" theater-item2" data-theater="item4">송도</li>
                                <li class=" theater-item2" data-theater="item5">영종</li>
                                <li class=" theater-item2" data-theater="item6">논현</li>
                            </ul>
                        </div>
                        <!-- 극장 -->
                        <div class="theater-list4" style="display:none;">
                            <ul class="list-group list-group-flush">
                                <li class=" theater-item2" data-theater="item7">시청</li>
                                <li class=" theater-item2" data-theater="item8">서면</li>
                                <li class=" theater-item2" data-theater="item9">동래</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 상영정보  -->
            <div class="time-choice">
                <div>
                    <p class="title3">시간</p>
                </div>
                <!--  상영정보 -->
                <div class="time-select" style="display: none;" id="screeningInfoContainer">
                    <ol class="list-group">
                        <!--AJAX 상영정보  -->
                    </ol>
                    <c:if test="${empty reserveInfoList}">
                        <div>상영 정보가 없습니다.</div>
                    </c:if>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <jsp:include page="/common/footer.jsp" />
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
		// JSP에서 받아온 title 값을 자바스크립트로 전달
	    const serverTitle = "${title}";  // JSP에서 인코딩된 title을 받아옴
		console.log("서버에서 넘긴 제목:", serverTitle);
	</script>
    <script>
        sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
    </script>
    <script src="<c:url value='/resources/js/common/common.js'/>"></script>
    <script src="<c:url value='/resources/js/common/header.js'/>"></script>
    <script src="<c:url value='/resources/js/reserve/reserve.js'/>"></script>
</body>

</html>