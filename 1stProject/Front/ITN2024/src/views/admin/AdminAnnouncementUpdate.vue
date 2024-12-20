<template>
  <h3>공지사항</h3>
  <hr />

  <br />
  <br />

  <h3>공지사항 수정</h3>
  <hr style="width:70vw;"/>

  <div class="mb-3" style="width:70vw;">
    <label class="form-label"> 제목</label>
    <input
      type="text"
      class="form-control"
      placeholder="제목을 적어주세요"
      v-model="notices.title"
    />
  </div>

  <br />

  <div class="mb-3" style="width:70vw;">
    <label for="exampleFormControlTextarea1" class="form-label">본문</label>
    <textarea
      class="form-control"
      rows="3"
      v-model="notices.contents"
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

  <br  />
  <br />

  <div class="text-center" style="width:70vw;">
    <button type="button" class="btn btn-primary me-2" @click="update">
      수정하기
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
      email: "",
      notices: {
        noid: this.$route.params.noid,
        title: "",
        contents: "",
        imageUrl: "",
        image: undefined,
        // TODO : Email 나중에 소프트 코딩하기
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
    select() {
      this.notices.image = this.$refs.file.files[0];
    },
    async update() {
      this.notices.writer = this.email;
      try {
        let response = await AnnouncementService.update(
          this.notices.noid,
          this.notices
        );
        console.log(response.data);
        this.notices = response.data;
        alert("수정되었습니다");
        this.$router.push("/admin/AdminAnouncement");
      } catch (error) {
        this.notices.image = undefined;
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

    this.getDetail(this.$route.params.noid);
  },
};
</script>
<style></style>
