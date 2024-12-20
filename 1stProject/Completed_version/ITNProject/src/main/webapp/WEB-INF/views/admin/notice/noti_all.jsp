<%@page import="com.itn.projectb.vo.advanced.NoticeVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<style>
.padding_style{
	padding-top: 10px; /* 원하는 값으로 조정 */
    vertical-align: middle; /* 중앙 정렬 */
}
.btn-custom {
    width: 100%; /* 버튼이 부모 요소의 너비에 맞게 조정 */
    padding: 10px; /* 패딩 조정 (원하는 크기로 변경 가능) */
    font-size: 16px; /* 글자 크기 조정 */
}

</style>
<body>
	<main class="container-fluid"
		style="background-color: rgb(238, 238, 238);">
		<div class="row">
			<!-- 메뉴 탭 -->
			<jsp:include page="/common/menu.jsp" />
			<div class="container mt-5">
				<h2 class="mb-4">공지사항</h2>
				<a href="<c:url value='/noti/addition'/>">
					<button type="button" id="addGenre"
						class="btn btn-success mt-2 mb-3" style="float: right;">등록</button>
				</a>
				<table class="table table-bordered" style="background-color: white;">
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">작성일</th>
							<th scope="col" style="width: 1vw;">수정</th>
							<th scope="col" style="width: 1vw;">삭제</th>
							<th scope="col">조회수</th>
						</tr>
					</thead>
					<tbody>
				           <c:forEach var="noti" items="${notis}" varStatus="status">
							    <tr>
							        <th scope="row" class="padding_style">${status.index + 1}</th>  <!-- 최신 글이 1번이 되도록 계산 -->
							        <td class="padding_style"><a href="<c:url value='/noticeDetail?noid=${noti.noid}'/>" style="color: black">${noti.title}</a></td>
							        <td class="padding_style">${noti.writer}</td>
							        <td class="padding_style">${noti.insertTime}</td>
									<td>
									        <a href="<c:url value='/editNoti/${noti.noid}'/>"> 
									            <button type="button" class="btn btn-primary btn-custom" onclick="editForm()" style="width: 5vw">수정하기</button>
									        </a>
									</td>
									<td>
									        <form action="<c:url value='/noti/deleteNoti'/>" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
									            <input type="hidden" name="noid" value="${noti.noid}">
									            <button type="submit" class="btn btn-danger btn-custom" style="width: 5vw;">삭제</button> <!-- 버튼 크기 조정 -->
									        </form>
									</td>
							        <td class="padding_style">
							            <%
							                // 1에서 1000 사이의 랜덤 숫자 생성
							                int randomNumber = (int)(Math.random() * 1000) + 1; // 새로운 랜덤 숫자 생성
							            %>
							            <%= randomNumber %> <!-- 랜덤 숫자를 표시 -->
							        </td>
							    </tr>
							</c:forEach>
					</tbody>
				</table>

				<button id="loadMoreButton" class="btn btn-primary btn-lg"
					style="display: none;">더보기</button>
			</div>
		</div>
	</main>
	<script src="<c:url value='/resources/js/admin/hover.js'/>"></script>
		<script type="text/javascript">
		    let currentPage = 1; // 현재 페이지 번호
		    let totalPages = ${totalPages}; // 전체 페이지 수
		    const pageSize = ${searchVO.pageSize}; // 페이지당 항목 수
		
		    function loadMore() {
		    	currentPage += 1;
		
		        if (currentPage > totalPages) {
		            alert("더 이상 데이터를 불러올 수 없습니다.");
		            $('#loadMoreButton').hide(); // 더보기 버튼 숨기기
		            return;
		        }
		
		        $.ajax({
		            url: "/noti", // 공지사항 목록을 가져오는 API 엔드포인트
		            type: "GET",
		            data: { pageIndex: currentPage }, // 현재 페이지 번호 전달
		            success: function(data) {
		                // 받아온 데이터를 이용해 tbody에 추가
		                $('#notiTableBody').append($(data).find('#notiTableBody').html());
		
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
		        if ($('#notiTableBody tr').length >= pageSize && currentPage < totalPages) {
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