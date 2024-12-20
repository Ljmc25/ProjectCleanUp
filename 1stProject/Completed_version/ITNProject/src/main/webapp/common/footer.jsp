<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!-- Footer Section -->
    <div style="background-color: rgb(0, 12, 26);">
      <nav id="nav1" class="navbar navbar-expand-lg">
        <div class="container-fluid">
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarFooter"
            aria-controls="navbarFooter"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <!-- White Divider -->
          <div style="border-bottom: solid white 10px;"></div>

          <!-- Footer Navigation Links -->
          <div class="collapse navbar-collapse" id="navbarFooter">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 mx-auto" style="font-size: 20px; padding-top: 30px;">
              <li class="nav-item"><a class="nav-link" href="http://localhost:8000/companyabout?list-company1">회사소개</a></li>
              <li class="nav-item"><a class="nav-link" href="http://localhost:8000/companyabout?id=2">윤리경영</a></li>
              <li class="nav-item"><a class="nav-link" href="http://localhost:8000/companyabout?id=3#list-company1">이용약관</a></li>
              <li class="nav-item"><a class="nav-link" href="http://localhost:8000/companyabout?id=4">개인정보처리방침</a></li>
            </ul>
          </div>
        </div>
      </nav>

      <!-- Company Info Section -->
      <div class="company-info d-flex align-items-center justify-content-center" style="background-color: rgb(0, 12, 26);">
        <a class="nav-link" href="#">
          <img src="<c:url value='/resources/images/common/ITN_MOVIES.jfif'/>" alt="itn movies" style="width: 120px; height: 94px;" />
        </a>
        <p style="margin-left: 10px; color: white;">
          사업자 정보: ITN MOVIES 사업자 등록번호: 123-45-67890 대표자명: 김영수 <br>
          주소: 서울특별시 강남구 삼성로 123, 4층 전화번호: 02-1234-5678 이메일: contact@itnmovies.co.kr <br>
          웹사이트: www.itnmovies.co.kr 설립연도: 2024년 주요 사업: 영화 상영, 영화관 운영 및 관련 서비스
        </p>
      </div>
    </div>
