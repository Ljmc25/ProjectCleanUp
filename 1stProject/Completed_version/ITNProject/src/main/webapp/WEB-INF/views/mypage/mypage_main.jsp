<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ITN MOVIE</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/style.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/header.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/footer.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header>
        <jsp:include page="/common/header.jsp" />
    </header>
    <main>
        <!-- body background color -->
        <div style="background-color: white">
            <!-- Main Content -->
            <div class="d-flex justify-content-center" style="height: 100vh; padding-top: 50px">
                <div class="row" style="width: 80%">
                    <!-- Sidebar Navigation -->
                    <div class="col-2">
                        <div class="list-group" id="list-tab" role="tablist">
                            <a class="list-group-item list-group-item-action" style="
        						background-color: rgb(207, 226, 255); color: black;
        						border: 3px solid rgb(207, 226, 255); height: 60px;
        						font-size: 25px;" data-bs-toggle="list" 
        						href="#list-mypagehome" role="tab">마이페이지</a>
                            <a class="list-group-item list-group-item-action active" data-bs-toggle="list"
                                href="#list-reservation" role="tab">예매/구매 내역</a>
                            <a class="list-group-item list-group-item-action" data-bs-toggle="list" href="#list-ticket"
                                role="tab">영화 관람권</a>
                            <a class="list-group-item list-group-item-action" data-bs-toggle="list" href="#list-qna"
                                role="tab">문의 내역</a>
                            <a class="list-group-item list-group-item-action" data-bs-toggle="list"
                                href="#list-information" role="tab">회원정보</a>
                        </div>
                    </div>
                    <!-- Tab Content -->
                    <div class="col-8">
                        <div class="tab-content" id="nav-tabContent" style="
                border: 1px solid gray;
                border-radius: 5px;
                margin-top: 10px;
                padding: 20px;
                background-color: white;
              ">
                            <!-- Home Tab -->
                            <div class="tab-pane fade" id="list-mypagehome" role="tabpanel"
                                style="font-size: 50px; color: black">
                                환영합니다 회원님!
                            </div>

                            <!-- Reservation Tab -->
                            <div class="tab-pane fade show active" id="list-reservation" role="tabpanel">
                                <p style="font-size: 30px; color: black">예매/구매 내역</p>
                                <nav>
                                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                        <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab"
                                            data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home"
                                            aria-selected="true" style="font-weight: bold">
                                            예매
                                        </button>
                                        <button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab"
                                            data-bs-target="#nav-profile" type="button" role="tab"
                                            aria-controls="nav-profile" aria-selected="false" style="font-weight: bold">
                                            구매
                                        </button>
                                    </div>
                                </nav>
                                <div class="tab-content" id="nav-tabContent">
                                    <!-- 예매 내역 -->
                                    <div class="tab-pane fade show active" id="nav-home" role="tabpanel"
                                        aria-labelledby="nav-home-tab" tabindex="0">

                                        <form id="bookForm" name="bookForm">
                                            <input type="hidden" name="boid" />
                                            <input type="hidden" name="email" />


                                            <table class="table table-striped" style="background-color: white">
                                                <thead>
                                                    <tr class="table-primary ">
                                                        <!--                         book table -->
                                                        <th scope="col">예매일</th>
                                                        <th scope="col">예매번호</th>
                                                        <th scope="col">영화</th>
                                                        <th scope="col">좌석</th>
                                                        <!--                         pay table -->
                                                        <th scope="col">가격</th>
                                                        <th scope="col">예매취소</th>
                                                        <th scope="col">환불일시</th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                	<c:if test="${empty BookList}">
                                                		<tr>
                                           	 				<td></td>
                                                    		<td></td>
                                                    		<td></td>
                                                    		<td></td>
                                                    		<td></td>
                                                    		<td></td>
                                                    		<td></td>
                                                		</tr>
                                                	</c:if>
                                                	<c:forEach var="data" items="${BookList}">
                                                    	<tr>                                            
                                                            <th id="insertTime" scope="row">
                                                                <c:out value="${data.insertTime}" />
                                                            </th>
                                                            <td id="bookNum">
                                                                <c:out value="${data.bookNum}" />
                                                            </td>
                                                            <td id="title">
                                                                <c:out value="${data.title}" />
                                                            </td>
                                                            <td id="selectSeat">
                                                                <c:out value="${data.selectSeat}" />
                                                            </td>
                                                            <td id="totalPrice">
                                                                <c:out value="${data.totalprice}" />
                                                            </td>
                                                            <td>
                                                            	<c:choose>
                                                            		<c:when test="${empty data.updateTime}">
                                                            			<button type="button" class="btn btn-danger btn-sm cancel-button"
                                                                    	onclick="fn_saveBook('${data.boid}')">예매취소</button>
                                                            		</c:when>
                                                            		<c:otherwise>
                                                            			<p style="color:red; font-weight: blod;">취소 완료</p>
                                                            		</c:otherwise>
                                                            	</c:choose>
                                                            </td>
                                                            <td id="updateTime">
                                                                <c:out value="${data.updateTime}" />
                                                            </td>
                                                        	</tr>
                                                        </c:forEach>                                                        
                                                </tbody>
                                            </table>
                                           	<div id="paging" class="text-center">
                    						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
                    						<input type="hidden" id="pageIndex" name="pageIndex" />
                							</div>
                                        </form>

                                        <br />

                                        <!-- Accordion -->
                                        <div class="accordion accordion-flush" id="accordionFlushExample">
                                            <div class="accordion-item">
                                                <h2 class="accordion-header">
                                                    <button class="accordion-button collapsed" type="button"
                                                        data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo"
                                                        style="background-color: rgb(207, 226, 255)">
                                                        이용안내
                                                    </button>
                                                </h2>
                                                <div id="flush-collapseTwo" class="accordion-collapse collapse show">
                                                    <div class="accordion-body">
                                                        <ul>
                                                            <p style="font-weight: bold">[예매 안내]</p>
                                                            <li>
                                                                만 4세(48개월) 이상부터는 영화티켓을 반드시
                                                                구매하셔야 입장 가능합니다.
                                                            </li>
                                                            <li>
                                                                예매 변경은 불가능하며, 취소 후 재 예매를
                                                                하셔야만 합니다.
                                                            </li>
                                                            <li>
                                                                ITN 모바일앱을 이용할 경우 티켓출력없이
                                                                모바일티켓을 통해 바로 입장하실 수 있습니다.
                                                            </li>
                                                            <br />
                                                            <p style="font-weight: bold">[예매취소 안내]</p>
                                                            <li>
                                                                온라인(홈페이지/모바일) 예매 취소는 상영시간
                                                                20분전까지 입니다.
                                                            </li>
                                                            <li>
                                                                위탁 예매 사이트 이용 시 취소 및 환불 규정은
                                                                해당 사이트 규정을 따릅니다.
                                                            </li>
                                                            <li>
                                                                공연 관람시 시작 시간 이후에는 입장이 제한
                                                                됩니다.
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 구매 내역 -->
                                    <div class="tab-pane fade" id="nav-profile" role="tabpanel"
                                        aria-labelledby="nav-profile-tab" tabindex="0">
                                        <table class="table table-striped" style="background-color: white">
                                            <br />
                                            <thead>
                                                <tr class="table-primary">
                                                    <th scope="col">걸제일시</th>
                                                    <th scope="col">비고</th>
                                                    <th scope="col">상품명</th>
                                                    <th scope="col">결제금액</th>
                                                    <th scope="col">환불</th>
                                                    <th scope="col">상태</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row"></th>
                                                    <td></td>
                                                    <td>구매 내역이 없습니다.</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                            </tbody>
                                        </table>

                                        <br />
                                        <!-- Accordion -->
                                        <div class="accordion accordion-flush" id="accordionFlushExample">
                                            <div class="accordion-item">
                                                <h2 class="accordion-header">
                                                    <button class="accordion-button collapsed" type="button"
                                                        data-bs-toggle="collapse" data-bs-target="#flush-collapseThree"
                                                        style="background-color: rgb(207, 226, 255)">
                                                        이용안내
                                                    </button>
                                                </h2>
                                                <div id="flush-collapseThree" class="accordion-collapse collapse show">
                                                    <div class="accordion-body">
                                                        <ul>
                                                            <p style="font-weight: bold">
                                                                [스토어 구매/취소 안내]
                                                            </p>
                                                            <li>
                                                                스토어 상품은 구매 후 취소가능기간 내 100%
                                                                환불이 가능하며, 부분환불은 불가 합니다.
                                                            </li>
                                                            <li>
                                                                (ex. 3개의 쿠폰을 한 번에 구매하신 경우, 3개
                                                                모두 취소만 가능하며 그 중 사용하신 쿠폰이 있는
                                                                경우 환불이 불가합니다)
                                                            </li>
                                                            <li>
                                                                스토어 교환권은 MMS로 최대 1회 재전송 하실 수
                                                                있습니다.
                                                            </li>
                                                            <br />
                                                            <p style="font-weight: bold">
                                                                [모바일오더 구매/취소 안내]
                                                            </p>
                                                            <li>
                                                                관람권으로 예매 시 차액은 환급되지 않습니다.
                                                            </li>
                                                            <li>관람권별 사용정책이 다를 수 있습니다.</li>
                                                            <li>
                                                                관람권 사용 시 멤버십 포인트가 적립됩니다.
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Movie Ticket Tab -->
                            <div class="tab-pane fade" id="list-ticket" role="tabpanel">
                                <p style="font-size: 30px; color: black">영화 관람권</p>
                                <table class="table" style="background-color: white">
                                    <thead>
                                        <tr class="table-primary">
                                            <th scope="col">관람권명</th>
                                            <th scope="col">유효기간</th>
                                            <th scope="col">사용상태</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td colspan="3">조회된 관람권이 없습니다.</td>
                                        </tr>
                                    </tbody>
                                </table>

                                <!-- Accordion -->
                                <div class="accordion accordion-flush" id="accordionFlushExample">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header">
                                            <button class="accordion-button collapsed" type="button"
                                                data-bs-toggle="collapse" data-bs-target="#flush-collapseOne"
                                                style="background-color: rgb(207, 226, 255)">
                                                이용안내
                                            </button>
                                        </h2>
                                        <div id="flush-collapseOne" class="accordion-collapse collapse show">
                                            <div class="accordion-body">
                                                <ul>
                                                    <li>
                                                        등록된 관람권은 홈페이지/모바일 예매 시 사용
                                                        가능하며, 현장 사용은 불가합니다.
                                                    </li>
                                                    <li>
                                                        예매 후 취소 시 유효기간 내 재사용 가능합니다.
                                                    </li>
                                                    <li>유효기간 만료 시 사용이 불가합니다.</li>
                                                    <li>관람권으로 예매 시 차액은 환급되지 않습니다.</li>
                                                    <li>관람권별 사용정책이 다를 수 있습니다.</li>
                                                    <li>관람권 사용 시 멤버십 포인트가 적립됩니다.</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- QnA Tab -->
                            <div class="tab-pane fade" id="list-qna" role="tabpanel">
                                <p style="font-size: 30px; color: black">문의 내역</p>
                                <p><a href="<c:url value='/inquiryList'/>"><button class="btn btn-info">1:1 문의 바로가기</button></a></p>
                            </div>

                            <!-- Member Info Tab -->
                            <div class="tab-pane fade" id="list-information" role="tabpanel">
                                <p style="font-size: 30px; color: black">회원정보</p>
                                <div id="info-section" style="display: block;"> <!-- Change display to block -->
                                    <p></p>
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">정보수정</th>
                                                <th scope="col">email</th>
                                                <th scope="col">password</th>
                                                <th scope="col">phone</th>
                                                <th scope="col">grade</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                                <tr>
                                                    <td>
                                                        <a>
                                                        <!-- Button trigger modal -->
                                                        <button type="button" class="btn btn-info btn-sm"
                                                            data-bs-toggle="modal" data-bs-target="#exampleModal">
                                                            수정하기
                                                        </button>
                                                        <!-- Modal -->
                                                        <div class="modal fade" id="exampleModal"
                                                            data-bs-backdrop="static" tabindex="-1"
                                                            aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h1 class="modal-title fs-5"
                                                                            id="exampleModalLabel">회원 정보 수정하기</h1>
                                                                        <button type="button" class="btn-close"
                                                                            data-bs-dismiss="modal"
                                                                            aria-label="Close"></button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <div class="container">
                                                                            <form id="memberForm" name="memberForm"
                                                                                method="post">
                                                                                <div class="mb-3">
                                                                                    <label for="email"
                                                                                        class="form-label">이메일 :
                                                                                    </label>
                                                                                    <input class="form-control"
                                                                                        id="email" name="email"
                                                                                        value="<c:out value='${MemberVO.email}' />"
                                                                                        placeholder="${MemberVO.email}" readonly="readonly"/>
                                                                                </div>

                                                                                <div class="mb-3">
                                                                                    <label for="password"
                                                                                        class="form-label">비밀번호 :
                                                                                    </label>
                                                                                    <input type="text"
                                                                                        class="form-control"
                                                                                        id="password" name="password"
                                                                                        placeholder="Enter new password" />
                                                                                </div>

                                                                                <div class="mb-3">
                                                                                    <label for="phone"
                                                                                        class="form-label">전화번호 :
                                                                                    </label>
                                                                                    <input class="form-control"
                                                                                        id="phone" name="phone"
                                                                                        value="<c:out value='${MemberVO.phone}' />"
                                                                                        placeholder="${MemberVO.phone}" />
                                                                                </div>

                                                                                <div class="mb-3">
                                                                                    <label for="grade"
                                                                                        class="form-label">등급 :
                                                                                        ${MemberVO.grade}</label>
                                                                                    <input class="form-control"
                                                                                        id="grade" name="grade"
                                                                                        value="<c:out value='${MemberVO.grade}' />"
                                                                                        placeholder="${MemberVO.grade}"
                                                                                        style="display: none;" />
                                                                                </div>

                                                                                <div class="modal-footer">
                                                                                    <button type="button"
                                                                                        class="btn btn-secondary"
                                                                                        data-bs-dismiss="modal">취소</button>
                                                                                    <button type="button"
                                                                                        class="btn btn-info"
                                                                                        onclick="fn_saveMember()">저장</button>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <c:out value="${MemberVO.email}" />
                                                    </td>
                                                    <td>
                                                        <c:out value="*****" />
                                                    </td>
                                                    <td>
                                                        <c:out value="${MemberVO.phone}" />
                                                    </td>
                                                    <td>
                                                        <c:out value="${MemberVO.grade}" />
                                                    </td>
                                                </tr>
                                        </tbody>
                                    </table>
                                </div>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bcryptjs/2.4.3/bcrypt.min.js"></script>
    <script>
        sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
    </script>
    <script src="<c:url value='/resources/js/common/common.js'/>"></script>
    <script src="<c:url value='/resources/js/common/header.js'/>"></script>
    <script src="<c:url value='/resources/js/mypage/main.js'/>"></script>
</body>

</html>