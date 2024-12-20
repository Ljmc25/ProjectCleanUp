<template>
  <h3>1:1 문의</h3>
  <hr />

  <br />
  <br />

  <h3>검색</h3>
  <br />

  <table class="table table-bordered table-sm" style="width:70vw;">
    <tbody>
      <tr>
        <th scope="row" class="text-center align-middle">검색어</th>
        <td>
          <div class="row align-items-center">
            <div class="col-auto">
              <select
                class="form-select form-select"
                style="width: 200px"
                aria-label=".form-select example"
                id="search-select"
                v-model="searchCriteria" @change = "changeIndex"
              >
                <option selected>검색어 선택</option>
                <option value="title">제목</option>
                <option value="writer">작성자</option>
              </select>
            </div>
            <div class="col">
              <input
                type="text"
                class="form-control"
                placeholder="검색어를 입력하세요"
                v-model="searchKeyword"
              />
            </div>
          </div>
        </td>
      </tr>
    </tbody>
  </table>

  <div class="text-center">
    <button type="button" class="btn btn-primary btn-sm me-2" @click="search">
      검색
    </button>
    <button type="button" class="btn btn-secondary btn-sm" @click="resetSearch">
      초기화
    </button>
  </div>

  <br />
  <br />

  <div>
    <span class="fs-3 text">1:1 문의 리스트</span>
  </div>
  <hr style="width:70vw;"/>

  <table class="table" style="width:70vw;">
    <thead>
      <tr class="table-secondary">
        <th scope="col">번호</th>
        <th scope="col">제목</th>
        <th scope="col">문의내용</th>
        <th scope="col">작성자</th>
        <th scope="col">등록날짜</th>
        <th scope="col">수정날짜</th>
        <th scope="col">답변</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(data, index) in inquiries" :key="index">
        <td>{{ calculateIndex(index) }}</td>
        <td>
          <router-link :to="'/admin/AdminFaqDetailView/' + data.iqid">{{
            data.title
          }}</router-link>
        </td>
        <td>{{ data.contents }}</td>
        <td>{{ data.writer }}</td>
        <td>{{ data.insertTime }}</td>
        <td>{{ data.updateTime }}</td>
        <td>{{ data.anwser }}</td>
      </tr>
    </tbody>
  </table>

  <br />
  <br />
  <!-- 페이지네이션 -->
  <div class="text-center d-flex justify-content-center">
    <b-pagination
      v-model="pageIndex"
      :total-rows="totalCount"
      :per-page="recordCountPerPage"
      @click="getInquiry"
    ></b-pagination>
  </div>

  <br />
  <br />
</template>
<script>
import InquiryService from "@/services/basic/InquiryService";
export default {
  data() {
    return {
      pageIndex: 1, // 현재페이지번호
      totalCount: 0, // 전체개수
      recordCountPerPage: 5, // 화면에 보일 개수
      searchKeyword: "", // 검색어
      searchCriteria: "all",
      inquiries: [],
      searchpageIndex : 1,
    };
  },
  computed: {
    pagedItems() {
      const start = (this.pageIndex - 1) * this.recordCountPerPage;
      const end = start + this.recordCountPerPage;
      return this.items.inquiries(start, end);
    },
  },
  methods: {
    calculateIndex(index) {
      return (this.pageIndex - 1) * this.recordCountPerPage + index + 1;
    },

    async getInquiry() {
      try {
        let response = await InquiryService.getadmin(
          this.searchCriteria,
          this.searchKeyword,
          this.pageIndex - 1,
          this.recordCountPerPage
        );
        const { results, totalCount } = response.data;
        console.log(response.data); // 디버깅
        this.inquiries = results;
        this.totalCount = totalCount;
      } catch (error) {
        console.log(error);
      }
      console.log(`Fetching data for page ${this.pageIndex}`);
      // 필요한 경우, 서버에서 데이터를 다시 로드
    },

    changeIndex(){
      this.searchpageIndex = 1;
    },

    search(){
      this.pageIndex = this.searchpageIndex;
      this.getInquiry();
    },

    // 초기화 버튼을 클릭했을 때 호출되는 메소드
    resetSearch() {
      this.searchKeyword = ""; // 검색어 초기화
      this.searchCriteria = "all"; // 검색 기준 초기화
      document.getElementById("search-select").selectedIndex = 0; // 검색어 선택 초기화
    },
  },
  mounted() {
    this.getInquiry();
  },
};
</script>
<style></style>
