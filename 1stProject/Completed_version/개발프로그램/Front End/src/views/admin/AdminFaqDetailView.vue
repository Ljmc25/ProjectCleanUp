<template>
  <div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="board-content p-4 border rounded shadow-lg" style="max-width: 1000px; width: 100%;">
      <h3 class="text-center">1:1 문의</h3>
      <hr />
      
      <!-- 제목 -->
      <div class="mb-3">
        <div class="fs-3 text-center font-weight-bold">제목 : {{ inquiries.title }}</div>
      </div>
      <hr />
      
      <!-- 작성자 및 기타 정보 -->
      <div class="row mb-4">
        <div class="col text-center">작성자 : {{ inquiries.writer }}</div>
        <div class="col text-center">등록 일시 : {{ inquiries.insertTime }}</div>
        <div class="col text-center">
          답변 : {{ inquiries.answerContent == null ? "N" : "Y" }}
        </div>
      </div>

      <hr />
      
      <!-- 문의 내용 -->
      <div class="mb-3 text-center">
        <div class="fs-4 font-weight-bold">문의 내용:</div>
        <div class="fs-5">{{ inquiries.content }}</div>
      </div>

      <!-- 답변 입력칸 -->
      <div v-if="showReplyInput" class="mt-3">
        <textarea
          class="form-control mb-2"
          rows="3"
          placeholder="답변 내용을 입력하세요"
          v-model="newReply"
        ></textarea>
        <button type="button" class="btn btn-primary me-2" @click="answersave">
          등록
        </button>
        <a href="/AdminFaqDetailView">
          <button type="button" class="btn btn-secondary" @click="toggleReplyInput">
            취소
          </button>
        </a>
      </div>

      <!-- 등록된 답변 -->
      <div v-if="inquiries.answerContent" class="mt-4">
        <hr />
        <h4 class="text-center">답변 내용</h4>
        <div class="list-group-item text-center">
          <!-- 수정할 답변 내용 표시 -->
          <div v-if="isEditing">
            <textarea
              class="form-control mb-2"
              v-model="updatedReply"
              rows="3"
            ></textarea>
            <button
              type="button"
              class="btn btn-primary me-2"
              @click="saveUpdatedReply"
            >
              수정 저장
            </button>
            <button type="button" class="btn btn-secondary" @click="cancelEdit">
              취소
            </button>
          </div>
          <div v-else>
            {{ inquiries.answerContent }}
            <p class="text-center" style="margin-top:3vh;">
              <button type="button" class="btn btn-sm btn-primary me-2" @click="editReply">
                답변 수정
              </button>
              <button type="button" class="btn btn-sm btn-danger" @click="removeAnswer">
                답변 삭제
              </button>
            </p>
          </div>
        </div>
        <hr />
      </div>

      <!-- 답변 입력칸 미사용 시 -->
      <div v-if="!showReplyInput" class="text-center">
        <button
          type="button"
          class="btn btn-primary me-2"
          v-if="!inquiries.answerContent"
          @click="toggleReplyInput"
        >
          답변하기
        </button>
        <button type="button" class="btn btn-danger me-2" @click="remove">
          문의 삭제
        </button>
        <a href="/admin/AdminFaq">
          <button type="button" class="btn btn-secondary">확인/뒤로가기</button>
        </a>
      </div>

    </div>
  </div>
</template>

<script>
import InquiryService from "@/services/basic/InquiryService";

