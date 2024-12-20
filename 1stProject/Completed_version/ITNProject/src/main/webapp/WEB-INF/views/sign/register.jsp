<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ITN MOVIE</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value='/resources/css/common/style.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/common/header.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/common/footer.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/sign/register.css'/>">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
	<header>
		<!-- 헤더 -->
		<jsp:include page="/common/header.jsp"></jsp:include>
	</header>
	<main>
		<div class="d-flex justify-content-center align-items-center"
			style="height: 100vh">
			<div class="container">
				<form class="row g-3 user" id= "register-form" method="post">
					<h2 class="text-center mb-4">회원가입</h2>


					<!-- E-mail 입력 필드 -->
					<div class="row mb-3">
						<label for="emailIdInput" class="form-label">E-mail</label>
						<div class="col-5">
							<input type="text" class="form-control" id="emailIdInput"
								name="emailIdInput" placeholder="e-mail이 ID. 영문자와 숫자만 입력 (20자 이내)"
								maxlength="20" oninput="validateInput(this)" />

							<div id="emailWarning" style="display: none; color: red;">이메일을
								입력해 주세요!</div>
						</div>
						<div class="col-5">
							<div class="input-group">
								<div class="input-group-text">@</div>
								<div class="d-flex gap-1">
									<div>
										<input type="text" class="form-control"
											id="customDomainInput1" placeholder="직접 입력" />
									</div>

									<select class="form-select" id="domainSelect">
										<option selected>Choose</option>
										<option value="naver.com">네이버</option>
										<option value="daum.net">다음</option>
										<option value="google.com">구글</option>
										<option value="direct">직접입력</option>
									</select>
								</div>
							</div>
						</div>

						<div class="col-2">
							<button type="button" class="btn btn-outline-primary w-100"
								id="checkDuplicateBtn">중복 확인</button>
						</div>

						<div id="resultMessage"></div>
						<c:if test="${not empty emailMessage}">
							<div class="alert alert-danger">${emailMessage}</div>
						</c:if>

						<input type="hidden" id="email" name="email" />
					</div>



					<!-- 오류 메시지 표시 -->
					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger">${errorMessage}</div>
					</c:if>

					<!-- 비밀번호 입력 필드 -->
					<div class="row mb-3">
						<div class="col-6">
							<label for="inputPassword" class="form-label">비밀번호</label> <input
								type="password" class="form-control" id="password"
								name="password" placeholder="비밀번호" />
						</div>
						<div class="col-6">
							<label for="inputPasswordConfirm" class="form-label">비밀번호확인</label>
							<input type="password" class="form-control"
								id="inputPasswordConfirm" name="passwordConfirm"
								placeholder="비밀번호 확인" oninput="checkPasswordMatch()" />
							<div id="passwordMismatch" style="color: red; display: none;">비밀번호가
								서로 틀렸습니다.</div>
						</div>
					</div>


					<!-- 전화번호 입력 필드 -->
					<div class="row mb-3">
						<div class="col-6">
							<label for="phone" class="form-label">전화번호</label> <input
								type="tel" class="form-control" id="phone" name="phone"
								placeholder="전화번호, '-'는 제외하고 입력" pattern="\d{10,11}"
								inputmode="numeric" required
								oninput="this.value = this.value.replace(/-/g, '')"
								title="10~11자리 숫자만 입력하세요." />
						</div>
					</div>

					<!-- 회원가입 및 취소 버튼 -->
					<div class="row">
						<div class="col-6 mt-3">
							<button type="button" id="signup-bn" class="btn btn-primary w-100">회원
								가입</button>
						</div>
						<div class="col-6 mt-3">
							<button type="button" class="btn btn-secondary w-100">취소</button>
						</div>
					</div>
			</div>
			</form>
		</div>
		</div>
	</main>
	<footer>
		<jsp:include page="/common/footer.jsp"></jsp:include>
	</footer>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
	</script>
	<script src="<c:url value='/resources/js/common/common.js'/>"></script>
	<script src="<c:url value='/resources/js/common/header.js'/>"></script>
	<script src="<c:url value='/resources/js/sign/register.js'/>"></script>
</body>
</html>
