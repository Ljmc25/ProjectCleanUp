<template>
  <div>
    <div v-if="!productData">
      <p>로딩 중...</p>
    </div>

    <div v-else>
      <!-- 제품 상세 페이지 상단 -->
      <div class="product-detail-container">
        <!-- 제품 이미지와 슬라이드 -->
        <div class="product-image-carousel">
          <div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
              <div v-for="(image, index) in productData.images" :key="index" class="carousel-item"
                :class="{ active: index === 0 }">
                <img :src="image" class="d-block w-100 product-image" alt="Product image" />
              </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleSlidesOnly"
              data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleSlidesOnly"
              data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
        </div>

        <!-- 제품 정보 영역 -->
        <div class="product-info">
          <h2>{{ productData?.product?.name }}</h2>
          <hr class="separator" />
          <p class="price">
            <span class="original-price">정상가:
              {{ formatPrice(productData?.product?.price || 0) }}원</span>
          </p>
          <div class="size-selection">
            <label for="size" class="size-label">사이즈 선택</label>
            <select id="size" v-model="selectedSize" class="form-select" @change="addSize">
              <option disabled value="default">
                - [필수] 옵션을 선택해주세요 -
              </option>
              <option v-for="size in sizes" :key="size" :value="size">
                {{ size }}
              </option>
            </select>
          </div>
          <p style="font-size: 12px">(최소 주문 수량 1개 이상)</p>
          <hr class="separator" />

          <!-- 선택된 사이즈 정보 -->
          <div v-for="(item, index) in selectedSizes" :key="index" class="size-info">
            <div class="info-row" style="font-size: 12px">
              <p class="product-name">
                {{ productData.product.name }}
                <br />
                <span class="size">{{ item.size }}</span>
              </p>
              <div class="quantity-controls">
                <button @click="decreaseQuantity(index)" class="quantity-btn">
                  -
                </button>
                <span class="quantity">{{ item.quantity }}</span>
                <button @click="increaseQuantity(index)" class="quantity-btn">
                  +
                </button>
              </div>
              <p class="subPrice">
                {{ formatPrice(productData.product.price * item.quantity) }}원
              </p>
              <button @click="removeSize(index)" class="remove-btn">x</button>
            </div>
          </div>

          <hr class="separator" />

          <!-- 총 합계 -->
          <div class="total" style="font-size: 12px">
            <span>
              TOTAL
              <span style="color: gray"> ({{ selectedSizes.length }}개) </span>
            </span>
            <span id="total-price">
              {{ formatPrice(calculateTotal()) }}원
              <span style="font-size: 10px; color: gray"> (개수) </span>
            </span>
          </div>

          <!-- 구매 옵션 -->
          <div class="buy-options">
            <button class="btn btn-snow" style="border: 1px solid gray" @click="addToCart">
              장바구니
            </button>
          </div>
        </div>
      </div>

      <hr class="separator2" />

      <!-- 제품 상세 페이지 하단 -->
      <div class="container d-flex" style="justify-content: center; align-items: center">
        <div class="btn-group" role="group" aria-label="Basic outlined example">
          <button type="button" class="btn proBt" :class="{ active: activeTab === 'details' }"
            @click="setActiveTab('details')">
            상세정보
          </button>

          <button type="button" class="btn proBt" :class="{ active: activeTab === 'refund' }"
            @click="setActiveTab('refund')">
            배송/교환/환불 안내
          </button>
        </div>
      </div>

      <hr class="separator2" />

      <!-- 상세페이지 탭 별 화면 구성 -->
      <div class="container d-flex" style="justify-content: center; align-items: center">
        <!-- 상세정보 -->
        <div v-if="activeTab === 'details'" class="tab-content">
          <div class="row">
            <div class="col-md-6">
              <h2 class="bottom-title">{{ productData?.product?.name }}</h2>
              <h2 class="bottom-title">온새미로 모델분의 착용 사진입니다.</h2>
              <p class="bottom-text">{{ productData?.product?.description }}</p>
            </div>
          </div>
          <div v-if="productData.images && productData.images.length">
            <div class="row">
              <div v-for="(image, index) in productData.images" :key="index" class="col-md-4">
                <img :src="image" alt="" class="bottom-image" />
              </div>
            </div>
          </div>
        </div>

        <!-- 배송/교환/환불 안내 -->
        <div v-if="activeTab === 'refund'" class="tab-content">
          <img src="@/assets/images/fix/refundInfo.png" alt="" />
        </div>
      </div>
    </div>
        <img
      v-if="showButton"
      :src="buttonImage"
      class="scroll-button"
      :alt="buttonAltText"
      @click="scrollToTarget"
    />
  </div>
</template>

<script>
/* global bootstrap */
import ProductService from "@/services/basic/ProductService";
import CartService from "@/services/advanced/CartService";
import "@/assets/css/Product/ProductDetails.css";

