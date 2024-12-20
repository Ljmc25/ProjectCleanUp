<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html lang="en">
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
<link rel="stylesheet" href="<c:url value='/resources/css/admin/border.css'/>" />
</head>
<body>
	<main class="container-fluid"
		style="background-color: rgb(238, 238, 238);">
		<div class="row">
			<!-- 메뉴 탭 -->
			<jsp:include page="/common/menu.jsp" />
			<!-- 중앙 콘텐츠 영역 -->
			<div class="col-md-10 d-flex align-items-start"
				style="padding-left: 0; padding-right: 0;">
				<div
					style="background-color: white; height: 100%; flex-grow: 1; max-width: 100%; margin-left: 10vw;">
					<h2 class="text-left mt-4 ms-3" style="padding-bottom: 2vh;">신규 영화 등록</h2>
					<form id="movieForm" method="post" action="<c:url value='/product/add'/>" enctype="multipart/form-data">
						
						<!-- 메인 이미지 업로드 -->
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageMainUrl" class="form-label">메인 이미지 업로드</label>
						    <input class="form-control" type="file" id="imageMainUrl" name="imageMainUrl" accept="image/*"
						           onchange="handleFileSelect(event, 'imagePreview1', 'imageMainUrlPath')" required>
						    <input type="hidden" id="imageMainUrlPath" name="imageMainUrlPath" value="">
						    <img id="imagePreview1" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<!-- 작은 이미지 업로드 -->
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageSmallUrl" class="form-label">작은 이미지 업로드</label>
						    <input class="form-control" type="file" id="imageSmallUrl" name="imageSmallUrl" accept="image/*"
						           onchange="handleFileSelect(event, 'imagePreview2', 'imageSmallUrlPath')" required>
						    <input type="hidden" id="imageSmallUrlPath" name="imageSmallUrlPath" value="">
						    <img id="imagePreview2" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<!-- 배경 이미지 업로드 -->
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageBackgroundUrl" class="form-label">배경 이미지 업로드</label>
						    <input class="form-control" type="file" id="imageBackgroundUrl" name="imageBackgroundUrl" accept="image/*"
						           onchange="handleFileSelect(event, 'imagePreview3', 'imageBackgroundUrlPath')" required>
						    <input type="hidden" id="imageBackgroundUrlPath" name="imageBackgroundUrlPath" value="">
						    <img id="imagePreview3" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<!-- 스틸 이미지 1 업로드 -->
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageThril1Url" class="form-label">스틸 이미지 1 업로드</label>
						    <input class="form-control" type="file" id="imageThril1Url" name="imageThril1Url" accept="image/*"
						           onchange="handleFileSelect(event, 'imagePreview4', 'imageThril1UrlPath')" required>
						    <input type="hidden" id="imageThril1UrlPath" name="imageThril1UrlPath" value="">
						    <img id="imagePreview4" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<!-- 스틸 이미지 2 업로드 -->
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageThril2Url" class="form-label">스틸 이미지 2 업로드</label>
						    <input class="form-control" type="file" id="imageThril2Url" name="imageThril2Url" accept="image/*"
						           onchange="handleFileSelect(event, 'imagePreview5', 'imageThril2UrlPath')" required>
						    <input type="hidden" id="imageThril2UrlPath" name="imageThril2UrlPath" value="">
						    <img id="imagePreview5" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<!-- 스틸 이미지 3 업로드 -->
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="imageThril3Url" class="form-label">스틸 이미지 3 업로드</label>
						    <input class="form-control" type="file" id="imageThril3Url" name="imageThril3Url" accept="image/*"
						           onchange="handleFileSelect(event, 'imagePreview6', 'imageThril3UrlPath')" required>
						    <input type="hidden" id="imageThril3UrlPath" name="imageThril3UrlPath" value="">
						    <img id="imagePreview6" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
						</div>
						
						<!-- 관람 등급 이미지 업로드 -->
						<div class="mb-3 ms-3" style="max-width: 30%;">
						    <label for="rateUrl" class="form-label">관람 등급 이미지 업로드</label>
						    <input class="form-control" type="file" id="rateUrl" name="rateUrl" accept="image/*"
						           onchange="handleFileSelect(event, 'imagePreview7', 'rateUrlPath')" required>
						    <input type="hidden" id="rateUrlPath" name="rateUrlPath" value="">
						    <img id="imagePreview7" alt="Uploaded Image" style="max-width: 100%; margin-top: 10px; display: none;">
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
						    <label for="trailer2Url">트레일러 2 URL:</label>
						    <input type="text" id="trailer2Url" name="trailer2Url" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="title" class="form-label">영화 제목</label>
						    <input type="text" class="form-control" id="title" name="title" placeholder="영화 제목을 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="contents" class="form-label">내용</label>
						    <textarea class="form-control" id="contents" name="contents" placeholder="내용을 입력하세요" required></textarea>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="director" class="form-label">감독</label>
						    <input type="text" class="form-control" id="director" name="director" placeholder="감독을 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="movieCast" class="form-label">배우</label>
						    <input type="text" class="form-control" id="movieCast" name="movieCast" placeholder="배우를 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="genre" class="form-label">장르</label>
						    <input type="text" class="form-control" id="genre" name="genre" placeholder="장르를 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="runningTime" class="form-label">러닝타임 (분)</label>
						    <input type="number" class="form-control" id="runningTime" name="runningTime" placeholder="러닝타임을 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="rate" class="form-label">관람 등급</label>
						    <input type="text" class="form-control" id="rate" name="rate" placeholder="관람 등급을 입력하세요" required>
						</div>
						
						<div class="mb-3 ms-3 mt-4" style="max-width: 50%;">
						    <label for="openingDate" class="form-label">개봉일</label>
						    <input type="date" class="form-control" id="openingDate" name="openingDate" required>
						</div>

						<div class="d-flex justify-content-center"
							style="margin-bottom: 3vh; margin-top: 5vh;">
							<button type="submit" class="btn btn-success btn-lg me-5">저장</button>
							<button type="button" class="btn btn-danger btn-lg"
								onclick="cancelForm()">취소</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
	<script src="<c:url value='/resources/js/admin/hover.js'/>"></script>
	<script type="text/javascript">
		function handleFileSelect(event, previewId, pathId) {
			const file = event.target.files[0];
			const previewElement = document.getElementById(previewId);
			const pathElement = document.getElementById(pathId);

			if (file) {
				// 비디오 파일인 경우
				if (file.type.startsWith('video/')) {
					const videoURL = URL.createObjectURL(file);
					previewElement.src = videoURL;
					previewElement.style.display = 'block';
					pathElement.value = videoURL; // 비디오 URL을 hidden input에 저장
				} else {
					// 이미지 파일인 경우
					const reader = new FileReader();
					reader.onload = function(e) {
						previewElement.src = e.target.result;
						previewElement.style.display = 'block';
						pathElement.value = e.target.result; // 이미지 URL을 hidden input에 저장
					};
					reader.readAsDataURL(file);
				}
			} else {
				// 파일이 없을 경우 미리보기 및 hidden input을 초기화
				previewElement.src = '';
				previewElement.style.display = 'none';
				pathElement.value = '';
			}
		}
		function cancelForm() {
			window.location.href = "<c:url value='/product/list' />"; // 공지사항 메인 페이지로 리다이렉트
		}
	</script>
</body>
</html>
