// LoginView.vue // vueInit
<template>
  <div class="main">
    <div class="row justify-content-center login-box">
      <div class="col-xl-4 col-lg-6 col-md-9">
        <div class="card mt-5">
          <div class="card-body p-0">
            <!-- {/* Nested Row within Card Body */} -->
            <div class="row">

              <!-- TODO: 입력양식(오른쪽) -->
              <div class="col-lg-12">
                <div class="p-5">
                  <div class="login-main-text-title">
                    <p class="login-main-text">로그인</p>
                    <hr/>
                  </div>
                  <div class="user">
                    <div class="form-group">
                      <p class="login-text">이메일</p>
                      <input
                          type="text"
                          class="form-control form-control-user mb-3 input-box"
                          v-model="user.email"
                          required
                      />
                    </div>

                    <div class="form-group">
                      <p class="login-text">비밀번호</p>
                      <input
                          type="password"
                          class="form-control form-control-user mb-3 input-box"
                          v-model="user.password"
                          required
                      />
                    </div>

                    <button
                        class="btn btn-dark btn-user w-100 mb-3 login-button"
                        @click="login"
                    >
                      로그인
                    </button>
                  </div>
                  <hr/>
                  <div class="register-text">
                    <a>등록된 아이디가 없으신가요?</a>
                    <br>
                    <a class="register-button" href="/auth/register">회원가입</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import MemberService from '@/services/auth/MemberService';

export default {

  data() {

    return {
      user: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    required(value) {
      return value && value.trim() !== ''; // 값이 비어있지 않은지 확인
    },
    // 샘플 => 수정 : 추천(방법) : 이해 가정(로직)
    async login() {
      if (!this.required(this.user.email)) {
        alert("이메일을 입력하세요.");
        return;
      }
      if (!this.required(this.user.password)) {
        alert("비밀번호를 입력하세요.");
        return;
      }
      try {
        let response = await MemberService.login(this.user);
        console.log(response.data); // 디버깅
        // TODO: 공유변수 : loggedIn 상태 변경 : true
        // TODO: vue/react 공유저장소 단점 : 새로고침(정보 사라짐)
        // TODO: 보완 : 쿠키/로컬스토리지 등 이용 : 값을 저장
        //        (웹브라우저 기본 기능)
        this.$store.state.loggedIn = true;
        // TODO: 사용법(저장) : localStorage.setItem("키", 값(문자열));
        //  js : 객체->문자열변환 : JSON.stringify(객체)
        localStorage.setItem("user", JSON.stringify(response.data));
        // 홈으로 강제이동 : /
        this.$router.push("/");
      } catch (error) {
        this.$store.state.loggedIn = false; // 실패, 로그인안됨
        alert("로그인 실패. 이메일 또는 비밀번호를 확인하세요.");
        console.log(error);
      }
    },
  },
  // TODO: 화면이 뜰때 이미 로그인되어있으면 홈(/) 강제이동
  mounted() {
    if (this.$store.state.loggedIn == true) {
      this.$router.push("/");
    }
  },


};
</script>
<style>
.main {
}

.login-box {
  margin-top: 80px;
  margin-bottom: 130px;
}

.login-main-text-title {
}

.login-main-text {
  text-align: left;
  font-size: 16px;
}

.login-text {
  margin-bottom: 5px;
  font-size: 14px;
}

.input-box {
  background-color: lightgray;
  border: none; /* 기본 테두리 제거 */
  transition: border-color 0.3s ease; /* 부드러운 전환 효과 */
}

.input-box:focus {
  outline: none; /* 포커스 시 기본 외곽선 제거 */
  background-color: lightgray;
  border-bottom: 1px solid dimgray; /* 입력 시 밑줄 강조 */
  box-shadow: none; /* 기본 그림자 제거 */
}

.login-button {
  margin-top: 20px;
}

.register-text {
  margin-top: 20px;
  text-align: center;
  font-size: 13px;
}

.register-button {
  margin-top: 20px;
  border: none;
  text-decoration: 1px solid underline;
  color: inherit; /* 부모 요소의 색상을 상속받음 */
  cursor: pointer; /* 마우스 포인터 변경 */
}
</style>
