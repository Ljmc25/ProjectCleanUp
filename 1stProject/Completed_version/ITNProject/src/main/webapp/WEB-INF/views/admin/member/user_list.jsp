<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
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
<style>
    #loadMoreButton {
        background-color: #007bff; /* 버튼 배경색 */
        color: white;              /* 글자색 */
        border: none;              /* 테두리 없음 */
        border-radius: 5px;       /* 모서리 둥글게 */
        padding: 10px 20px;       /* 여백 */
        font-size: 16px;          /* 글자 크기 */
        cursor: pointer;           /* 커서 모양 변경 */
        transition: background-color 0.3s; /* 배경색 변경 시 애니메이션 */
    }

    #loadMoreButton:hover {
        background-color: #0056b3; /* 호버 시 배경색 */
    }

    #loadMoreButton:focus {
        outline: none; /* 포커스 시 아웃라인 제거 */
    }
</style>
</head>
<body>
	<main class="container-fluid"
		style="background-color: rgb(238, 238, 238);">
		<div class="row">
			<!-- 메뉴 탭 -->
			<jsp:include page="/common/menu.jsp" />
			<!-- 회원 리스트 -->
			<div class="container mt-5" style="width: 65%">
				<h2 class="mb-4">회원 리스트</h2>
				<table class="table table-bordered" style="background-color: white;">
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">아이디</th>
							<th scope="col">전화번호</th>
							<th scope="col" style="width: 1vw;">등급</th>
							<th scope="col">가입일</th>

						</tr>
					</thead>
					<tbody id="userTableBody">
						<c:forEach var="user" items="${users}" varStatus="status">
				            <tr>
				                <th scope="row">${status.index + 1}</th> <!-- 번호 계산 -->
				                <td>${user.email}</td> <!-- 아이디 -->
				                <td>${user.phone}</td> <!-- 전화번호 -->
				                <td>${user.grade}</td> <!-- 회원등급 -->
				                <td>${user.insertTime}</td> <!-- 가입일 -->
				            </tr>
				        </c:forEach>
					</tbody>
				</table>
				<button id="loadMoreButton" class="btn btn-primary btn-lg" style="display: none;">더보기</button>
			</div>
		</div>
	</main>
	<script src="<c:url value='/resources/js/admin/hover.js'/>"></script>
	<script src="<c:url value='/resources/js/admin/screening.js'/>"></script>
	<script type="text/javascript">
    let currentPage = 1; // 현재 페이지 번호
    let totalPages = ${totalPages}; // 전체 페이지 수
    const pageSize = ${searchVO.pageSize}; // 페이지당 항목 수
    let totalItems = ${totalItems}; // 전체 등록된 항목 수

    function loadMore() {
        currentPage += 1;

        if (currentPage > totalPages) {
            alert("더 이상 데이터를 불러올 수 없습니다.");
            $('#loadMoreButton').hide(); // 더보기 버튼 숨기기
            return;
        }

        $.ajax({
            url: "/user/user_list",
            type: "GET",
            data: { pageIndex: currentPage },
            success: function(data) {
                // 테이블에 더 많은 데이터를 추가
                $('#userTableBody').append($(data).find('#userTableBody').html());

                // 데이터가 더 이상 없으면 더보기 버튼 숨기기
                if (currentPage >= totalPages) {
                    $('#loadMoreButton').hide();
                }
            },
            error: function() {
                alert("데이터를 불러오는 중 오류가 발생했습니다.");
            }
        });
    }

    // 처음 페이지 로드 시 더보기 버튼 활성화 여부 결정
    $(document).ready(function() {
        if ($('#userTableBody tr').length >= pageSize && currentPage < totalPages) {
            $('#loadMoreButton').show();
        }

        // 버튼 클릭 이벤트 리스너 추가
        $('#loadMoreButton').on('click', function() {
            loadMore(); // loadMore 함수 호출
        });
    });
</script>
</body>
</html>