<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
    	<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>Showing Data Insert</title>
    	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.10.0/css/bootstrap-datepicker.min.css"/>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    	<link rel="stylesheet" href="<c:url value='/resources/css/showing/showing.css'/>">
	</head>
	<body>
	    <p class="h2 mb-5">Showing Data Insert</p>
    	<div class="container mb-3">
        	<form method="post">
            	<div class="mb-3">
                	<label class="form-label">TITLE</label>
                	<select class="form-select" aria-label="Default select example" id="title" name="title">
                    	<option value="대도시의 사랑법">대도시의 사랑법</option>
                    	<option value="베테랑2">베테랑2</option>
                    	<option value="와일드 로봇">와일드 로봇</option>
                    	<option value="보통의 가족">보통의 가족</option>
                    	<option value="룩백">룩백</option>
                   		<option value="트랜스포머 one">트랜스포머 one</option>
                    	<option value="비긴 어게인">비긴 어게인</option>
                    	<option value="안녕, 할부지">안녕, 할부지</option>
                    	<option value="로봇 드림">로봇 드림</option>
                    	<option value="빈 국립 오페라 : 투란도트">빈 국립 오페라 : 투란도트</option>
                    	<option value="노트북">노트북</option>
                    	<option value="비틀쥬스 비틀쥬스">비틀쥬스 비틀쥬스</option>
                    	<option value="새벽의 모든">새벽의 모든</option>
                    	<option value="원스">원스</option>
                    	<option value="기븐_히이라기 믹스">기븐_히이라기 믹스</option>
                    	<option value="소년시절의 너">소년시절의 너</option>
                    	<option value="에이리언: 로물루스">에이리언: 로물루스</option>
                    	<option value="조커: 폴리 아 되">조커: 폴리 아 되</option>
                    	<option value="사랑의 하츄핑">사랑의 하츄핑</option>
                    	<option value="타인의 삶">타인의 삶</option>
                	</select>
            	</div>
            	<div class="mb-3">
                	<label class="form-label">CINEMA</label>
                	<input type="text" class="form-control" id="cinema" name="cinema" value="ITN" readonly>
            	</div>
            	<div class="mb-3">
                	<label class="form-label">CITY</label>
                	<select class="form-select" aria-label="Default select example" id="city" name="city">
                    	<option value="서울">서울</option>
                    	<option value="인천">인천</option>
                    	<option value="부산">부산</option>
                	</select>
            	</div>
            	<div class="mb-3">
                	<label class="form-label">BRANCH</label>
                	<select class="form-select" aria-label="Default select example" id="branch" name="branch">
                    	<option value="강남">강남</option>
                    	<option value="신촌">신촌</option>
                    	<option value="판교">판교</option>
                    	<option value="송도" style="display: none;">송도</option>
                    	<option value="영종" style="display: none;">영종</option>
                    	<option value="논현" style="display: none;">논현</option>
                    	<option value="시청" style="display: none;">시청</option>
                    	<option value="서면" style="display: none;">서면</option>
                    	<option value="동래" style="display: none;">동래</option>
                	</select>
            	</div>
            	<div class="mb-3">
                	<label class="form-label">THEATER</label>
                	<select class="form-select" aria-label="Default select example" id="theater" name="theater">
                    	<option value="1">1</option>
                    	<option value="2">2</option>
                    	<option value="3">3</option>
                	</select>
            	</div>
            	<div class="mb-3">
                	<label class="form-label">SEATS</label>
                	<input type="text" class="form-control" id="seats" name="seats" value="32" readonly>
            	</div>
            	<div class="mb-3">
                	<label class="form-label">REMAIN_SEATS</label>
                	<input type="text" class="form-control" id="remainSeats" name="remainSeats" value="32" readonly>
            	</div>
            	<div class="mb-3">
                	<label class="form-label">ADDRESS</label>
                	<input type="text" class="form-control" id="address" name="address"
                			value="서울특별시 강남구 강남대로 438 스타플렉스 4층~8층" readonly>
            	</div>
            	<div class="mb-3">
                	<label class="form-label">SHOWING TIME</label>
                	<input type="text" class="form-control datePicker" id="showingTime" name="showingTime" readonly/>
            	</div>
            	<div class="mb-3">
                	<label class="form-label">START TIME</label>
               		<input type="time" class="form-control" id="startTime" name="startTime" value="00:00:00" step="2" required/>
            	</div>
            	<div class="mb-3">
                	<label class="form-label">END TIME</label>
                	<input type="time" class="form-control" id="endTime" name="endTime" value="00:00:00" step="2" required/>
            	</div>
            
        	</form>
    	</div>
    	<div class="bn-area">
        	<button type="button" class="btn btn-danger" id="back-bn">취소</button>
        	<button type="button" class="btn btn-primary" id="submit-bn">등록</button>
    	</div>
   		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.10.0/js/bootstrap-datepicker.min.js"></script>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.10.0/locales/bootstrap-datepicker.ko.min.js"></script>
    	<script> sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}"); </script>
    	<script src="<c:url value='/resources/js/showing/common.js'/>"></script>
    	<script src="<c:url value='/resources/js/showing/datePicker.js'/>"></script>
    	<script src="<c:url value='/resources/js/showing/addition.js'/>"></script>
	</body>
</html>