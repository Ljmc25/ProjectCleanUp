<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<!DOCTYPE html>
<html>
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
			<div class="col-md-10 d-flex align-items-start" style="padding-left: 0; padding-right: 0;">
			    <div style="background-color: white; height: 100%; flex-grow: 1; max-width: 100%; margin-left: 10vw;">
			        <h2 class="text-left mt-4 ms-3 mb-2" style="padding-bottom: 2vh;">공지사항 등록</h2>
			        <form id="notiForm" action="<c:url value='/UpdateNoti'/>" method="post" enctype="multipart/form-data">
			            <input type="hidden" name="noid" value="${noti.noid}">
			            
			            <div class="mb-3 ms-3" style="max-width: 50%;">
			                <label for="notiTitle" class="form-label">공지사항 제목</label>
			                <input type="text" class="form-control" id="notiTitle" name="title" 
			                       placeholder="제목을 입력하세요" value="${noti.title}" required>
			            </div>
			            
			            <div class="mb-3 ms-3" style="max-width: 50%;">
			                <label for="notiContent" class="form-label">공지사항 내용</label>
			                <textarea class="form-control" id="notiContents" name="contents" 
			                          placeholder="내용을 입력하세요" rows="4" required>${noti.contents}</textarea>
			            </div>
			            
			            <div class="mb-3 ms-3" style="max-width: 30%;">
			                <label for="moviePoster" class="form-label">파일 업로드</label>
			                <input class="form-control" type="file" id="fileUrl" name="moviePoster" accept="image/*" 
			                       onchange="previewImage(event)">
			                <!-- 이미지 미리보기 -->
			                <div class="mt-3">
			                    <img id="preview" src="${noti.fileUrl}" alt="미리보기" style="max-width: 100%; 
			                         display: ${noti.fileUrl ? 'block' : 'none'};">
			                </div>
			            </div>
			            
			            <div class="d-flex justify-content-center" style="margin-bottom: 3vh; margin-top: 5vh;">
			                <button type="submit" class="btn btn-success btn-lg me-5">수정</button>
			                <button type="button" class="btn btn-danger btn-lg" onclick="cancelForm()">취소</button>
			            </div>
			        </form>
			    </div>
			</div>
		</div>
	</main>
	<script
		src="${pageContext.request.contextPath}/resources/js/product/hover.js"></script>
	<script type="text/javascript">

		function cancelForm() {
			window.location.href = "<c:url value='/admin/notice' />"; // 공지사항 메인 페이지로 리다이렉트
		}
		  function previewImage(event) {
		        const preview = document.getElementById('preview');
		        const file = event.target.files[0]; // 선택된 파일

		        if (file) {
		            const reader = new FileReader();

		            // 파일이 로드되면 미리보기 이미지 업데이트
		            reader.onload = function(e) {
		                preview.src = e.target.result;
		                preview.style.display = 'block'; // 이미지가 로드되면 보여줌
		            }

		            reader.readAsDataURL(file); // 파일을 읽어 DataURL로 변환
		        } else {
		            // 파일이 없을 경우 기본 이미지 경로로 복구
		            preview.src = "${noti.fileUrl}";
		            preview.style.display = '${noti.fileUrl != null && noti.fileUrl != '' ? 'block' : 'none'}'; // 기존 URL에 따라 표시 여부 결정
		        }
		    }
	</script>
</body>
</html>