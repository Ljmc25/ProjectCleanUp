<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ITN MOVIE</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value='/resources/css/common/style.css'/>"/>
<link rel="stylesheet" href="<c:url value='/resources/css/common/header.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/common/footer.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/sign/signin.css'/>">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
	<header>
		<jsp:include page="/common/header.jsp"></jsp:include>
	</header>
	<main>
		<div class="login-container">
			<form class="row g-3 user" id="siginin-form" method="post">
				<h2 class="text-center mb-4">로그인</h2>

				<!-- E-mail 입력 필드 -->
				<div class="row mb-3">
					<label for="emailIdInput" class="form-label">E-mail</label>
					<div class="col-6">
						<input type="text" class="form-control" id="emailIdInput"
							name="email1" placeholder="e-mail은 id로 쓰입니다" />
					</div>
					<div class="col-6">
						<div class="input-group">
							<div class="input-group-text">@</div>
							<div class="d-flex gap-4">
								<div>
									<input type="text" class="form-control" id="customDomainInput"
										placeholder="직접 입력" disabled />
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
				</div>

				<!-- 숨겨진 이메일 필드 -->
				<input type="hidden" id="email" name="email" />

				<!-- 비밀번호 입력 필드 -->
				<div class="col-12 mb-3">
					<label for="inputPassword" class="form-label">비밀번호</label> <input
						type="password" class="form-control" id="inputPassword"
						name="password" placeholder="비밀번호 입력" required />
				</div>

				<!-- 로그인 버튼 -->
				<div class="col-12 mt-3">
					<button type="submit" class="btn btn-primary w-100" id="siginin-bn">로그인</button>
				</div>

				<!-- 회원가입 링크 -->
				<div class="col-12 text-center mt-2">
					<p>
						아직 회원이 아니신가요? <a id="signup-bn">회원 가입하기</a>
					</p>
				</div>
			</form>
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
	<script src="<c:url value='/resources/js/sign/signin.js'/>"></script>
	
</body>
</html>
