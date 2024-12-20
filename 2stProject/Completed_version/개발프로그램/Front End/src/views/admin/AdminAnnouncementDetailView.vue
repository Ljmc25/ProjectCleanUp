<template>
  <div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="board-content p-4 border rounded shadow-lg" style="max-width: 1000px; width: 100%;">

      <!-- 제목 영역 -->
      <div class="mb-4 text-center">
        <h2 class="text-black fw-bold">제목 : {{ notices.title }}</h2>
      </div>
      <hr class="mb-4" />

      <!-- 작성자 및 등록일 -->
      <div class="row mb-4">
        <div class="col text-start">
          <span class="text-muted">작성자 : <strong>{{ notices.writer }}</strong></span>
        </div>
        <div class="col text-end">
          <span class="text-muted">등록일 : <strong>{{ notices.insertTime }}</strong></span>
        </div>
      </div>
      <hr class="mb-4" />

      <!-- 내용 영역 -->
      <div class="mb-4">
        <p class="fs-5 text-muted">{{ notices.contents }}</p>
      </div>
      <hr class="mb-4" />

      <!-- 이미지 영역 (이미지가 없으면 표시하지 않음) -->
      <div v-if="notices.imageUrl" class="text-center mb-4">
        <img :src="notices.imageUrl" alt="공지사항 이미지" class="img-fluid rounded shadow" />
      </div>
      
      <!-- 원래 버튼 영역 -->
      <div class="text-center mt-4">
        <router-link :to="'/admin/AdminAnouncementUpdate/' + notices.noid">
          <button type="button" class="btn btn-primary me-2">
            수정
          </button>
        </router-link>

        <button type="button" class="btn btn-danger me-2" @click="remove">
          삭제
        </button>

        <a href="/admin/AdminAnouncement">
          <button type="button" class="btn btn-secondary">확인/뒤로가기</button>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import AnnouncementService from "@/services/basic/AnnouncementService";
export default {
  data() {
    return {
      notices: {
        noid: this.$route.params.noid,
        title: "",
        contents: "",
        imageUrl: "",
        image: undefined,
        writer: "",
        insertTime: "",
        updateTime: "",
      },
    };
  },
  methods: {
    async getDetail(noid) {
      try {
        let response = await AnnouncementService.get(noid);
        console.log(response.data);
        this.notices = response.data;
      } catch (error) {
        console.log(error);
      }
    },

    async remove() {
      try {
        let response = await AnnouncementService.remove(this.notices.noid);
        console.log(response.data); // 디버깅
        // 전체조회 화면 강제 이동
        this.$router.push("/admin/AdminAnouncement");
      } catch (error) {
        console.log(error);
      }
    },
  },
  mounted() {
    this.getDetail(this.$route.params.noid);
  },
};
</script>

<style scoped>
/* 공지사항 제목, 내용, 버튼 등 스타일링 */
h2 {
  font-weight: bold;
  color: black; /* 제목 검은색으로 설정 */
  margin-bottom: 20px; /* 제목과 다른 내용 간 간격 */
}

.fs-5 {
  font-size: 1.25rem;
  line-height: 1.5;
}

.text-muted {
  color: #6c757d !important;
}

img {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

button {
  font-size: 1rem;
  padding: 10px 20px;
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

.container {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center; /* 세로 및 가로 중앙 정렬 */
}

.board-content {
  width: 100%;
  max-width: 1000px;
  padding: 2rem;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.text-center {
  text-align: center;
}

.text-start {
  text-align: left;
}

.text-end {
  text-align: right;
}

hr {
  border-top: 1px solid #dee2e6;
}

hr.mb-4 {
  margin-top: 2rem;
  margin-bottom: 2rem;
}

p.fs-5 {
  font-size: 1.25rem;
  color: #6c757d;
}

.mb-4 {
  margin-bottom: 2rem !important;
}

.row.mb-4 {
  margin-bottom: 2rem;
}

.row .col {
  padding: 0;
}

/* 추가적인 구분을 위한 스타일링 */
.board-content {
  height: 100%; /* 게시판 높이를 100%로 늘려서 세로 공간 확보 */
  padding: 30px;
  overflow-y: auto;
  background-color: #f8f9fa;
}

.board-content hr {
  margin-top: 30px;
  margin-bottom: 30px;
  border-color: black;
}
</style>
