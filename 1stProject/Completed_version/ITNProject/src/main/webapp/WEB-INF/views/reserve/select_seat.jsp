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
    <link rel="stylesheet" href="<c:url value='/resources/css/reserve/select_seat.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
    <header>
        <jsp:include page="/common/header.jsp" />
    </header>
    <main>
        <form id="reserveForm" method="get">
            <input type="hidden" id="email" name="email" value="${sessionScope.memberVO.email}">
            <input type="hidden" id="selectNum" name="selectNum">
            <input type="hidden" id="selectSeat" name="selectSeat">
            <input type="hidden" id="childrenNum" name="childrenNum">
            <input type="hidden" id="teenagerNum" name="teenagerNum">
            <input type="hidden" id="adultNum" name="adultNum">
            <input type="hidden" id="agedNum" name="agedNum">
            <input type="hidden" id="scid" name="scid" value="${screeningInfo.scid}">
            <input type="hidden" id="title" name="title" value="${screeningInfo.title}">
            <input type="hidden" id="totalprice" name="totalprice">
            <input type="hidden" id="city" name="city" value="${screeningInfo.city}">
            <input type="hidden" id="cinema" name="cinema" value="${screeningInfo.cinema}">
            <input type="hidden" id="branch" name="branch" value="${screeningInfo.branch}">
            <input type="hidden" id="theater" name="theater" value="${screeningInfo.theater}">
            <input type="hidden" id="address" name="address" value="${screeningInfo.address}">
            <input type="hidden" id="showingTime" name="showingTime" value="${screeningInfo.showingTime}">
            <input type="hidden" id="startTime" name="startTime" value="${screeningInfo.startTime}">
            <input type="hidden" id="endTime" name="endTime" value="${screeningInfo.endTime}">
            <input type="hidden" id="runningTime" name="runningTime"
                value="${ProductVO.runningTime != null ? ProductVO.runningTime : 0}">
            <input type="hidden" id="imageSmallUrl" name="imageSmallUrl" value="${ProductVO.imageSmallUrl}">
            <input type="hidden" id="childrenPrice" name="childrenPrice">
            <input type="hidden" id="teenagerPrice" name="teenagerPrice">
            <input type="hidden" id="adultPrice" name="adultPrice">
            <input type="hidden" id="agedPrice" name="agedPrice">
        </form>

        <!-- TITLE -->
        <div class="TITLE-11">
            <div id="title">
                <h4>빠른예매</h4>
            </div>
        </div>
        <!-- 전체영역 -->
        <div class="ZONE-1">
            <!-- 관람인원선택 -->
            <div class="reserve">
                <!-- 관람인원선택 TITLE -->
                <div class="title-1">
                    <div>
                        <p class="left">관람인원선택</p>
                    </div>
                    <div class="right"> <button class="reset">reset</button> </div>
                </div>
                <!-- 좌석 인원수 버튼 -->
                <div class="flex2">
                    <div class="btn-group flex2-btn" role="group" aria-label="Adult group" id="adult-group">
                        <p id="adult-label">성인</p>
                        <button type="button" id="adult-minus">-</button>
                        <button type="button" id="adult-zero">0</button>
                        <button type="button" id="adult-plus">+</button>
                    </div>
                    <div class="btn-group flex2-btn" role="group" aria-label="Youth group" id="youth-group">
                        <p id="youth-label">청소년</p>
                        <button type="button" id="youth-minus">-</button>
                        <button type="button" id="youth-zero">0</button>
                        <button type="button" id="youth-plus">+</button>
                    </div>
                    <div class="btn-group flex2-btn" role="group" aria-label="Child group" id="child-group">
                        <p id="child-label">어린이</p>
                        <button type="button" id="child-minus">-</button>
                        <button type="button" id="child-zero">0</button>
                        <button type="button" id="child-plus">+</button>
                    </div>
                    <div class="btn-group flex2-btn" role="group" aria-label="Senior group" id="senior-group">
                        <p id="senior-label">노인</p>
                        <button type="button" id="senior-minus">-</button>
                        <button type="button" id="senior-zero">0</button>
                        <button type="button" id="senior-plus">+</button>
                    </div>
                </div>
                <!-- 좌석선택 버튼 -->
                <div class="reserve-area">
                    <div class="screen-label">
                        <p>SCREEN</p>
                    </div>
                    <!-- 세로버튼 -->
                    <div class="btn-group-vertical col-buttons" role="group" aria-label="Vertical button group">
                        <button id="col-A" type="button" class="btn btn-dark">A</button>
                        <button id="col-B" type="button" class="btn btn-dark">B</button>
                        <button id="col-C" type="button" class="btn btn-dark">C</button>
                        <button id="col-D" type="button" class="btn btn-dark">D</button>
                        <button id="col-E" type="button" class="btn btn-dark">E</button>
                        <button id="col-F" type="button" class="btn btn-dark">F</button>
                    </div>

                    <!-- A열 좌석 -->
                    <div class="btn-toolbar row-buttons" role="toolbar" aria-label="A row">
                        <button id="A1" type="button" class="btn btn-outline-success">A1</button>
                        <button id="A2" type="button" class="btn btn-outline-success">A2</button>
                        <button id="A3" type="button" class="btn btn-outline-success">A3</button>
                        <button id="A4" type="button" class="btn btn-outline-success">A4</button>
                        <button id="A5" type="button" class="btn btn-outline-success">A5</button>
                        <button id="A6" type="button" class="btn btn-outline-success">A6</button>
                        <button id="A7" type="button" class="btn btn-outline-success">A7</button>
                        <button id="A8" type="button" class="btn btn-outline-success">A8</button>
                    </div>

                    <!-- B열 좌석 -->
                    <div class="btn-toolbar row-buttons" role="toolbar" aria-label="B row">
                        <button id="B1" type="button" class="btn btn-outline-success">B1</button>
                        <button id="B2" type="button" class="btn btn-outline-success">B2</button>
                        <button id="B3" type="button" class="btn btn-outline-success">B3</button>
                        <button id="B4" type="button" class="btn btn-outline-success">B4</button>
                        <button id="B5" type="button" class="btn btn-outline-success">B5</button>
                        <button id="B6" type="button" class="btn btn-outline-success">B6</button>
                        <button id="B7" type="button" class="btn btn-outline-success">B7</button>
                        <button id="B8" type="button" class="btn btn-outline-success">B8</button>
                    </div>

                    <!-- C열 좌석 -->
                    <div class="btn-toolbar row-buttons" role="toolbar" aria-label="C row">
                        <button id="C1" type="button" class="btn btn-outline-success">C1</button>
                        <button id="C2" type="button" class="btn btn-outline-success">C2</button>
                        <button id="C3" type="button" class="btn btn-outline-success">C3</button>
                        <button id="C4" type="button" class="btn btn-outline-success">C4</button>
                        <button id="C5" type="button" class="btn btn-outline-success">C5</button>
                        <button id="C6" type="button" class="btn btn-outline-success">C6</button>
                        <button id="C7" type="button" class="btn btn-outline-success">C7</button>
                        <button id="C8" type="button" class="btn btn-outline-success">C8</button>
                    </div>

                    <!-- D열 좌석 -->
                    <div class="btn-toolbar row-buttons" role="toolbar" aria-label="D row">
                        <button id="D1" type="button" class="btn btn-outline-success">D1</button>
                        <button id="D2" type="button" class="btn btn-outline-success">D2</button>
                        <button id="D3" type="button" class="btn btn-outline-success">D3</button>
                        <button id="D4" type="button" class="btn btn-outline-success">D4</button>
                        <button id="D5" type="button" class="btn btn-outline-success">D5</button>
                        <button id="D6" type="button" class="btn btn-outline-success">D6</button>
                        <button id="D7" type="button" class="btn btn-outline-success">D7</button>
                        <button id="D8" type="button" class="btn btn-outline-success">D8</button>
                    </div>

                    <!-- E열 좌석 -->
                    <div class="btn-toolbar row-buttons" role="toolbar" aria-label="E row">
                        <button id="E1" type="button" class="btn btn-outline-success">E1</button>
                        <button id="E2" type="button" class="btn btn-outline-success">E2</button>
                        <button id="E3" type="button" class="btn btn-outline-success">E3</button>
                        <button id="E4" type="button" class="btn btn-outline-success">E4</button>
                        <button id="E5" type="button" class="btn btn-outline-success">E5</button>
                        <button id="E6" type="button" class="btn btn-outline-success">E6</button>
                        <button id="E7" type="button" class="btn btn-outline-success">E7</button>
                        <button id="E8" type="button" class="btn btn-outline-success">E8</button>
                    </div>

                    <!-- F열 좌석 -->
                    <div class="btn-toolbar row-buttons" role="toolbar" aria-label="F row">
                        <button id="F1" type="button" class="btn btn-outline-success">F1</button>
                        <button id="F2" type="button" class="btn btn-outline-success">F2</button>
                        <button id="F3" type="button" class="btn btn-outline-success">F3</button>
                        <button id="F4" type="button" class="btn btn-outline-success">F4</button>
                        <button id="F5" type="button" class="btn btn-outline-success">F5</button>
                        <button id="F6" type="button" class="btn btn-outline-success">F6</button>
                        <button id="F7" type="button" class="btn btn-outline-success">F7</button>
                        <button id="F8" type="button" class="btn btn-outline-success">F8</button>
                    </div>
                </div>
            </div>

            <!-- 영화정보 -->
            <div class="reserve-info">
                <!-- 영화정보 -->
                <div class="IMG">
                    <img src="../<c:out value='${ProductVO.imageSmallUrl}'/>" alt="영화제목">
                </div>
                <!-- 영화텍스트 -->
                <div class="movie-text">
                    <br>
                    <p>${screeningInfo.title} <span>2D</span>
                        <br>
                        ${screeningInfo.branch} ITN
                        <br>
                        ${screeningInfo.theater}관
                        <br>
                        ${screeningInfo.showingTime}
                    </p>
                </div>
                <div class="info-container">
                    <div class="movie-info">
                        <div class="select-title">
                            <p>선택 좌석</p>
                        </div>
                        <div class="button-grid select-btn">
                            <button id="select-button-1" class="btn btn-outline-dark" type="button">-</button>
                            <button id="select-button-2" class="btn btn-outline-dark" type="button">-</button>
                            <button id="select-button-3" class="btn btn-outline-dark" type="button">-</button>
                            <button id="select-button-4" class="btn btn-outline-dark" type="button">-</button>
                            <button id="select-button-5" class="btn btn-outline-dark" type="button">-</button>
                            <button id="select-button-6" class="btn btn-outline-dark" type="button">-</button>
                            <button id="select-button-7" class="btn btn-outline-dark" type="button">-</button>
                            <button id="select-button-8" class="btn btn-outline-dark" type="button">-</button>
                        </div>
                    </div>
                    <div class="movie-info2">
                        <p class="TOTAL-text">결제하기</p>
                        <div id="total-price">총 금액 <br>: 0원</div>

                        <div class="info-btn">
                            <button type="button" class="btn btn-secondary info-btn1">
                                <p class="info-text">뒤로가기</p>
                            </button>
                            <button type="button" class="btn btn-info info-btn2" id="payment">
                                <p class="info-text2">결제하기</p>
                            </button>
                        </div>
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
        // 서버에서 전달받은 상영 정보 중 theater 값
        const theater = "${screeningInfo.theater}";
        //서버에서 전달한 remainSeats 값을 가져옵니다.
        const reservedSeats = "${screeningInfo.reservedSeats}".split(',');

        sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
    </script>
    <script src="<c:url value='/resources/js/common/common.js'/>"></script>
    <script src="<c:url value='/resources/js/common/header.js'/>"></script>
    <script src="<c:url value='/resources/js/reserve/select_seat.js'/>"></script>
</body>

</html>