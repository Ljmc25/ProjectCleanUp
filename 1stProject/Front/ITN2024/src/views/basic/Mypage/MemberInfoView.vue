<template>
  <div class="container all mt-5">
    <h1 class="title">
      <router-link to="/mypage">MY PAGE</router-link>
    </h1>

    <hr class="hr2" />

    <div class="left">
      <li class="list-group-item p1">마이페이지</li>
      <hr />
      <br />

      <li class="list-group-item p">나의 쇼핑</li>
      <li class="list-group-item p2">
        <a href="/basic/mypage">-구매 내역 조회</a>
      </li>
      <br />
      <li class="list-group-item p">나의 활동</li>
      <li class="list-group-item p2">
        <a href="/basic/mypage/inquiry"> -1:1 문의하기 </a>
      </li>

      <br />
      <li class="list-group-item p">나의정보</li>
      <li class="list-group-item p2">
        <a href="/basic/mypage/memberinfo">-회원 정보</a>
      </li>
    </div>

    <div class="main">
      <h3>회원 정보</h3>

      <table class="table buy-list mt-3">
        <thead>
          <tr>
            <th class="email-cell">이메일</th>
            <th>
              <input
                class="form-control"
                type="text"
                value="남궁민수"
                readonly
                v-model="member.email"
              />
            </th>
          </tr>
        </thead>

        <tbody>
          <tr>
            <td class="password-cell">비밀번호</td>
            <td>
              <div class="form-floating">
                <input
                  v-model="member.password"
                  type="password"
                  class="form-control"
                  id="floatingPassword"
                  placeholder="Password"
                />
                <label for="floatingPassword">Password</label>
              </div>
              <p v-if="passwordError" class="error-message">
                {{ passwordError }}
              </p>
            </td>
          </tr>

          <tr>
            <td class="name-cell">이름</td>
            <td>
              <div class="form-floating mb-3">
                <input
                  v-model="member.name"
                  type="email"
                  class="form-control"
                  id="floatingInput"
                  placeholder="홍길동"
                />
                <label for="floatingInput">Name</label>
              </div>
            </td>
          </tr>

          <tr>
            <td class="phone-cell">휴대폰 번호</td>
            <td>
              <div class="form-floating mb-3">
                <input
                  v-model="member.phoneNumber"
                  type="email"
                  class="form-control"
                  id="floatingInput"
                  placeholder="010-xxxx-xxxx"
                />
                <label for="floatingInput">Phone-number</label>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="btn">
      <button class="upload-btn" @click="handleUpdate">회원정보수정</button>
      <a href="/basic/mypage">
        <button class="cancle-btn">취소</button>
      </a>
    </div>
  </div>
</template>

<script>
import MemberService from "@/services/auth/MemberService";

export default {
  data() {
    return {
      email: "",
      member: {
        password: "",
        name: "",
        phoneNumber: "",
      },
      confirmPassword: "", // 비밀번호 확인 필드
      passwordError: "", // 비밀번호 유효성 오류 메시지
    };
  },
  methods: {
    async getDetail() {
      try {
        let response = await MemberService.get(this.email);
        console.log(response.data);
        this.member = response.data;
        this.member.password = ".....";
      } catch (error) {
        console.log(error);
      }
    },

    async handleUpdate() {
      this.passwordError = this.validatePassword(this.member.password);

      if (this.passwordError) {
        // 유효성 검사 실패 시 업데이트 중단
        return;
      }

      try {
        let response = await MemberService.update(
          this.member.email,
          this.member
        );
        console.log(response.data);
        this.$router.push("/basic/mypage");
        alert("회원정보 수정완료.");
      } catch (error) {
        console.log(error);
      }
    },

    validatePassword(password) {
      const lengthValid = password.length >= 8 && password.length <= 30;
      const hasLetters = /[a-zA-Z]/.test(password);
      const hasNumbers = /\d/.test(password);
      const hasSpecialChars = /[!@#$%^&*(),.?":{}|<>]/.test(password);
      const charTypesCount = [hasLetters, hasNumbers, hasSpecialChars].filter(
        Boolean
      ).length;

      if (!lengthValid) {
        return "비밀번호는 8자 이상 30자 이내여야 합니다.";
      }
      if (charTypesCount < 2) {
        return "비밀번호는 영문자, 숫자, 특수문자 중 두 가지 이상을 포함해야 합니다.";
      }

      return ""; // 유효성 검사 통과
    },
  },

  mounted() {
    const userData = localStorage.getItem("user");
    if (userData) {
      const parsedData = JSON.parse(userData);
      if (parsedData.email) {
        this.email = parsedData.email;
      } else {
        console.warn("Email 값이 존재하지 않습니다.");
      }
    } else {
      console.warn("localStorage에서 'user' 데이터를 찾을 수 없습니다.");
    }

    this.getDetail(this.$route.params.email);
  },
};
</script>

<style scoped>
@import "@/assets/css/Mypage/MemberInfoView.css";

.error-message {
  color: red;
  font-size: 0.9rem;
  margin-top: 0.5rem;
}
</style>
