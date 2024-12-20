<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<!DOCTYPE html>
<html lang="en">
<head>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ITN MOVIE</title>
<!-- jQuery CDN 추가 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Bootstrap CDN 추가 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value='/resources/css/admin/managr.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/admin/style.css'/>" />
</head>
<body>
    <main class="container-fluid" style="background-color: rgb(238, 238, 238);">
        <div class="row">
             <!-- 메뉴 탭 -->
 			<jsp:include page="/common/menu.jsp" />
            <div class="col-md-10 d-flex align-items-start" style="padding-left: 0; padding-right: 0;">
                <div style="background-color: white; height: 100%; flex-grow: 1; max-width: 100%; margin-left: 10vw;">
					<h2 class="text-left mt-4 ms-3" style="padding-bottom: 2vh;">영화 수정</h2>
					<form id="movieForm" method="post" action="<c:url value='/product/update'/>" enctype="multipart/form-data">
				    <input type="hidden" id="pdid" name="pdid" value="${product.pdid}">
				
				    
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageMainFile" class="form-label">메인 이미지 업로드</label>
						    <input class="form-control" type="file" id="imageMainFile" name="imageMainFile" accept="image/*" onchange="handleFileSelect(event, 'IMAGE_MAIN_URL', 'imagePreview1')">
						    <img id="imagePreview1" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageSmallFile" class="form-label">작은 이미지 업로드</label>
						    <input class="form-control" type="file" id="imageSmallFile" name="imageSmallFile" accept="image/*" onchange="handleFileSelect(event, 'IMAGE_SMALL_URL', 'imagePreview2')">
						    <img id="imagePreview2" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageBackgroundFile" class="form-label">배경 이미지 업로드</label>
						    <input class="form-control" type="file" id="imageBackgroundFile" name="imageBackgroundFile" accept="image/*" onchange="handleFileSelect(event, 'IMAGE_BACKGROUND_URL', 'imagePreview3')">
						    <img id="imagePreview3" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageThril1File" class="form-label">스틸 이미지 1 업로드</label>
						    <input class="form-control" type="file" id="imageThril1File" name="imageThril1File" accept="image/*" onchange="handleFileSelect(event, 'IMAGE_THRIL1_URL', 'imagePreview4')">
						    <img id="imagePreview4" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageThril2File" class="form-label">스틸 이미지 2 업로드</label>
						    <input class="form-control" type="file" id="imageThril2File" name="imageThril2File" accept="image/*" onchange="handleFileSelect(event, 'IMAGE_THRIL2_URL', 'imagePreview5')">
						    <img id="imagePreview5" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageThril3File" class="form-label">스틸 이미지 3 업로드</label>
						    <input class="form-control" type="file" id="imageThril3File" name="imageThril3File" accept="image/*" onchange="handleFileSelect(event, 'IMAGE_THRIL3_URL', 'imagePreview6')">
						    <img id="imagePreview6" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="rateFile" class="form-label">관람 등급 이미지 업로드</label>
						    <input class="form-control" type="file" id="rateFile" name="rateFile" accept="image/*" onchange="handleFileSelect(event, 'RATE_URL', 'ratePreview')">
						    <img id="ratePreview" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<div class="mb-3 ms-3" style="max-width: 30%;">
						   	<label for="trailerMainUrl">트레일러 메인 URL:</label>
						    <input type="text" id="trailerMainUrl" name="trailerMainUrl" required>
						</div>
						
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="trailer1Url">트레일러 1 URL:</label>
						    <input type="text" id="trailer1Url" name="trailer1Url" required>
						</div>
						
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="trailer1Url">트레일러 1 URL:</label>
						    <input type="text" id="trailer1Url" name="trailer1Url" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="title" class="form-label">영화 제목</label>
						    <input type="text" class="form-control" id="title" name="title" value="${product.title}" placeholder="영화 제목을 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="contents" class="form-label">내용</label>
						    <input type="text" class="form-control" id="contents" name="contents" value="${product.contents}" placeholder="내용을 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="director" class="form-label">감독</label>
						    <input type="text" class="form-control" id="director" name="director" value="${product.director}" placeholder="감독을 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="movieCast" class="form-label">배우</label>
						    <input type="text" class="form-control" id="movieCast" name="movieCast" value="${product.movieCast}" placeholder="배우를 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="genre" class="form-label">장르</label>
						    <input type="text" class="form-control" id="genre" name="genre" value="${product.genre}" placeholder="장르를 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="openingDate" class="form-label">개봉일</label>
						    <input type="date" class="form-control" id="openingDate" name="openingDate" value="${product.openingDate}" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="runningTime" class="form-label">상영 시간</label>
						    <input type="text" class="form-control" id="runningTime" name="runningTime" value="${product.runningTime}" placeholder="상영 시간을 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="rate" class="form-label">관람 등급</label>
						    <input type="text" class="form-control" id="rate" name="rate" value="${product.rate}" placeholder="관람 등급을 입력하세요" required>
						</div>

					    <div class="text-center">
					        <button type="submit" class="btn btn-primary">수정</button>
					        <a class="btn btn-secondary" href="<c:url value='/admin/product/list'/>">목록으로 돌아가기</a>
					    </div>
					</form>
                </div>
            </div>
        </div> 
 </main>
	<script src="<c:url value='/resources/js/admin/hover.js'/>"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    	    <script type="text/javascript">
    	    function cancelForm() {
    			window.location.href = "<c:url value='/product/list' />"; // 공지사항 메인 페이지로 리다이렉트
    		}
    	    
    	    function handleFileSelect(event, urlField, previewId) {
    	        const file = event.target.files[0]; // 선택된 파일
    	        const previewElement = document.getElementById(previewId); // 미리보기 요소

    	        if (file) {
    	            const reader = new FileReader(); // FileReader 객체 생성

    	            // 파일이 로드되면
    	            reader.onload = function (e) {
    	                previewElement.src = e.target.result; // 미리보기 요소의 src 속성에 파일 URL 설정
    	                previewElement.style.display = 'block'; // 미리보기 요소 보이기

    	                // 선택된 파일의 URL을 특정 필드에 저장하는 경우
    	                document.getElementById(urlField).value = e.target.result; // URL 필드에 파일 URL 저장
    	            };

    	            reader.readAsDataURL(file); // 파일을 데이터 URL로 읽기
    	        } else {
    	            previewElement.style.display = 'none'; // 파일이 선택되지 않은 경우 미리보기 숨기기
    	        }
    	    }

    </script>
</body>
</html>
