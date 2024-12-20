<template>
  <div>
    <h3>전체 상품 관리</h3>
    <hr />
    <br />
    <br />

    <h3>상품 검색</h3>

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
                  v-model="searchCriteria"
                  @change="changeIndex"
                >
                  <option value="all" selected>검색어 선택</option>
                  <option value="name">상품명</option>
                  <option value="code">상품코드</option>
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
      <button
        type="button"
        class="btn btn-secondary btn-sm"
        @click="resetSearch"
      >
        초기화
      </button>
    </div>

    <br />
    <br />

    <div class="row">
      <div class="col">
        <p class="fs-3 text">상품 리스트</p>
      </div>
      <div class="col">
        <p class="d-grid gap-2 d-md-flex justify-content-md-end">
          <a href="/admin/AdminMerchandiseAdd"
            ><button class="btn btn-primary me-md-2" type="button" style="margin-left: -17vw;">
              상품 등록
            </button></a
          >
        </p>
      </div>
    </div>
    <hr style="width:70vw;">

    <table class="table" style="width:70vw;">
      <thead>
        <tr class="table-secondary">
          <th scope="col">번호</th>
          <th scope="col">상품코드</th>
          <th scope="col">메인 이미지</th>
          <th scope="col">상품명</th>
          <th scope="col">카테고리</th>
          <th scope="col">제품 성별</th>
          <th scope="col">단일가격</th>
          <th scope="col">등록날짜</th>
          <th scope="col">수정날짜</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(data, index) in product" :key="index">
          <td>{{ calculateIndex(index) }}</td>
          <td>
            <router-link :to="'/admin/AdminMerchandise/' + data.product.prid">{{
              data.product.code
            }}</router-link>
          </td>
          <td><img :src="data.image" alt="이미지" style="width: 40px" /></td>
          <td>{{ data.product.name }}</td>
          <td>{{ data.product.karegorie }}</td>
          <td>{{ data.product.gender }}</td>
          <td>{{ data.product.price }}</td>
          <td>{{ data.product.insertTime }}</td>
          <td>{{ data.product.updateTime }}</td>
        </tr>
      </tbody>
    </table>

    <br />
    <br />

    <!-- 페이지네이션 -->
    <div class="text-center d-flex justify-content-center" style="margin-left: -80px;">
      <b-pagination
        v-model="pageIndex"
        :total-rows="totalCount"
        :per-page="recordCountPerPage"
        @click="getProduct"
      ></b-pagination>
    </div>

    <br />
    <br />
  </div>
</template>
<script>
import ProductService from "@/services/basic/ProductService";
export default {
  data() {
    return {
      pageIndex: 1, // 현재페이지번호
      totalCount: 0, // 전체개수
      recordCountPerPage: 5, // 화면에 보일 개수
      searchKeyword: "", // 검색어
      searchCriteria: "all",
      product: [],
      searchpageIndex: 1,
    };
  },
  computed: {
    pagedItems() {
      const start = (this.pageIndex - 1) * this.recordCountPerPage;
      const end = start + this.recordCountPerPage;
      return this.items.product(start, end);
    },
  },
  methods: {
    calculateIndex(index) {
      return (this.pageIndex - 1) * this.recordCountPerPage + index + 1;
    },
    async getProduct() {
      try {
        let response = await ProductService.getAllAdmin(
          this.searchCriteria,
          this.searchKeyword,
          this.pageIndex - 1,
          this.recordCountPerPage
        );
        const { results, totalCount } = response.data;
        console.log(response.data); // 디버깅
        this.product = results;
        this.totalCount = totalCount;
      } catch (error) {
        console.log(error);
        console.log(`Fetching data for page ${this.pageIndex}`);
        // 필요한 경우, 서버에서 데이터를 다시 로드
      }
    },

    changeIndex() {
      this.searchpageIndex = 1;
    },

    search() {
      this.pageIndex = this.searchpageIndex;
      this.getProduct();
    },

    // 초기화 버튼을 클릭했을 때 호출되는 메소드
    resetSearch() {
      this.searchKeyword = ""; // 검색어 초기화
      this.searchCriteria = "all"; // 검색 기준 초기화
      document.getElementById("search-select").selectedIndex = 0; // 검색어 선택 초기화
    },
  },

  mounted() {
    this.getProduct();
  },
};
</script>
<style></style>
