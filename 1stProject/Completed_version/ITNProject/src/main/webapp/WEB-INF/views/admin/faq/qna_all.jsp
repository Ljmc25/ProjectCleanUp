<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  <!-- 여기 남김 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

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
<style>
  .action-column {
    width: 80px; /* 열 너비 고정 */
    text-align: center;
  }
  .btn-custom {
    width: 60px; /* 버튼 너비 고정 */
    padding: 6px 0; /* 상하 패딩만 적용 */
    font-size: 0.9rem;
    white-space: nowrap;
  }
  .btn-container {
    display: flex;
    justify-content: center;
  }
  .padding_style{
	padding-top: 10px; /* 원하는 값으로 조정 */
    vertical-align: middle; /* 중앙 정렬 */
}

</style>
</head>
<body>
	<main class="container-fluid"
		style="background-color: rgb(238, 238, 238);">
		<div class="row">
			<!-- 메뉴 탭 -->
			<jsp:include page="/common/menu.jsp" />
			<div class="container mt-5" style="width: 70%">
					<h2 class="mb-4">자주 묻는 질문</h2>
					<a href="<c:url value='/qna/addition'/>">
						<button type="button" id="addGenre"
							class="btn btn-success mt-2 mb-3" style="float: right;">등록</button>
					</a>
					<table class="table table-bordered" style="background-color: white;">
					  <thead>
					    <tr>
					      <th scope="col">번호</th>
					      <th scope="col">제목</th>
					      <th scope="col">조회수</th>
					      <th scope="col" class="action-column">수정</th>
					      <th scope="col" class="action-column">삭제</th>
					    </tr>
					  </thead>
					  <tbody>
					    <c:forEach var="data" items="${qnas}" varStatus="status">
					      <tr>
					        <th scope="row" class="padding_style">${status.index + 1}</th>
					        <td class="padding_style"><a href="<c:url value='/faqDetail?faid=${data.faid}'/>" style="color: black">${data.title}</a></td>
					         <td class="padding_style">
							      <%
							         // 1에서 1000 사이의 랜덤 숫자 생성
							         int randomNumber = (int)(Math.random() * 1000) + 1; // 새로운 랜덤 숫자 생성
							      %>
							      <%= randomNumber %> <!-- 랜덤 숫자를 표시 -->
							</td>
					        <td class="action-column">
					          <div class="btn-container" style="margin-top: 1vh">
					            <a href="<c:url value='/qna/edition/${data.faid}'/>" class="btn btn-primary btn-custom">수정</a>
					          </div>
					        </td>
					        <td class="action-column padding_style">
					          <div class="btn-container"  style="margin-top: 1vh">
					            <form action="<c:url value='/qna/delete'/>" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
					              <input type="hidden" name="faid" value="${data.faid}">
					              <button type="submit" class="btn btn-danger btn-custom">삭제</button>
					            </form>
					          </div>
					        </td>
					      </tr>
					    </c:forEach>
					  </tbody>
					</table>
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
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
		
		<script type="text/javascript">
		  function fn_egov_link_page(pageIndex) {
		        document.getElementById('pageIndex').value = pageIndex; // 선택한 페이지 인덱스를 hidden 필드에 설정
		        document.forms[0].submit(); // 폼 제출
		    }
		</script>
</body>
</html>
