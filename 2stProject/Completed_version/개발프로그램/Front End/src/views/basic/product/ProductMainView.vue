<template>
  <div class="productMain">
    <!-- 페이지 타이틀 -->
    <div class="d-flex justify-content-center align-items-center mainText">
      <h3>전체 상품</h3>
    </div>

    <!-- 정렬 및 총 개수 -->
    <div class="d-flex justify-content-between align-items-center">
      <p class="totalProductNum">
        총 <span style="font-weight: bold; color: black">{{ displayedTotalCount }}</span>개의 상품이 있습니다
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
          <li><a class="dropdown-item" href="#" @click="changeSortType('sales')">추천상품</a></li> <!-- 추천상품 추가 -->
        </ul>
      </div>
    </div>

    <!-- 추천 상품 -->
    <div class="d-flex justify-content-center align-items-center mainText" v-if="sortType === 'sales'">
      <h4>추천 상품</h4>
    </div>
    <div class="container text-center" v-if="sortType === 'sales'">
      <div class="row">
        <div
          class="col-3"
          v-for="(data, index) in recommendedProducts"
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

    <!-- 상품 페이지 본문 -->
    <div class="container text-center" v-if="sortType !== 'sales'">
      <div class="row">
        <div
          class="col-3"
          v-for="(data, index) in product"
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
import '@/assets/css/Product/ProductMainView.css';
import ProductService from '@/services/basic/ProductService';

export default {
  data() {
    return {
      pageIndex: 1,
      totalCount: 0,
      recodeCountPerPage: 5,
      searchKeyword: "",
      product: [], // 백엔드 응답 구조를 처리할 수 있도록 변경
      recommendedProducts: [], // 추천상품
      sortType: "", // 정렬 기준
    };
  },
  computed: {
    // 추천 상품일 때 표시할 상품 개수를 반환하는 computed 속성
    displayedTotalCount() {
      return this.sortType === 'sales' ? this.recommendedProducts.length : this.totalCount;
    },
  },
  methods: {
    async getProduct() {
      try {
        const response = await ProductService.getAll(
          this.searchKeyword,
          this.pageIndex - 1,
          this.recodeCountPerPage
        );
        const { results, totalCount } = response.data;

        console.log("백엔드 응답 데이터:", response.data);

        // 중복된 상품을 방지하기 위해 기존 상품 목록과 새로운 목록을 비교
        const newProducts = results.filter(
          (newProduct) =>
            !this.product.some((existingProduct) => existingProduct.product.prid === newProduct.product.prid)
        );

        // 중복되지 않는 상품만 추가
        this.product = [...this.product, ...newProducts];
        this.totalCount = totalCount;

        // 추천상품 정렬
        this.sortProducts();
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    },

    // 정렬 기능 구현
    sortProducts() {
      if (this.sortType === "sales") {
        // SALES 값이 높은 순으로 정렬
        this.recommendedProducts = [...this.product]
          .sort((a, b) => b.product.sales - a.product.sales)
          .slice(0, 5); // 예시로 상위 5개 상품만 추천상품으로 표시
      } else if (this.sortType === "newest") {
        this.product.sort((a, b) => new Date(b.product.dateAdded) - new Date(a.product.dateAdded));
      } else if (this.sortType === "name") {
        this.product.sort((a, b) => a.product.name.localeCompare(b.product.name));
      } else if (this.sortType === "low-price") {
        this.product.sort((a, b) => a.product.price - b.product.price);
      } else if (this.sortType === "high-price") {
        this.product.sort((a, b) => b.product.price - a.product.price);
      }
    },

    // 드롭다운에서 선택한 정렬 기준 처리
    changeSortType(sortType) {
      this.sortType = sortType; // 선택된 정렬 기준 설정
      this.product = []; // 기존 상품 목록 초기화
      this.getProduct(); // 정렬된 상품 목록 다시 불러오기
    },
  },
  mounted() {
    this.getProduct();
  },
};
</script>
