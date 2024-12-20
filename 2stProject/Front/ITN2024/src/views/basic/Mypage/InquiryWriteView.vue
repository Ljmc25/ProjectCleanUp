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
      <h2>1:1 문의내역</h2>

      <table class="table buy-list mt-3">
        <thead>
          <tr>
            <td class="title-cell">제목</td>
            <th>
              <input
                class="form-control"
                type="text"
                placeholder="제목을 입력하세요"
                v-model="inquiry.title"
              />
            </th>
          </tr>
        </thead>

        <tbody>
          <tr>
            <td class="body-cell">본문</td>
            <td>
              <div class="mb-3">
                <!-- Quill 에디터 추가 -->
                <div ref="quillEditorContainer" style="height: 500px"></div>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="btn">
      <button class="upload-btn" @click="save" style="margin-right:3vw;">등록</button>

      <button class="delete-btn"><a href="/basic/mypage/inquiry">취소</a></button>
    </div>
  </div>
</template>

<script>
import Quill from "quill";
import InquiryService from "@/services/basic/InquiryService";

export default {
  name: "App",
  data() {
    return {
      inquiry: {
        title: "", // 제목
        contents: "", // 본문
        writer: "", // 작성자
      },
      quill: null, // Quill 인스턴스를 저장할 변수
    };
  },

  methods: {
    initQuill() {
      // Quill 에디터 인스턴스를 수동으로 초기화
      const options = {
        theme: "snow",
        placeholder: "내용을 입력해주세요...",
        modules: {
          toolbar: [
            ["bold", "italic", "underline", "strike"],
            ["blockquote", "code-block"],
            [{ header: 1 }, { header: 2 }],
            [{ list: "ordered" }, { list: "bullet" }],
            [{ script: "sub" }, { script: "super" }],
            [{ indent: "-1" }, { indent: "+1" }],
            [{ direction: "rtl" }],
            [{ size: ["small", false, "large", "huge"] }],
            [{ header: [1, 2, 3, 4, 5, 6, false] }],
            [{ color: [] }, { background: [] }],
            [{ font: [] }],
            [{ align: [] }],
            ["clean"],
            ["link", "image", "video"],
          ],
        },
      };

      // Quill 에디터 초기화
      this.quill = new Quill(this.$refs.quillEditorContainer, options);
      console.log("Quill initialized:", this.quill); // 초기화 확인을 위한 로그
    },

    // Quill 에디터로부터 텍스트를 안전하게 추출하는 메서드
    getQuillText() {
      if (this.quill) {
        const contentsText = this.quill.getText().trim(); // 텍스트만 추출
        console.log("Quill Text:", contentsText); // 텍스트 출력
        return contentsText;
      }
      console.error("Quill editor is not initialized.");
      return "";
    },

    // Save 메서드
    async save() {
      const contentsText = this.getQuillText(); // Quill 에디터에서 텍스트 추출
      if (contentsText === "") {
        console.error("내용이 비어 있습니다.");
        return;
      }

      try {
        console.log("Title:", this.inquiry.title);
        console.log("Contents (Plain Text):", contentsText); // 순수 텍스트만

        // 서버에 데이터 전송
        const response = await InquiryService.insert({
          title: this.inquiry.title,
          contents: contentsText, // 텍스트만 전송
          writer: this.inquiry.writer,
        });

        console.log("Response:", response.data);
        window.location.href = "/basic/mypage/inquiry"; // 등록 후 이동
      } catch (error) {
        console.error("Error during save:", error);
      }
    },
  },
  mounted() {
    // Quill 에디터 초기화
    this.initQuill();

    // localStorage에서 writer 값 설정
    const userData = localStorage.getItem("user");
    if (userData) {
      const parsedData = JSON.parse(userData);
      if (parsedData.email) {
        this.inquiry.writer = parsedData.email; // writer 값 설정
        console.log("Writer set to:", this.inquiry.writer); // 디버깅 로그
      } else {
        console.warn("Email 값이 존재하지 않습니다.");
      }
    } else {
      console.warn("localStorage에서 'user' 데이터를 찾을 수 없습니다.");
    }
  },
};
</script>

<style scoped>
@import "@/assets/css/Mypage/InquiryWriteView.css";
</style>
