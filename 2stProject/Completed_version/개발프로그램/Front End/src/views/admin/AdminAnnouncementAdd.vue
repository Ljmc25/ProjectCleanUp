<template>
  <h3>공지사항</h3>
  <hr />

  <br />
  <br />

  <h3>공지사항 등록</h3>
  <hr style="width:70vw;"/>

  <div class="mb-3" style="width:70vw;">
    <label class="form-label"> 제목</label>
    <input
      type="text"
      class="form-control"
      placeholder="제목을 적어주세요"
      v-model="notice.title"
    />
  </div>

  <br />

  <div class="mb-3" style="width:70vw;">
    <label for="exampleFormControlTextarea1" class="form-label">본문</label>
    <textarea
      class="form-control"
      rows="3"
      v-model="notice.contents"
    ></textarea>
  </div>
  <div class="input-group" style="width:70vw;">
    <input
      type="file"
      class="form-control"
      aria-describedby="inputGroupFileAddon04"
      aria-label="Upload"
      ref="file"
      @change="select"
    />
    <button
      class="btn btn-outline-secondary"
      type="button"
      id="inputGroupFileAddon04"
    >
      파일 불러오기
    </button>
  </div>

  <br />
  <br />

  <div class="text-center" style="width:70vw;">
    <button type="button" class="btn btn-primary me-2" @click="save">
      등록
    </button>
    <a href="/admin/AdminAnouncement"
      ><button type="button" class="btn btn-danger">취소</button></a
    >
  </div>
</template>
<script>
import AnnouncementService from "@/services/basic/AnnouncementService";
export default {
  data() {
    return {
      email:"",
      notice: {
        title: "",
        contents: "",
        imageUrl: "",
        image: undefined,
        // TODO : Email 나중에 소프트 코딩하기
        writer: "",
      },
    };
  },
  methods: {
    select() {
      this.notice.image = this.$refs.file.files[0];
    },
    async save() {
      this.notice.writer = this.email;
      try {
        let response = await AnnouncementService.insert(this.notice);
        console.log(response.data);
        this.$router.push("/admin/AdminAnouncement");
      } catch (error) {
        this.notice.image = undefined;
        console.log(error);
      }
    },
  },
  mounted() {
    const userData = localStorage.getItem("user");
      if (userData) {
        const parsedData = JSON.parse(userData);
        if (parsedData.email) {
          this.email = parsedData.email; // email 값을 업데이트
        } else {
          console.warn("Email 값이 존재하지 않습니다.");
        }
      } else {
        console.warn("localStorage에서 'user' 데이터를 찾을 수 없습니다.");
      }
  },
};
</script>
<style></style>
