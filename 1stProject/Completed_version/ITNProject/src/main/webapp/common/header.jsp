<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

	<div>
		<p style="height: 30px; background-color: rgb(0, 12, 26); margin: 0px; padding: 0px;"></p>
	</div>
	<!-- Header Section -->
	<div style="background-color: rgb(0, 12, 26)">
		<nav id="nav1" class="navbar navbar-expand-lg">
			<div class="container-fluid">
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
					<span class="navbar-toggler-icon"></span>
				</button>
				<!-- Centered Navigation Links -->
				<div class="collapse navbar-collapse" id="navbarSupportedContent"
					style="display: flex; justify-content: center; align-items: flex-end; height: 100%;">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0 mx-auto"
						style="list-style: none; display: flex; align-items: flex-end; justify-content: center; padding: 0; margin: 0; font-size: 25px; font-weight: bold; color: white;">
						<li class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="headerMovie(); return false">영화</a></li>
						<li class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="headerReserve(); return false">예매</a></li>
						<li class="nav-item"><a class="nav-link"
							href="#">극장</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/projectb/main"><img
								style="width: 200px; height: 140px; margin: 0px; padding: 0px;"
								src="<c:url value='/resources/images/common/ITN_MOVIES.jfif'/>" alt="itn movies" /></a></li>
						<li class="nav-item"><a class="nav-link" href="#">이벤트</a></li>
						<li class="nav-item"><a class="nav-link"
							href="#">스토어</a></li>
						<li class="nav-item"><a class="nav-link" href="#">혜택</a></li>
					</ul>
				</div>
				<!-- Login and Support Links -->
				<ul class="nav-login" style="padding-top: 5px; font-size: 15px; position: absolute;">
					<a class="nav-item" id="suppord" href="javascript:void(0);" onclick="headerCenter(); return false">고객센터</a>
					<c:if test="${sessionScope.memberVO == null }">
						<a class="nav-item" id="login"  href="javascript:void(0);" onclick="headerSignin(); return false">로그인</a>
						<a class="nav-item" id="register"  href="javascript:void(0);" onclick="headerSignup(); return false">회원가입</a>
					</c:if>
					<c:if test="${sessionScope.memberVO != null }">
						<a class="nav-item" id="mypage" href="javascript:void(0);" onclick="headerMypage(); return false">마이페이지</a>
						<c:if test="${sessionScope.memberVO.grade =='ADMINISTRATOR'}">
							<a class="nav-item" id="adminpage" href="javascript:void(0);" onclick="headerAdmin(); return false">관리자 페이지</a>
						</c:if>
						<a class="nav-item" id="logout" href="javascript:void(0);" onclick="headerSignOut(); return false">로그아웃</a>
						<!-- 사용자 이메일을 출력하는 부분 -->
					</c:if>
					<span class="hidden">${sessionScope.memberVO.email}</span>
				</ul>
			</div>
		</nav>
	</div>
	<div style="margin: 0; padding: 0;">
		<p style="width: 100%; height: 3px; background-color: white; margin: 0; padding: 0;"></p>
		<p style="width: 100%; height: 10px; background-color: rgb(0, 12, 26); margin: 0; padding: 0;"></p>
	</div>
