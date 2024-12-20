# node_modules 폴더(실행폴더) 재생성
npm i
# 서버 실행
npm run serve
# 서버 종료 : ctrl + c(여러번 타이핑)

# 1) axios 설치 : 통신(json)
npm i axios
# 2) 페이징 lib : bootstrap-vue-3 설치
npm i bootstrap
npm i bootstrap-vue-3

# 참고) 설치 제거 : 
npm uninstall 페키지명
npm uninstall axois

# 요약 
# 회원가입 : 
  - services/MemberService/insert()
  - 화면 : views/auth/RegisterView.vue
# 로그인 :
  - services/MemberService/login()
  - 화면 : views/auth/LoginView.vue
     1) 로컬스토리지 : user 저장
        localStorage.setItem("user", JSON.stringify(response.data));
     2) loggedIn : true (변경)
  - 공유저장소 : store/index.js
     1) loggedIn : 로그인여부 변수
# 로그아웃 : 
   - services/MemberService/logout()
   - 로컬스토리지 : user 삭제
# 필터 구현(인터셉터 비슷) : 화면별 조회되기/안되기
   - 프론트 : 게시판 CRUD 위해 -> 웹토큰 -> 벡엔드 확인 -> 결과통보
   - 참고) 필터 왜? 부서/사원 등 벡엔드 : 웹토큰 유효한지, 우리회원인지 확인(항상구현)
       => 필터 : 부서/사원 등 조회 전에 미리 체크 (게시판 가기전에 미리 체크)
   - 프론트 헤더 : 웹토큰 넣어 전송 (axios 함수들에 코딩)
      예) let user = JSON.parse(localStorage.getItem("user"));           // 웹토큰 조회
          const token = { Authorization: "Bearer "+ user?.accessToken }; // 웹토큰 정의
           axios.get("url", {headers: token});                           // 벡엔드 토큰 전송
       