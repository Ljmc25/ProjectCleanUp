<template lang="">
  <h3>공지사항</h3>
  <hr />

  <br />
  <br />

  <h3>검색</h3>
  <br />

  <table class="table table-bordered table-sm" style="width: 70vw">
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
                v-model="searchCriteria"
                @change="changeIndex"
              >
                <option value="all" selected>검색어 선택</option>
                <option value="title">제목</option>
                <option value="insertTime">작성일</option>
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

  <div class="row">
    <div class="col">
      <p class="fs-3 text">공지사항 리스트</p>
    </div>
    <div class="col">
      <p class="d-grid gap-2 d-md-flex justify-content-md-end">
        <a href="/admin/AdminAnouncementAdd">
          <button
            class="btn btn-primary me-md-2"
            type="button"
            style="margin-left: -17vw"
          >
            등록하기
          </button></a
        >
      </p>
    </div>
  </div>

  <hr style="width: 70vw" />

  <table class="table" style="width: 70vw">
    <thead>
      <tr class="table-secondary">
        <th scope="col">번호</th>
        <th scope="col">제목</th>
        <th scope="col">작성자</th>
        <th scope="col">작성일</th>
        <th scope="col">수정일</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(data, index) in notices" :key="index">
        <td>{{ calculateIndex(index) }}</td>
        <td>
          <router-link :to="'/admin/AdminAnouncement/' + data.noid">{{
            data.title
          }}</router-link>
        </td>
        <td>{{ data.writer }}</td>
        <td>{{ data.insertTime }}</td>
        <td>{{ data.updateTime }}</td>
      </tr>
    </tbody>
  </table>

  <br />
  <br />
  <!-- 페이지네이션 -->
  <div
    class="text-center d-flex justify-content-center"
    style="margin-left: -80px"
  >
    <b-pagination
      v-model="pageIndex"
      :total-rows="totalCount"
      :per-page="recordCountPerPage"
      @click="getNotice"
    ></b-pagination>
  </div>
</template>

<script>
import AnnouncementService from "@/services/basic/AnnouncementService";
export default {
  data() {
    return {
      pageIndex: 1, // 현재페이지번호
      totalCount: 0, // 전체개수
      recordCountPerPage: 5, // 화면에 보일 개수
      searchKeyword: "", // 검색어
      searchCriteria: "all",
      notices: [],
      searchpageIndex: 1,
    };
  },
  computed: {
    pagedItems() {
      const start = (this.pageIndex - 1) * this.recordCountPerPage;
      const end = start + this.recordCountPerPage;
      return this.items.notices(start, end);
    },
  },
  methods: {
  calculateIndex(index) {
    return this.totalCount -(this.pageIndex - 1) * this.recordCountPerPage - index;
  },

  async getNotice() {
    try {
      let response = await AnnouncementService.getAll(
        this.searchCriteria,
        this.searchKeyword,
        this.pageIndex - 1, // 페이지 인덱스에 맞춰 데이터를 요청
        this.recordCountPerPage
      );
      const { results, totalCount } = response.data;
      console.log(response.data); // 디버깅
      this.notices = results;
      this.totalCount = totalCount;
    } catch (error) {
      console.log(error);
    }
    console.log(`Fetching data for page ${this.pageIndex}`);
  },

  changeIndex() {
    // 페이지 인덱스 변경 시, 데이터 새로 불러오기
    this.getNotice();
  },

  search() {
    // 검색 버튼 클릭 시, 데이터 새로 불러오기
    this.pageIndex = 1; // 검색 시 첫 페이지로 이동
    this.getNotice();
  },

  // 초기화 버튼을 클릭했을 때 호출되는 메소드
  resetSearch() {
    this.searchKeyword = ""; // 검색어 초기화
    this.searchCriteria = "all"; // 검색 기준 초기화
    document.getElementById("search-select").selectedIndex = 0; // 검색어 선택 초기화
    this.pageIndex = 1; // 초기화 시 첫 페이지로 이동
    this.getNotice();
  },
  },
  mounted() {
    this.getNotice();
  },
};
</script>
<style></style>