export default {
  data() {
    return {
      showReplyInput: false, // 답변 입력칸 표시 여부
      newReply: "", // 새로운 답변 내용
      updatedReply: "", // 수정된 답변 내용
      isEditing: false, // 답변 수정 여부
      email: "",
      inquiries: {
        iqid: this.$route.params.iqid,
        title: "",
        content: "",
        writer: "",
        answerId: null,
        answerContent: "",
        answerWriter: "",
        insertTime: "",
        answerInsertTime: "",
      },
    };
  },
  methods: {
    async getDetail(iqid) {
      try {
        let response = await InquiryService.get(iqid);
        console.log(response.data);
        this.inquiries = response.data;
      } catch (error) {
        console.log(error);
      }
    },

    async remove() {
      try {
        let response = await InquiryService.remove(this.inquiries.iqid);
        console.log(response.data); // 디버깅
        // 전체조회 화면 강제 이동
        this.$router.push("/admin/AdminFaq");
      } catch (error) {
        console.log(error);
      }
    },

    toggleReplyInput() {
      this.showReplyInput = !this.showReplyInput; // 답변 입력칸 표시/숨기기
    },

    async answersave() {
      try {
        const answerData = {
          iqid: this.inquiries.iqid, // 답변의 ID는 문의 ID와 동일
          reid: this.inquiries.iqid, // REID 설정
          contents: this.newReply,
          writer: this.email, // 관리자 정보 설정, 나중에 수정하기
        };

        let response = await InquiryService.insertadmin(answerData);
        console.log(response.data);
        this.$router.push("/admin/AdminFaq");
      } catch (error) {
        console.log(error);
      }
    },

    // 답변 수정 시작
    editReply() {
      this.isEditing = true;
      this.updatedReply = this.inquiries.answerContent; // 수정할 답변 내용 미리 설정
    },

    cancelEdit() {
      this.isEditing = false;
      this.updatedReply = ""; // 수정 취소 시 내용 초기화
    },

    async saveUpdatedReply() {
      console.log("수정 시 이메일 값:", this.email); // 디버깅
      try {
        const updatedData = {
          iqid: this.inquiries.answerId,
          contents: this.updatedReply,
          writer: this.email,
        };

        let response = await InquiryService.update(
          this.inquiries.answerId,
          updatedData
        );
        console.log(response.data);
        this.inquiries.contents = this.updatedReply; // 수정된 답변 내용 반영
        this.isEditing = false; // 수정 모드 종료
        alert("답변이 수정되었습니다.");
      } catch (error) {
        console.log(error);
      }
    },

    // 답변 삭제
    async removeAnswer() {
      try {
        let response = await InquiryService.remove(this.inquiries.answerId);
        console.log(response.data); // 디버깅
        // 전체조회 화면 강제 이동
        this.$router.push("/admin/AdminFaq");
      } catch (error) {
        console.log(error);
      }
    },
  },
  mounted() {
    const userData = localStorage.getItem("user");
    if (userData) {
      const parsedData = JSON.parse(userData);
      console.log(this.email);
      if (parsedData.email) {
        this.email = parsedData.email; // email 값을 업데이트
        console.log("로그인한 이메일:", this.email);
      } else {
        console.warn("Email 값이 존재하지 않습니다.");
      }
    } else {
      console.warn("localStorage에서 'user' 데이터를 찾을 수 없습니다.");
    }

    this.getDetail(this.$route.params.iqid);
  },
};
</script>

<style scoped>
/* 1:1 문의 페이지 스타일링 */
h3, h4 {
  font-weight: bold;
  color: #333;
}

hr {
  border-top: 1px solid rgb(0, 0, 0);
  margin: 20px 0;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center; /* 세로 및 가로 중앙 정렬 */
  min-height: 100vh;
}

.board-content {
  width: 100%;
  max-width: 1000px;
  padding: 30px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  text-align: center; /* 전체 텍스트 중앙 정렬 */
}

.text-center {
  text-align: center;
}

.text-end {
  text-align: right;
}

.mb-3 {
  margin-bottom: 1.5rem;
}

button {
  font-size: 0.9rem; /* 버튼 크기 줄임 */
  padding: 8px 16px;
}

button.btn-primary {
  background-color: #007bff;
  border-color: #007bff;
}

button.btn-primary:hover {
  background-color: #0056b3;
  border-color: #004085;
}

button.btn-danger {
  background-color: #dc3545;
  border-color: #dc3545;
}

button.btn-danger:hover {
  background-color: #c82333;
  border-color: #bd2130;
}

button.btn-secondary {
  background-color: #6c757d;
  border-color: #6c757d;
}

button.btn-secondary:hover {
  background-color: #5a6268;
  border-color: #545b62;
}

textarea.form-control {
  width: 100%;
}

textarea {
  resize: vertical;
}

.list-group-item {
  margin-bottom: 15px;
}

.mb-4 {
  margin-bottom: 2rem;
}

.row {
  margin-bottom: 1rem;
  text-align: center;
}

/* 제목 및 문의 내용 강조 스타일 */
.fs-3, .fs-4 {
  font-weight: bold;
  color: #333;
}

.fs-5 {
  font-weight: normal;
}

</style>
