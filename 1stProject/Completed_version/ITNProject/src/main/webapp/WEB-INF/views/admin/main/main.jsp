<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ITN MOVIE</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/managr.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/style.css'/>" />
    <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.2/main.min.css' rel='stylesheet' />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.2/main.min.js'></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        /* 디지털 시계 스타일 */
        .digital-clock {
            font-size: 48px;
            font-weight: bold;
            color: #007bff;
            text-align: center;
            margin-top: 20px;
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>

<body>    
    <main class="container-fluid" style="background-color: rgb(238, 238, 238);">
        <div class="row">
        	
            <!-- 메뉴 탭 -->
			<jsp:include page="/common/menu.jsp" />
            <!-- 중앙 컨텐츠 영역 -->
            <div class="col-md-10">
                <div class="d-flex align-items-center admin-welcome-box"
                    style="height: 5vh; width: 101.5%; margin-left: -0.6vw; background-color: white; border-radius: 10px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); padding: 10px;">
                    <h5 class="mb-1" style="color: #333;">환영합니다 관리자님</h5>
                </div>
                <!-- 차트 및 시간 정보 -->
                <div class="row"
                    style="background-color: #f8f9fa; margin-top: 1vh; padding: 20px; border-radius: 10px; box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);">
                    <div class="col-md-6 mt-4">
                        <div
                            style="background-color: white; border-radius: 10px; padding: 20px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);">
                            <h2 class="text-left">일일 사이트 접속 현황</h2>
                            <canvas id="visitorChart" width="600" height="300"></canvas>
                        </div>
                        <div
                            style="background-color: white; border-radius: 10px; padding: 20px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); margin-top: 20px;">
                            <h2 class="text-left">일일 예매 현황</h2>
                            <canvas id="visitorChart2" width="600" height="300"></canvas>
                        </div>
                    </div>
                    <div class="col-md-6 mt-4 text-center">
                        <!-- 디지털 시계 -->
                        <div
                            style="background-color: white; padding: 20px; text-align: center; margin-top: 20px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);">
                            <div id="digitalClock" style="font-size: 48px; font-weight: bold; color: #007bff;">
                                <script type="text/javascript">
                                    function updateClock() {
                                        var now = new Date();
                                        var hours = String(now.getHours()).padStart(2, '0');
                                        var minutes = String(now.getMinutes()).padStart(2, '0');
                                        var seconds = String(now.getSeconds()).padStart(2, '0');
                                        document.write(hours + ":" + minutes + ":" + seconds);
                                    }
                                    updateClock();
                                </script>
                            </div>
                        </div>
                        <div
                            style="background-color: white; padding: 15px; text-align: center; margin-top: 20px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);">
                            <div id="calendar"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<c:url value='/resources/js/admin/hover.js'/>"></script>
    <script src="<c:url value='/resources/js/admin/graph.js'/>"></script>
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', function () {
            // FullCalendar 초기화
            var calendarEl = document.getElementById('calendar');
            if (calendarEl) {
                var calendar = new FullCalendar.Calendar(calendarEl, {
                    initialView: 'dayGridMonth'
                });
                calendar.render();
            }

            // 1초마다 시계 업데이트
            setInterval(function () {
                var clockElement = document.getElementById('digitalClock');
                if (clockElement) {
                    var now = new Date();
                    var hours = String(now.getHours()).padStart(2, '0');
                    var minutes = String(now.getMinutes()).padStart(2, '0');
                    var seconds = String(now.getSeconds()).padStart(2, '0');
                    clockElement.textContent = hours + ":" + minutes + ":" + seconds;
                }
            }, 1000);
        });
    </script>
</body>

</html>