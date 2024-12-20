<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 메뉴 탭 -->

<div class="col-md-2 bg-dark d-flex flex-column" style="min-height: 100vh;">
    <div>
        <a href="<c:url value='/main'/>"><img src="<c:url value='/resources/images/admin/ITN_MOVIES.png'/>" class="figure-img img-fluid rounded"
            alt="영화관 로고"></a>
    </div>
    <nav class="nav flex-column">
        <div>
            <a class="nav-link link-light" href="<c:url value='/admin/main'/>" id="menu1">
                <img src="<c:url value='/resources/images/admin/default_home.png'/>" alt="관리자메인"
                    class="figure-img img-fluid rounded" style="width: 15%; margin-right: 0.5vw;"> 관리자 메인
            </a>
        </div>
        <div>
            <a class="nav-link link-light" href="<c:url value='/admin/notice'/>" id="menu2" style="margin-top: 2vh;">
                <img src="<c:url value='/resources/images/admin/default_announcement.png'/>" alt="공지사항"
                    class="figure-img img-fluid rounded" style="width: 15%; margin-right: 0.5vw;"> 공지사항
            </a>
        </div>
        <div>
            <a class="nav-link link-light" href="<c:url value='/admin/member'/>" id="menu3" style="margin-top: 2vh;">
                <img src="<c:url value='/resources/images/admin/default_person.png'/>" alt="회원리스트"
                    class="figure-img img-fluid rounded" style="width: 15%; margin-right: 0.5vw;"> 회원 리스트
            </a>
        </div>
        <div class="dropdown" style="margin-top: 2vh;">
            <div class="nav-link link-light dropdown-toggle" style="cursor: pointer;" id="menu4">
                <img src="<c:url value='/resources/images/admin/default_movie.png'/>" alt="영화 관리"
                    class="figure-img img-fluid rounded" style="width: 15%; margin-right: 0.5vw;"> 영화 관리
            </div>
            <div class="dropdown-content">
                <a class="dropdown-item" href="<c:url value='/product/addition'/>" id="menu4_1">신규 영화 등록</a>
                <a class="dropdown-item" href="<c:url value='/product/list'/>" id="menu4_3">영화목록</a>
            </div>
        </div>
        <div>
            <a class="nav-link link-light" href="<c:url value='/admin/faq'/>" id="menu7" style="margin-top: 2vh;">
                <img src="<c:url value='/resources/images/admin/default_qna.png'/>" alt="자주 묻는 질문"
                    class="figure-img img-fluid rounded" style="width: 15%; margin-right: 0.5vw;"> 자주 묻는 질문
            </a>
        </div>
    </nav>
    <div class="mt-auto">
        <a href="<c:url value='/logout'/>" style="border-style: none; color: white; margin-bottom: 1.5vh;" onclick="log_out()">
            <img src="<c:url value='/resources/images/admin/logout.png'/>" alt="로그아웃"
                style="margin-right: 3.5vh; margin-left: 1.5vh; margin-bottom: 2vh; width: 15%;">로그아웃
        </a>
    </div>
</div>