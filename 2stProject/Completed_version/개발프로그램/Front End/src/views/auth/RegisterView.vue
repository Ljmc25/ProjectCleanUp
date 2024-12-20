// views/auth/RegisterView.vue // vueInit
<template>
  <div>
    <div class="row justify-content-center register-box">
      <div class="col-xl-4 col-lg-6 col-md-9">
        <div class="card mt-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">

              <!-- TODO: 오른쪽(email/pwd 입력양식) -->
              <div class="col-lg-12 register-infomation-box">
                <div class="p-5">
                  <div class="text-center register-title">
                    <h1 class="h4 text-gray-900 mb-4">회원 가입</h1>
                  </div>
                  <div class="register-text-title">
                    <p>가입 정보 입력</p>
                    <hr/>
                  </div>

                  <div class="user">
                    <div class="form-group col-sm-12">
                      <p class="register-text">이메일</p>
                      <div class="input-email-group">
                        <input
                            type="email"
                            class="form-control form-control-user mb-3 col-sm-3 input-box"
                            v-model="user.email"
                            name="id"
                            required
                        />
                        <!--                        <button type="button"
                                                        onclick="fn_dbCheckId()"
                                                        name="dbCheckId"
                                                        class="btn btn-close-white btn-user w-10 col-sm-5 checkId">중복확인
                                                </button>-->
                      </div>
                    </div>
                    <div class="form-group row">
                      <p class="register-text">비밀번호</p>
                      <div class="col-sm-12 mb-3 mb-sm-0">
                        <input
                            type="password"
                            id="password1"
                            class="form-control form-control-user mb-3 input-box"
                            v-model="user.password"
                            required
                        />
                        <p class="help-text">8~30자 이내로 영문자/숫자/특수문자 중 2가지 이상포함</p>
                      </div>
                      <br>
                      <div class="col-sm-12">
                        <p class="register-text">비밀번호 확인</p>
                        <input
                            type="password"
                            id="password2"
                            v-model="confirmPassword"
                            class="form-control form-control-user mb-3 input-box"
                            required
                        />
                      </div>
                      <div class="form-group">
                        <p class="register-text">이름</p>
                        <input
                            type="text"
                            class="form-control form-control-user mb-3 input-box"
                            v-model="user.name"
                            required
                        />
                      </div>
                      <div class="form-group">
                        <p class="register-text">휴대폰 번호</p>
                        <input
                            type="text"
                            class="form-control form-control-user mb-3 input-box"
                            v-model="user.phoneNumber"
                            required
                        />
                        <p class="help-text">'-'를 빼고 휴대폰 번호를 입력해주세요</p>
                      </div>

                    </div>
                    <button
                        @click="register"
                        class="btn btn-dark btn-user w-100 mb-3 register-button"
                    >
                      등록
                    </button>
                  </div>
                  <hr/>
                  <div class="text-center login-text">
                    <a>등록된 아이디가 있으신가요?</a>
                    <br>
                    <a class="login-button" href="/auth/login">로그인</a>
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
import MemberService from "@/services/auth/MemberService";

export default {
  data() {
    return {
      user: {
        email: "",
        password: "",
        name: "",
        phoneNumber: "",
        codeName: "ROLE_USER", // 권한명
      },
      confirmPassword: '', //비밀번호 확인 필드
      passwordError: '' // 비밀번호 유효성 오류 메시지
    };
  },
  methods: {
    required(value) {
      return value && value.trim() !== ''; // 값이 비어있지 않은지 확인
    },

      // 비밀번호 유효성 검사
      validatePassword(password) {
      // 비밀번호 길이 조건 8 ~ 30 자리
      const lengthValid = password.length >= 8 && password.length <= 30;

      // 비밀번호 조건 ( 특수문자, 숫자, 영문자 2개 이상 추가 )
      const hasLetters = /[a-zA-Z]/.test(password);
      const hasNumbers = /\d/.test(password);
      const hasSpecialChars = /[!@#$%^&*(),.?":{}|<>]/.test(password);

      const charTypesCount = [hasLetters, hasNumbers, hasSpecialChars].filter(Boolean).length;

      if (!lengthValid) {
        return "비밀번호는 8자 이상 30자 이내여야 합니다.";
      }
      if (charTypesCount < 2) {
        return "비밀번호는 영문자, 숫자, 특수문자 중 두 가지 이상을 포함해야 합니다.";
      }
      return ''; // No error
    },
    async register() {
      if (!this.required(this.user.email)) {
        alert("이메일을 입력하세요.");
        return;
      }
      if (!this.required(this.user.password)) {
        alert("비밀번호를 입력하세요.");
        return;
      }
      if (!this.required(this.confirmPassword)) {
        alert("비밀번호 확인을 입력하세요.");
        return;
      }
      if (!this.required(this.user.name)) {
        alert("이름을 입력하세요.");
        return;
      }
      if (!this.required(this.user.phoneNumber)) {
        alert("전화번호를 입력하세요.");
        return;
      }
      if (this.user.password !== this.confirmPassword) {
        alert("비밀번호가 일치하지 않습니다.");
        return; // 함수 종료
      }

      // 비밀번호 유효성 검사 ( 조건 만족하지 않으면 오류 메세지 반환 )
      this.passwordError = this.validatePassword(this.user.password);
      if (this.passwordError) {
        alert(this.passwordError);
        return;
      }

      try {
        let response = await MemberService.insert(this.user);
        console.log(response.data); // 디버깅
        alert("회원 가입이 완료되었습니다.");
        this.$router.push('/');
      } catch (error) {
        alert("등록시 오류가 발생했습니다.");
        console.log(error);
      }
    },
  },
  // TODO: 화면이 뜰때 이미 회원이면(로그인됨) 홈(/)으로 강제이동
  mounted() {
    // TODO: vue 의 전역변수(공유변수) 관리하는 곳
    // 사용법 : this.$store.state.변수
    if (this.$store.state.loggedIn == true) {
      this.$router.push("/");
    }
  },
};

</script>

<style>

.register-box {
  margin-top: 80px;
  margin-bottom: 130px;
}

/* input box style */

.register-text-title {
  font-size: 16px;
}

.register-text {
  margin-bottom: 5px;
  font-size: 13px;
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

.register-button {
  margin-top: 20px;
}

.login-text {
  font-size: 13px;
}

.login-button {
  margin-top: 20px;
  font-size: 13px;
  border: none;
  text-decoration: 1px solid underline;
  color: inherit; /* 부모 요소의 색상을 상속받음 */
  cursor: pointer; /* 마우스 포인터 변경 */
}

.input-email-group {
  display: flex; /* 가로 정렬 */
  align-items: center; /* 수직 가운데 정렬 */
  gap: 10px; /* input과 버튼 간격 */
}

.checkId {
  padding: 7px;
  border: 1px solid lightgray;
  font-size: 13px;
  cursor: pointer; /* 클릭 포인터 */
  margin-bottom: 15px;
  margin-left: 8px;
}

.checkId:hover {
  background-color: gray; /* 호버 시 색상 */
}

.help-text {
  font-size: 12px;
  color: gray;
}
</style>
