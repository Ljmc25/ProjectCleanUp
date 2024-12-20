<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ITN MOVIE</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value='/resources/css/common/style.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/common/header.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/common/footer.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/main/main.css'/>" />
</head>
<body>
	<header>
		<!-- 헤더 -->
		<jsp:include page="/common/header.jsp"></jsp:include>
	</header>
	<main>
			<div class="first-divided">

				<div class="container">
					<br /> <br /> <br /> <br />

					<div class="boxoffice">
						<div class="col-12 text-center">
							<p class="fw-bold text-decoration-underline">박스오피스</p>
						</div>
					</div>

					<div class="col-12 text-end">
						<button type="button" class="btn btn-primary" onclick="headerMovie();">더 많은 영화 보기+</button>
					</div>
					<br />

					<div class="container text-center bg-custom">
						<div class="row d-flex align-items-stretch">
							<c:forEach var="data" items="${productList}">
								<div class="col">
									<div class="card" style="height: 100%">
										<img src="<c:out value='${data.imageSmallUrl}'/> " class="card-img-top" alt="베테랑" />
										<div class="card-body">
											<h5 class="card-title"><c:out value='${data.title}'/></h5>
											<p class="card-text"></p>
											<div class="d-flex align-items-center">
												<a href="#" class="btn heart-img" id="like-btn"> 
													<img src="<c:url value='/resources/images/main/heart.png'/>" alt="">
												</a> 
												<span id="like-count" class="ms-2">0</span> 
												<a class="btn btn-primary ms-2" href="javascript:void(0);" onclick="reservePage('${data.title}'); return false">예매</a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>

					<br /> <br />

					<div class="container text-center nav-container">
						<div class="row justify-content-center align-items-center">
							<div class="col-auto">
								<div class="input-group">
									<input type="text" class="form-control form-control-sm"
										placeholder="영화를 입력해주세요" aria-label="영화를 입력해주세요"
										aria-describedby="button-addon2"
										style="border-radius: 5px 0 0 5px; border: 1px solid #007bff" />
									<button class="btn btn-info btn-sm" type="button"
										id="button-addon2" style="border-radius: 0 5px 5px 0">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
											fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                    <path
												d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
                  </svg>
									</button>
								</div>
							</div>
							<div class="col nav-link">
								<a href="timetable.html" class="nav-link">상영시간표</a>
							</div>
							<div class="col nav-link">
								<a href="boxoffice.html" class="nav-link">박스오피스</a>
							</div>
							<div class="col nav-link">
								<a href="fast_booking.html" class="nav-link">빠른예매</a>
							</div>
						</div>
					</div>
				</div>
				<br> <br>
			</div>

			<div class="second-divided">
				<br> <br> <br>
				<div class="container">
					<!-- 캐러셀 -->
					<div id="carouselExampleCaptions" class="carousel slide"
						data-bs-ride="false">
						<div class="carousel-indicators">
							<button type="button" data-bs-target="#carouselExampleCaptions"
								data-bs-slide-to="0" class="active" aria-current="true"
								aria-label="Slide 1"></button>
							<button type="button" data-bs-target="#carouselExampleCaptions"
								data-bs-slide-to="1" aria-label="Slide 2"></button>
							<button type="button" data-bs-target="#carouselExampleCaptions"
								data-bs-slide-to="2" aria-label="Slide 3"></button>
						</div>
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img src="<c:url value='/resources/images/main/vetterain2.jpeg'/>" class="d-block w-100"
									alt="..." />
								<div class="carousel-caption d-none d-md-block">
									<h5>베테랑2 I, THE EXECUTIONER</h5>
									<p>가족들도 못 챙기고 밤낮없이 범죄들과 싸우는 베테랑 형사 '서도철'(황정민)과 강력범죄수사대 형사들.
									</p>
								</div>
							</div>
							<div class="carousel-item">
								<img src="<c:url value='/resources/images/main/lookback.jpeg'/>" class="d-block w-100" alt="..." />
								<div class="carousel-caption d-none d-md-block">
									<h5>룩백</h5>
									<p>“만화를 그려서 다행이라고 생각해”</p>
								</div>
							</div>
							<div class="carousel-item">
								<img src="<c:url value='/resources/images/main/Joker_Folie_a_Deux.jpeg'/>" class="d-block w-100"
									alt="..." />
								<div class="carousel-caption d-none d-md-block">
									<h5>조커: 폴리 아 되 Joker: Folie à Deux</h5>
									<p>아캄에 수감된 조커와 할리 퀸의 운명적인 만남과 조커의 재판과정</p>
								</div>
							</div>
						</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>
				</div>
				<br /> <br /> <br />
			</div>

			<!-- 작은 캐러셀 (7개 슬라이드) -->
			<div class="third-divided">
				<div id="smallCarousel" class="carousel slide small-carousel"
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<div class="d-flex justify-content-center">
								<a href="#"> <img src="<c:url value='/resources/images/main/dolbyCinema.png'/>" class="d-block mx-2" alt="Slide 1" /></a> 
								<a href="#"> <img src="<c:url value='/resources/images/main/dolbyAtmos.jpeg'/>" class="d-block mx-2" alt="Slide 2" /></a>
								<a href="#"> <img src="<c:url value='/resources/images/main/comfort.jpeg'/>" class="d-block mx-2" alt="Slide 3" /></a>
								<a href="#"> <img src="<c:url value='/resources/images/main/kid.jpeg'/>" class="d-block mx-2" alt="Slide 4" /></a> 
								<a href="#"> <img src="<c:url value='/resources/images/main/THE_BOUTIQUE.jpeg'/>" class="d-block mx-2" alt="Slide 5" /></a> 
								<a href="#"> <img src="<c:url value='/resources/images/main/THE_BOUTIQUE_PRIVATE.jpeg'/>" class="d-block mx-2" alt="Slide 6" /></a> 
								<a href="#"> <img src="<c:url value='/resources/images/main/mx4d.jpeg'/>" class="d-block mx-2" alt="Slide 7" /></a>
							</div>
						</div>
						<div class="carousel-item">
							<div class="d-flex justify-content-center">
								<img src="<c:url value='/resources/images/main/dolbyCinema.png'/>" class="d-block mx-2" alt="Slide 1" /> 
								<img src="<c:url value='/resources/images/main/dolbyAtmos.jpeg'/>" class="d-block mx-2" alt="Slide 2" /> 
								<img src="<c:url value='/resources/images/main/comfort.jpeg'/>" class="d-block mx-2" alt="Slide 3" />
								<img src="<c:url value='/resources/images/main/kid.jpeg'/>" class="d-block mx-2" alt="Slide 4" />
								<img src="<c:url value='/resources/images/main/THE_BOUTIQUE.jpeg'/>" class="d-block mx-2" alt="Slide 5" /> 
								<img src="<c:url value='/resources/images/main/THE_BOUTIQUE_PRIVATE.jpeg'/>" class="d-block mx-2" alt="Slide 6" /> 
								<img src="<c:url value='/resources/images/main/mx4d.jpeg'/>" class="d-block mx-2" alt="Slide 7" />
							</div>
						</div>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#smallCarousel" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#smallCarousel" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>


				<div class="container text-center">
					<div class="row">
						<div class="col bottom-img">
							<a href="javascript:void(0);" onclick="noticePage(); return false"> 
								<img src="<c:url value='/resources/images/main/headphones.png'/>" alt="고객센터" />
								<p>고객센터</p>
							</a>
						</div>
						<div class="col bottom-img">
							<a href="javascript:void(0);" onclick="faqPage(); return false"> 
								<img src="<c:url value='/resources/images/main/question_and_answer.png'/>" alt="자주 묻는 질문" />
								<p>자주 묻는 질문</p>
							</a>
						</div>
						<div class="col bottom-img">
							<a href="javascript:void(0);" onclick="InquiryPage(); return false"> 
								<img src="<c:url value='/resources/images/main/comments.png'/>" alt="1:1 문의" />
								<p>1:1 문의</p>
							</a>
						</div>
					</div>
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
	<script src="<c:url value='/resources/js/main/main.js'/>"></script>
	<script src="<c:url value='/resources/js//center/common/common.js'/>"></script>
</body>
</html>
