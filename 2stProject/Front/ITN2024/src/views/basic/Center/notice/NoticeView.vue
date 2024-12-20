// views-basic-dept/DeptView.vue // vueInit
<template>
  
  <div class="container mb-3">
    <h2 class="text-center">공지사항</h2>
  </div>


  <div>
    <hr class="hr2">
    <!-- TODO: 부트스트랩 테이블 -->
  <div class="main">
           
            <div class="search-container">
     
              <input
                type="text"
                class="form-control"
                placeholder="검색어"
                v-model="searchKeyword"
              />
              <button class="btn search-btn" type="button" @click="getNotice">
                검색
              </button>
            </div>

            <table class="table buy-list mt-3">
                <thead>
                  <tr>
                    <th scope="col">번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">작성일</th>
                  </tr>
                </thead>

                <tbody>
                  <tr v-for="(data, index) in notices" :key="index">
                    <td>{{ calculateIndex(index)}}</td>

                      <td><a :href="'/basic/notice/' +  data.noid">
                                {{ data.title }}
                            </a></td>

                    <td>{{data.writer }}</td>
                    <td>{{data.insertTime}}</td>
                  </tr>               
                </tbody>
              </table>
  </div>
     
  </div>

    <!-- TODO: 페이지 번호 : 부트스트랩뷰(페이지)  -->
                <div class=" page">
                 <b-pagination v-model="pageIndex" :total-rows="totalCount" :per-page="recordCountPerPage"
                @click="getNotice"></b-pagination>
            </div>

</template>
<script>
// 프론트 코딩 : 벡엔드(변수들 보내고) -> 벡엔드(sql 결과 리턴)
//              -> 프론트(json 반복문으로 화면출력)
// @ == src 폴더위치(절대경로)
import NoticeService from "@/services/basic/NoticeService";
export default {
  data() {
    return {
      pageIndex: 1, // 현재페이지번호
      totalCount: 0, // 전체개수
      recordCountPerPage: 7, // 화면에보일개수
      searchKeyword: "", // 검색어
      notices: [], // 빈배열(json)
    };
  },





  computed: {
    pagedItems() {
      const start = (this.pageIndex - 1) * this.recordCountPerPage;
      const end = start + this.recordCountPerPage;
      return this.items.slice(start, end);
    },
  },
  methods: {

      calculateIndex(index) {
      return this.totalCount -(this.pageIndex - 1) * this.recordCountPerPage - index;
    },

    // 함수 작성 : (비동기 코딩: async/await)
    // (복습) : 뷰함수 앞에 : async, axios 함수 앞에 : await
    async getNotice() {
      try {
        let response = await NoticeService.getAll(
          this.searchKeyword,
          this.pageIndex - 1,
          this.recordCountPerPage
        );
        // TODO: 벡엔드 전송되는것: results(배열), totalCount(총개수)
        const { results, totalCount } = response.data;
        console.log(response.data); // 디버깅
        this.notices = results;
        this.totalCount = totalCount;
      } catch (error) {
        console.log(error);
      }
      console.log(`Fetching data for page ${this.pageIndex}`);
      // 필요한 경우, 서버에서 데이터를 다시 로드
    },
  },
  // 화면이 뜰때 실행하는 함수
  mounted() {
    this.getNotice();
  },
};
</script>
<style>
@import '@/assets/css/Notice/notice.css';
.table{
  font-size: 12px;
}

</style>
