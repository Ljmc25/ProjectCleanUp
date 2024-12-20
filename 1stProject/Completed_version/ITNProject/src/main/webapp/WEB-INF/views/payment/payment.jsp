<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ITN MOVIE</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/style.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/header.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common/footer.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/payment/payment.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/payment/button.css'/>" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header>
        <jsp:include page="/common/header.jsp" />
    </header>
    <main>
    	<form method="post"> 
         	<input type="hidden" name="boid" value="${bookvo.boid}"> <!-- boid 값 추가 -->
         	<input type="hidden" name="email" value="${sessionScope.memberVO.email}">
         	<input type="hidden" name="totalPrice" value="${bookvo.totalprice}" />
         	<input type="hidden" name="bookNum" value="${bookvo.bookNum}" />
         
            <div class="position-absolute top-50 start-0" id="payment">
                <h1 style="font-size: 25px;">결제하기</h1>
                <hr id="border">
                <h2 class="text" style="font-size: 25px; margin-top: 1vh;">예매정보</h2>
                <div class="card mb-3" style="width: calc(100% - 40px); max-width: 800px;">
                    <div class="row g-0">
                        <div class="col-md-4">
                            <img src="../<c:out value='${bookvo.imageSmallUrl}'/>" class="img-fluid rounded-start" alt="Look_Back" width="550">
                        </div>
                        <!-- 예매정보 -->
                        <div class="col-md-8">
                            <div class="card-body">
                                <h1 class="card-title" style="font-size: 25px;">
                                    <c:out value='${bookvo.title}' />
                                </h1>
                                <br>
                                <h3 class="card-text" style="font-size: 20px;">
                                    <c:out value='${bookvo.showingTime}' />
                                </h3>
                                <h4 class="card-text" style="font-size: 20px;">
                                    선택 좌석 : <c:out value='${bookvo.selectSeat}' />
                                </h4>
                                <h5 class="card-text" style="font-size: 20px;">
                                    ITN 극장 <c:out value='${bookvo.branch}' />
                                </h5>
                                <h5 class="card-text" style="font-size: 20px;">
                                    러닝 타임 <c:out value='${bookvo.runningTime}' /> 분
                                </h5>
                                <br>
                                <p class="card-text">
                                    <small class="text-muted">환불 시 상영 1시간 전 상품만 환불 가능합니다</small>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <hr id="border">
            </div>
            <div id="discount"></div>

            <!-- 결제 금액 안내창 -->
            <div class="border border-start-0" id="Payment_amount">
                <h3 class="text-light" id="amunt_text">결제 금액</h3>
                <div class="border" id="amount_subbox1">
                    <div class="amount-container">
                        <h4 id="amunt_subtex">
                            성인 <c:out value='${bookvo.adultNum}' />명
                        </h4>
                        <span class="amount-value"><c:out value='${bookvo.adultPrice}' />원</span>
                    </div>
                    <div class="amount-container">
                        <h4 id="amunt_subtex">
                            어린이 <c:out value='${bookvo.childrenNum}' />명
                        </h4>
                        <span class="amount-value"><c:out value='${bookvo.childrenPrice}' />원</span>
                    </div>
                    <div class="amount-container">
                        <h4 id="amunt_subtex">
                            청소년 <c:out value='${bookvo.teenagerNum}' />명
                        </h4>
                        <span class="amount-value"><c:out value='${bookvo.teenagerPrice}' />원</span>
                    </div>
                    <div class="amount-container">
                        <h4 id="amunt_subtex">
                            노인 <c:out value='${bookvo.agedNum}' />명
                        </h4>
                        <span class="amount-value"><c:out value='${bookvo.agedPrice}' />원</span>
                    </div>
                    <hr class="border-black" id="amunt_border">
                    <div class="amount-container2">
                        <h3 id="amunt_subtex2">금액</h3>
                        <span class="amount-value2"><c:out value='${bookvo.totalprice}' />원</span>
                    </div>
                </div>

                <div id="amount_last">
                    <div class="amount-container4">
                        <h3 id="am_last_fn">최종결제금액</h3>
                        <span class="amount-value4" id="last_fn"><c:out value='${bookvo.totalprice}' />원</span>
                    </div>
                </div>

                <hr class="border-black" id="amunt_border2">

                <div id="amount_last2">
                    <div class="amount-container5">
                        <h3 id="am_last_fn2">결제수단</h3>
                        <span class="amount-value" id="selectedPaymentMethod">결제 수단을 선택해주세요</span>
                    </div>
                </div>
                <div class="button-container">
                
                    <button id="back_bt" class="btn btn-secondary">이전</button>
                
                    <button type="button" id="paymentSaveButton" class="btn btn-primary payment_bt">결제</button>
                </div>
            </div>
            <input type="hidden" id="methodInput" name="methodType">
           
            <!-- 결제수단 -->
            <div id="payment_methods"> 
                <h2 class="text-start" id="card_text">결제수단</h2>
                <button type="button" id="card_bt" class="btn btn-outline-dark toggle-btn" onclick="selectMethod('신용/체크카드')">카드 결제</button>
                <button type="button" id="bank_book_bt" class="btn btn-outline-dark toggle-btn" onclick="selectMethod('계좌 이체')">계좌 결제</button>
            </div>
        </form>
    </main>
 
    <footer>
        <jsp:include page="/common/footer.jsp" />
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
    </script>
    <script type="text/javascript">
    	function selectMethod(method) {
        	document.getElementById('selectedPaymentMethod').innerText = method; // 결제 수단 텍스트 업데이트
        	document.getElementById('methodInput').value = method; // 히든 필드 업데이트
    	}
    	
    	const boid = "${bookvo.boid}";
    	const bookNum = "${bookvo.bookNum}";
    	const totalprice = "${bookvo.totalprice}";
    	const email = "${sessionScope.memberVO.email}";
    </script>
    <script src="<c:url value='/resources/js/common/common.js'/>"></script>
    <script src="<c:url value='/resources/js/common/header.js'/>"></script>
    <script src="<c:url value='/resources/js/payment/payment.js'/>"></script>
</body>

</html>