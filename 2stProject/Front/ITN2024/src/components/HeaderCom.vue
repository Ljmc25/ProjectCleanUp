<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-bg nav-all ">
      <div class="container-fluid position-fixed " style="background-color: white">
        <!-- 대메뉴 -->
        <div class="collapse navbar-collapse nav-category" id="navbarScroll">
          <!-- 대메뉴(왼쪽) -->
          <ul class="navbar-nav me-auto mb-2 mb-lg-0 category-all">
            <li class="nav-item">
              <a class="nav-link active category" aria-current="page" href="/basic/product">전체상품</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active category" aria-current="page" href="/basic/product/durumagi">두루마기,도포</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active category" aria-current="page" href="/basic/product/jeogori">저고리</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active category" aria-current="page" href="/basic/product/bottoms">바지,치마</a>
            </li>
          </ul>

          <!-- TITLE -->
          <ul class="navbar-nav title-container">
            <li class="nav-item">
              <a class="nav-link title-link" href="/" style="font-style: italic">
              <img src="@/assets/images/logo5.png"/>
              </a>
            </li>
          </ul>

          <!-- 로그인메뉴(오른쪽) -->
          <ul class="navbar-nav">
            <!-- 관리자 -->
            <li class="nav-item" v-if="isAdmin">
              <a class="nav-link active" href="/admin/AdminMember" title="관리자">
                <img src="@/assets/images/header/admin.png" alt="관리자" class="nav-icon"/>
              </a>
            </li>
            <!-- 로그인 -->
            <li class="nav-item" v-if="this.$store.state.loggedIn == false">
              <a class="nav-link active" href="/auth/login" title="로그인">
                <img src="@/assets/images/header/login.png" alt="로그인" class="nav-icon"/>
              </a>
            </li>
            <!-- 회원가입 -->
            <li class="nav-item" v-if="this.$store.state.loggedIn == false">
              <a class="nav-link active" href="/auth/register" title="회원가입">
                <img src="@/assets/images/header/register.png" alt="회원가입" class="nav-icon"/>
              </a>
            </li>
            <!-- 장바구니 -->
            <li class="nav-item" v-if="this.$store.state.loggedIn == true">
              <a class="nav-link active" href="/advanced/cart" title="장바구니">
                <img src="@/assets/images/header/cart.png" alt="장바구니" class="nav-icon"/>
              </a>
            </li>
            <!-- 마이페이지 -->
            <li class="nav-item" v-if="this.$store.state.loggedIn == true">
              <a class="nav-link active" href="/basic/mypage" title="마이페이지">
                <img src="@/assets/images/header/mypage.png" alt="마이페이지" class="nav-icon"/>
              </a>
            </li>
            <!-- 고객센터 -->
            <li class="nav-item">
              <a class="nav-link active" href="/basic/notice" title="고객센터">
                <img src="@/assets/images/header/customer-service.png" alt="고객센터" class="nav-icon"/>
              </a>
            </li>
            <!-- 로그아웃 -->
            <li class="nav-item" v-if="this.$store.state.loggedIn == true">
              <a href="http://localhost:8080/" class="nav-link active" @click="logout" title="로그아웃">
                <img src="@/assets/images/header/logout.png" alt="로그아웃" class="nav-icon"/>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import MemberService from '@/services/auth/MemberService';

export default {
  computed: {
    isLoggedIn() {
      return this.$store.state.loggedIn;
    },
    isAdmin() {
      // 로컬 스토리지에서 사용자 정보를 가져와서 codeName이 admin_user인지 확인
      const user = localStorage.getItem("user");
      if (user) {
        const parsedUser = JSON.parse(user);
        return parsedUser.codeName === 'ROLE_ADMIN'; // ROLE_ADMIN 경우 true 반환
      }
      return false; // 로그인 정보가 없거나 admin_user가 아닐 경우 false 반환
    }
  },
  methods: {
    logout() {
      MemberService.logout();
      this.$store.state.loggedIn = false;
      this.$router.push('/login');
    },
  },

  watch: {
    // 로그인 상태가 변경되면 새로고침
    '$store.state.loggedIn'(newVal) {
      if (newVal) {
        window.location.reload(); // 로그인 후 페이지 새로고침
      }
    }
  }

};
</script>

<style scoped>
/* 네비게이션 바 */
.nav-all {
  z-index: 1000;
  margin-top: 6.4px;
  margin-left: -0.8vw;
}

/*네비게이션 바 사이즈*/
.nav-category {
  height: 60px;
}

/*브랜드 타이틀 위치*/
.title-container {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

/* 브랜드 타이틀 스타일 */
.title-link {
  font-size: 30px;
  font-weight: bold;
  color: black;
  text-decoration: none;
  white-space: nowrap;
}

/* 네비게이션 아이콘 */
.nav-icon {
  width: 22px;
  height: 22px;
  margin-left: 8px;
}

/* 상단 메뉴 폰트 */
.category {
  font-weight: bold;
  font-size: 13px;
  margin-left: 50px;
}

/* 이미지 hover */

</style>
