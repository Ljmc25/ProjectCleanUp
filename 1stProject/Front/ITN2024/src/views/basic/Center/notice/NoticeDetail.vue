<template>
  <div class="container mb-3">
    <h2 class="text-center">공지사항</h2>
  </div>
  <div class="bold-line"></div>

  <div>
    <div>
      
      
      <div class="container text-start">
        <div class="row">
          <h5 class="title">{{ notice.title }}</h5>
          <div class="col-1 .col-md-4">
            <p class="fw-bold">{{ notice.writer }}  </p>
          </div>
          <div class="col-2 .col-md-4">
            <p class="insertTime">&nbsp; &nbsp; &nbsp;{{ notice.insertTime }}</p>
          </div>          
        </div>
      </div>
    </div>
  </div>
  <hr />
  <br>

  <div class="container">
    <div class="text-center ">
      <img :src="notice.imageUrl" class="" alt="이미지 " />
      </div>
      <br>
      <br>

<div class="text-center">
     <p> {{ notice.contents }}</p>
</div>

  </div>
</template>
<script>
import NoticeService from "@/services/basic/NoticeService";
export default {
  data() {
    return {
      notice: {
        noid: "",
        title: "",
        contents: "",
        imageUrl: "",
        writer: "ITN2024@gmail.com",
        insertTime: "",
        notices: [], // 빈배열(json)
      },
    };
  },
  methods: {
    // 함수작성
    // TODO: 상세조회
    async getDetail(noid) {
      try {
        let response = await NoticeService.get(noid);
        console.log(response.data);
        this.notice = response.data;
      } catch (error) {
        console.log(error);
      }
    },
    // TODO: 수정
    async update() {
      try {
        // let temp = {
        //   dno: this.dept.dno,
        //   dname: this.dept.dname,
        //   loc: this.dept.loc,
        // };

        let response = await NoticeService.update(
          this.notice.noid,
          this.notice
        );
        console.log(response.data); // 디버깅
        this.$router.push("/basic/notice"); // 전체조회 강제이동: /dept
      } catch (error) {
        console.log(error);
      }
    },
    // TODO : 삭제
    async remove() {
      try {
        // let temp = {
        //   dno: this.dept.dno,
        //   dname: this.dept.dname,
        //   loc: this.dept.loc,
        // };

        let response = await NoticeService.remove(
          this.notice.noid,
          this.notice
        );
        console.log(response.data);
        this.$router.push("/basic/notice");
      } catch (error) {
        console.log(error);
      }
    },
  },
  // 화면이 뜰때 실행하는 함수
  mounted() {
    // 상세조회 :dno(부서번호:기본키)값
    //TODO: 사용법) this.$route.params.변수명
    // => router/index.js: 메뉴등록 : /dept/:dno */
    this.getDetail(this.$route.params.noid);
  },
};




</script>
<style>
.bold-line {
  border: 1px solid black;
  margin: 20px 0;
}

.fw-bold {
  font-size: 12px;
}

.insertTime {
  font-size: 12px;

}

</style>
