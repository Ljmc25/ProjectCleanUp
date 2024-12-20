<template>
  <div class="productMain">
    <!-- 페이지 타이틀 -->
    <div class="d-flex justify-content-center align-items-center mainText">
      <h3> 두루마기/도포</h3>
    </div>

    <!-- 정렬 및 총 개수 -->
    <div class="d-flex justify-content-between align-items-center">
      <p class="totalProductNum">
        총 <span style="font-weight: bold; color: black">{{ totalCount }}</span>개의 상품이 있습니다
      </p>
      <div class="dropdown">
        <button
          class="btn btn-snow dropdown-toggle"
          type="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          정렬
        </button>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="#" @click="changeSortType('newest')">신상품</a></li>
          <li><a class="dropdown-item" href="#" @click="changeSortType('name')">상품명</a></li>
          <li><a class="dropdown-item" href="#" @click="changeSortType('low-price')">낮은 가격</a></li>
          <li><a class="dropdown-item" href="#" @click="changeSortType('high-price')">높은 가격</a></li>
        </ul>
      </div>
    </div>

    <!-- 상품 페이지 본문 -->
    <div class="container text-center">
      <div class="row">
        <div
          class="col-3"
          v-for="(data, index) in sortedProducts"
          :key="index"
        >
          <div class="image-container">
            <router-link :to="'/basic/product/ProductDetail/' + data.product.prid" class="routerLink">
              <!-- 이미지 렌더링 -->
              <img
                :src="data.image || '/path/to/default-image.jpg'"
                :alt="data.product.name"
                class="product-image"
              />
              <p class="below-text title">{{ data.product.name }}</p>
              <p class="below-text title">{{ data.product.gender }}</p>
            </router-link>
            <p class="below-text salePrice">
              <span style="font-weight: bold">판매가 :</span>
              <span style="font-weight: bold">{{ data.product.price }}원</span>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "@/assets/css/Product/ProductMainView.css";
import ProductService from "@/services/basic/ProductService";

export default {
  data() {
    return {
      pageIndex: 1,
      totalCount: 0,
      recodeCountPerPage: 5,
      searchKeyword: "",
      product: [], // 백엔드 응답 구조를 처리할 수 있도록 변경
      sortType: "", // 정렬 기준
    };
  },
  computed: {
    // 정렬된 상품 목록
    sortedProducts() {
      let sorted = [...this.product]; // 원본 배열을 복사

      if (this.sortType === "newest") {
        sorted.sort((a, b) => new Date(b.product.dateAdded) - new Date(a.product.dateAdded));
      } else if (this.sortType === "name") {
        sorted.sort((a, b) => a.product.name.localeCompare(b.product.name));
      } else if (this.sortType === "low-price") {
        sorted.sort((a, b) => a.product.price - b.product.price);
      } else if (this.sortType === "high-price") {
        sorted.sort((a, b) => b.product.price - a.product.price);
      }

      return sorted;
    },
  },
  methods: {
    // getProductByCategory 메소드 정의
    async getProductByCategory() {
      try {
        // "두루마기" 카테고리를 파라미터로 전달
        const response = await ProductService.getAllByCategory(
          "두루마기",  // 카테고리명 "두루마기"를 전달
          this.searchKeyword,
          this.pageIndex - 1,
          this.recodeCountPerPage
        );
        const { results } = response.data;

        console.log("백엔드 응답 데이터:", response.data);

        // prid를 기준으로 중복을 제거하여 새로운 상품 목록 생성
        const uniqueProducts = [];
        const productIds = new Set();

        results.forEach(product => {
          const prid = product.product.prid;
          if (!productIds.has(prid)) {
            productIds.add(prid);
            uniqueProducts.push(product);
          }
        });

        // 중복되지 않는 상품만 추가
        this.product = uniqueProducts;  // 중복 제거된 상품 리스트
        this.totalCount = uniqueProducts.length;  // 중복 제거된 상품의 개수
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    },

    // 드롭다운에서 선택한 정렬 기준 처리
    changeSortType(sortType) {
      this.sortType = sortType; // 선택된 정렬 기준 설정
    },
  },
  mounted() {
    // getProductByCategory 호출
    this.getProductByCategory();
  },
};
</script>