export default {
  data() {
    return {
      productData: null,
      sizes: ["M", "L", "XL"],
      selectedSize: "default",
      selectedSizes: [],
      activeTab: "details",
      quantity: 1,
      email: "",
      showButton: false, // 버튼 표시 여부
      scrollPosition: 0, // 현재 스크롤 위치
      buttonImage: require("@/assets/images/fix/downArrow.png"), // 초기 버튼 이미지
      buttonAltText: "맨 밑으로", // 초기 버튼 대체 텍스트
    };
  },
  methods: {
    copyObj(obj) {
      const result = {};

      for (let key in obj) {
        if (typeof obj[key] === 'object') {
          result[key] = this.copyObj(obj[key]);
        } else {
          result[key] = obj[key];
        }
      }

      return result;
    },
    setActiveTab(tabName) {
      this.activeTab = tabName;
    },
async addToCart() {
  let CopyselectedSizes = this.copyObj(this.selectedSizes);
  
  for (let i = 0; i < Object.keys(CopyselectedSizes).length; i++) {
    CopyselectedSizes[i].prid = this.$route.params.prid;
    CopyselectedSizes[i].color = 'BLUE';
    CopyselectedSizes[i].email = this.email;
    CopyselectedSizes[i].siid = CopyselectedSizes[i].size;
    delete CopyselectedSizes[i].size;
    delete CopyselectedSizes[i].price;
    delete CopyselectedSizes[i].originalPrice;

    try {
      let response = await CartService.insert(CopyselectedSizes[i]);
      console.log(response.data);
    } catch {
      console.log("정상적으로 결제가 되지 않았습니다.");
      return false;
    }
  }

  // 장바구니로 이동할지 계속 쇼핑할지 선택하는 팝업
  if (window.confirm("제품이 장바구니에 추가되었습니다. 장바구니로 이동하시겠습니까?")) {
    // 장바구니로 이동
    this.$router.push({ name: "cart" });
  } else {
    // 계속 쇼핑
    console.log("계속 쇼핑합니다.");
  }
},
    fetchProductData() {
      const prid = this.$route.params.prid;
      ProductService.get(prid)
        .then((response) => {
          console.log("API 호출 성공, 전체 응답:", response.data); // 확인용
          this.productData = {
            product: response.data.product, // 제품 정보
            images: response.data.images, // 이미지 목록
          };
        })
        .catch((error) => {
          console.error("제품 정보를 불러오는 데 실패했습니다.", error);
        });
    },

    addSize() {
      if (
        this.selectedSize !== "default" &&
        !this.selectedSizes.some((size) => size.size === this.selectedSize)
      ) {
        let additionalPrice = 0;
        if (this.selectedSize === "L") {
          additionalPrice = 3000;
        } else if (this.selectedSize === "XL") {
          additionalPrice = 5000;
        }

        const originalPrice = this.productData.product.price;
        const price = originalPrice + additionalPrice;

        this.selectedSizes.push({
          size: this.selectedSize,
          quantity: 1,
          price: price,
          originalPrice: originalPrice,
        });

        this.selectedSize = "default"; // 사이즈 초기화
      }
    },
    increaseQuantity(index) {
      this.selectedSizes[index].quantity += 1;
    },
    formatPrice(price) {
      return price.toLocaleString("ko-KR", {
        style: "currency",
        currency: "KRW",
      });
    },
    decreaseQuantity(index) {
      if (this.selectedSizes[index].quantity > 1) {
        this.selectedSizes[index].quantity -= 1;
      }
    },
    removeSize(index) {
      this.selectedSizes.splice(index, 1);
    },
    calculateTotal() {
      return this.selectedSizes.reduce(
        (total, size) => total + size.price * size.quantity,
        0
      );
    },
  },
  mounted() {
    const userData = localStorage.getItem("user");

    if (userData) {
      const parsedData = JSON.parse(userData);
      if (parsedData.email) {
        this.email = parsedData.email;
      } else {
        console.warn("Email 값이 존재하지 않습니다.");
      }
    } else {
      console.warn("localStorage에서 'user' 데이터를 찾을 수 없습니다.");
    }

    this.fetchProductData();
    // Bootstrap Carousel 초기화
    const carouselElement = document.getElementById(
      "carouselExampleSlidesOnly"
    );
    if (carouselElement) {
      new bootstrap.Carousel(carouselElement);
    }
  },
};
</script>

<style scoped>
/* 제품 이미지에 외곽선과 둥글게 적용 */
.product-image {
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
.bottom-image {
  width: 100%; /* 이미지가 부모 요소에 맞게 크기를 자동으로 조정 */
  object-fit: contain; /* 이미지가 비율을 유지하면서 크기 조정 */
  margin-bottom: 50px; /* 이미지 아래쪽에 여백 추가 */
}
.tab-content .row {
  display: flex;
  flex-direction: column;
  /* 한 줄에 하나씩 나열 */
  align-items: center;
  /* 이미지 가운데 정렬 */
  gap: 20px;
  /* 이미지 간 간격 */
}

.tab-content .row .col-md-4 {
  width: 100%;
  /* 이미지가 한 줄에 차지하도록 설정 */
  max-width: 600px;
  /* 최대 너비 설정 */
}

.tab-content .row .col-md-4 img {
  width: 100%;
  /* 이미지 가로 100% */
  height: auto;
  /* 비율 유지 */
  border: 1px solid #ddd;
  /* 경계선 추가 */
  border-radius: 8px;
  /* 모서리 둥글게 */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
</style>