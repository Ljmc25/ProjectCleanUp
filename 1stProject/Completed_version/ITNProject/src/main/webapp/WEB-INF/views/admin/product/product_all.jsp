<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.truncate {
	white-space: nowrap; /* 한 줄로 설정 */
	overflow: hidden; /* 넘치는 내용 숨김 */
	text-overflow: ellipsis; /* '...' 표시 */
}

.header-container {
	display: flex;
	justify-content: space-between; /* 제목과 버튼을 양쪽 끝으로 배치 */
	align-items: center; /* 세로 정렬 */
	margin-bottom: 20px; /* 아래 여백 추가 */
}

.movie-list-title {
	text-align: center; /* 제목 중앙 정렬 */
	flex-grow: 1; /* 제목이 가능한 공간을 차지하도록 설정 */
	margin-top: 2vh;
}
/* 카드 스타일 수정 */
.card {
	width: 80%; /* 카드 너비를 100%로 설정 */
	height: auto; /* 카드 높이 자동 조정 */
	margin-left: 2vw;
}
/* 영화 목록 콘텐츠 스타일 */
#product-list {
	display: flex;
	flex-wrap: wrap;
	justify-content: center; /* 중앙 정렬 */
}
</style>
</head>

<body>
	<main class="container-fluid"
		style="background-color: rgb(238, 238, 238);">
		<div class="row">
			<!-- 메뉴 탭 -->
			<jsp:include page="/common/menu.jsp" />
			<!-- 영화 목록 콘텐츠 -->
			<div class="col-md-10">
				<div class="col-12 header-container">
					<h2 class="movie-list-title">영화 목록</h2>
					<a href="<c:url value='/product/addition'/>" class="btn btn-success">신규 영화 등록</a>
				</div>
				<div id="loading-indicator" style="display: none;">로딩 중...</div>
					<div id="product-list" class="row">
					    <c:forEach var="product" items="${productList}">
					        <div class="col-md-4 mb-4 movie_list">
					            <div class="card">
					                <%-- <span>제품 ID: ${product.pdid != null ? product.pdid : '정보 없음'}</span> --%>
					                <img src="<c:url value='../${product.imageSmallUrl}'/>" class="card-img-top" alt="영화 포스터" style="width: 100%">
					                <div class="card-body d-flex flex-column" style="flex-grow: 1; justify-content: space-between;">
					                    <h5 class="card-title">${product.title != null ? product.title : '제목 없음'}</h5>
					                    <p class="card-text truncate">${product.contents != null ? product.contents : '내용 없음'}</p>
					                    <div style="display: inline-block;">
					                        <a href="<c:url value='/product/edit/${product.pdid}'/>">
					                            <button type="button" class="btn btn-primary" style="width: 100px;">수정하기</button>
					                        </a>
					                       <form action="<c:url value='/product/delete'/>" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');" style="display: inline;">
											    <input type="hidden" name="pdid" value="${product.pdid}">
											    <button type="submit" class="btn btn-danger" style="width: 5vw;">삭제하기</button>
											</form>
					                    </div>
					                    <button class="btn toggleShowtime inactive" style="margin-top: 1vh;" onclick="toggleShowtime(this);">상영 활성화</button>
					                    <div class="mt-2 showtimeStatus">현재 상영 중이 아닙니다.</div>
					                </div>
					            </div>
					        </div>
					    </c:forEach>
					</div>
				<nav aria-label="Page navigation">
				    <ul class="pagination justify-content-center">
				        <c:if test="${criteria.pageNum2 > 1}">
				            <li class="page-item">
				                <a class="page-link" href="?pageIndex=${criteria.pageNum2 - 1}" aria-label="Previous">
				                    <span aria-hidden="true">&laquo;</span>
				                </a>
				            </li>
				        </c:if>
				        
				        <c:forEach begin="1" end="${criteria.totalPages}" var="i">
				            <li class="page-item <c:if test='${i == criteria.pageNum2}'>active</c:if>'">
				                <a class="page-link" href="?pageIndex=${i}">${i}</a>
				            </li>
				        </c:forEach>
				        
				        <c:if test="${criteria.pageNum2 < criteria.totalPages}">
				            <li class="page-item">
				                <a class="page-link" href="?pageIndex=${criteria.pageNum2 + 1}" aria-label="Next">
				                    <span aria-hidden="true">&raquo;</span>
				                </a>
				            </li>
				        </c:if>
				    </ul>
				</nav>
			</div>
		</div>
	</main>
	<script src="<c:url value='/resources/js/admin/hover.js'/>"></script>
	<script src="<c:url value='/resources/js/admin/screening.js'/>"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
