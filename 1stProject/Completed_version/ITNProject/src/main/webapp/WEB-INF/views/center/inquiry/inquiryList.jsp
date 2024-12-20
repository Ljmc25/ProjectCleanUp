<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ITN MOVIE</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/style.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/header.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/footer.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/center/inquiry/inquiryList.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header>
        <jsp:include page="/common/header.jsp" />
    </header>
    <main>
        <div class="container-fluid" style="margin-bottom: 300px; margin-left: 30px">
            <div class="row">
           	 	<!-- 사이드바 -->
            	<jsp:include page="/common/sidebar.jsp"/>
                <!-- 게시판 영역 -->
                <div class="col-lg-9 col-md-8 board" style="width: 1000px; margin-top: 170px;">
                    <i class="fa fa-comments fa-3x">1:1 문의</i>
                    <form id="listForm" name="listForm" method="get">
                        <div class="search-bar">
                            <input type="text" name="searchKeyword" class="form-control d-inline" placeholder="검색어를 입력해주세요..." value="${searchVO.searchKeyword}">
                            <button type="button" class="btn btn-dark" onclick="fn_egov_selectList()">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                        <input type="hidden" id="inid" name="inid">
                        <table class="table board-table">
                            <thead>
                                <tr>
                                    <th><i class="fa fa-list" style="color: green;"></i></th>
                                    <th><i class="fa fa-book" style="color: brown;"></i></th>
                                    <th><i class="fa fa-user" style="color: blue;"></i></th>
                                    <th><i class="fa fa-calendar" style="color: red;"></i></th>
                                    <th><i class="fa fa-cog" style="color: gray;"></i></th>
                                    <th><i class="fa fa-paper-plane" style="color: skyblue;"></i></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="data" items="${inquiryList}" varStatus="status">
                                    <tr>
                                        <td>
                                            <c:out value="${paginationInfo.totalRecordCount - ((searchVO.pageIndex - 1) * searchVO.pageSize + status.index)}"/>
                                        </td>
                                        <td>
                                            <a class="ellipsis-text" href="javascript:void(0);" onclick="detailPage('${data.inid}'); return false">
                                            	<c:choose>
        											<c:when test="${fn:length(data.contents) gt 16}">
        												<c:out value="${fn:substring(data.contents, 0, 15)}"/>...
        											</c:when>
        											<c:otherwise>
														<c:out value="${data.contents}"/>
        											</c:otherwise>
												</c:choose>
                                            </a>
                                        </td>
                                        <td>
                                            <c:out value="${data.writer}" />
                                        </td>
                                        <td>
                                            <c:out value="${data.insertTime}" />
                                        </td>
                                        <td class="action-btns">
                                            <c:if test="${sessionScope.memberVO.email == data.writer}">
                                                <a class="btn btn-sm btn-custom" style="background-color: lightgray;" 
                                                href="javascript:void(0);" onclick="UpdatePage('${data.inid}'); return false">수정</a>
                                                <button type="button" class="btn btn-sm btn-danger" onclick="fn_delete('${data.inid}');">삭제</button>
                                            </c:if>
                                            <c:if test="${sessionScope.memberVO.email != data.writer}">
                                                <span>&nbsp;</span>
                                            </c:if>
                                        </td>
                                        <td>
                							<c:set var="isAnswered" value="false" />
    										<c:forEach var="reply" items="${inquiryAnswerList}">
        										<c:if test="${reply.inid == data.inid}">
            										<c:set var="isAnswered" value="true" />
        										</c:if>
    										</c:forEach>
    
											<c:if test="${isAnswered}">
    											<span class="answer-complete">
       			 									<i class="fa fa-check-circle" style="color: #28a745;"></i> 답변완료
    											</span>
											</c:if>
 											<c:if test="${!isAnswered}">
    											<i class="fa fa-exclamation-circle" style="color: red;"></i> 답변준비
											</c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- 전자정부 -->
                        <div id="paging" class="text-center">
                            <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
                            <input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}" />
                        </div>

                        <div class="mb-3">
                            <input type="hidden" class="form-control" id="writer" name="writer" value="${sessionScope.memberVO.email}" readonly>
                        </div>
                    </form>

                    <!-- 글쓰기 버튼 -->
                    <button class="btn btn-write" data-bs-toggle="modal" data-bs-target="#writeModal" style="background-color: black; width: 8%;">
                        <i class="fa fa-pencil"></i>
                    </button>
                </div>
            </div>

            <!-- 글쓰기 모달 -->
            <div class="modal fade" id="writeModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">글쓰기</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="writeForm" name="writeForm" method="post">
                                <div class="mb-3">
                                    <label for="writeTitle" class="col-form-label">제목:</label>
                                    <input type="text" class="form-control" id="writeTitle" name="title" />
                                </div>
                                <div class="mb-3">
                                    <label for="writeContent" class="col-form-label">내용:</label>
                                    <textarea class="form-control" id="writeContent" name="contents" style="height: 150px;"></textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                                    <button type="button" class="btn btn-primary" onclick="submitWrite()">작성</button>
                                </div>
                            </form>
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
        sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
    </script>
    <script src="<c:url value='/resources/js/common/common.js'/>"></script>
    <script src="<c:url value='/resources/js/common/header.js'/>"></script>
    <script src="<c:url value='/resources/js/center/common/common.js'/>"></script>
    <script src="<c:url value='/resources/js/center/inquiry/inquiryList.js'/>"></script>
</body>
</html>