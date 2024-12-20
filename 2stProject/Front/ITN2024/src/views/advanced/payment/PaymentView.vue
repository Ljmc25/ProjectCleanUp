<template>
  <div class="payment-container">
    <!-- 왼쪽 섹션: 주문자 및 수취인 정보 -->
    <div class="left-section">
      <h3>주문자 정보</h3>
      <div class="form-group">
        <label for="ordererName">이름</label>
        <input type="text" id="ordererName" class="form-control" v-model="orderer.name" />
      </div>
      <div class="form-group">
        <label for="ordererPhone">전화번호</label>
        <input type="text" id="ordererPhone" class="form-control" v-model="orderer.phone" />
      </div>
      <div class="form-group">
        <label for="ordererAddress">주소</label>
        <input type="text" id="ordererAddress" class="form-control" v-model="orderer.address" />
      </div>

      <h3>수취인 정보</h3>
      <div class="form-group">
        <label for="recipientName">이름</label>
        <input type="text" id="recipientName" class="form-control" v-model="recipient.name" />
      </div>
      <div class="form-group">
        <label for="recipientPhone">전화번호</label>
        <input type="text" id="recipientPhone" class="form-control" v-model="recipient.phone" />
      </div>
      <div class="form-group">
        <label for="recipientAddress">주소</label>
        <input type="text" id="recipientAddress" class="form-control" v-model="recipient.address" />
      </div>
    </div>

    <!-- 오른쪽 섹션: 상품 정보 및 결제 버튼 -->
    <div class="right-section">
      <h3>상품 정보</h3>
      <div v-if="items.length > 0">
        <div
          v-for="(item, index) in items"
          :key="index"
          class="product-item"
        >
          <img :src="item.image" alt="상품 이미지" class="product-image" />
          <div class="product-details">
            <p>이름: {{ item.name }}</p>
            <p>사이즈: {{ item.size }}</p>
            <p>개수: {{ item.quantity }}</p>
            <p>가격: {{ item.price }}원</p>
          </div>
        </div>
        <h4>총 결제 금액: {{ totalPrice }}원</h4>
      </div>
      <p v-else>상품 정보가 없습니다.</p>
      <button class="btn btn-primary" :disabled="isLoading" @click="processPayment">
        {{ isLoading ? "결제 중..." : "토스페이로 결제하기" }}
      </button>
    </div>
  </div>
</template>


<script>
import PaymentService from "@/services/advanced/PaymentService";

export default {
  data() {
    return {
      orderer: {
        name: "",
        phone: "",
        address: "",
      },
      recipient: {
        name: "",
        phone: "",
        address: "",
      },
      items: [], // 상품 정보 리스트
      isLoading: false, // 로딩 상태
      error: null, // 에러 메시지
    };
  },
  computed: {
    totalPrice() {
      // 상품 총합 금액 계산
      return this.items.reduce((sum, item) => sum + item.price * item.quantity, 0);
    },
  },
  methods: {
    async fetchItems() {
      // 상품 전체 조회
      this.isLoading = true;
      this.error = null;

      try {
        const response = await PaymentService.getAll("", 1, 10, "");
        this.items = response.data.items; // 데이터 구조에 맞게 매핑
      } catch (err) {
        this.error = "상품 정보를 불러오는 데 실패했습니다.";
        console.error("fetchItems 에러:", err);
      } finally {
        this.isLoading = false;
      }
    },
    async processPayment() {
      // 결제 데이터 전송
      const paymentData = {
        orderer: this.orderer,
        recipient: this.recipient,
        items: this.items,
        totalPrice: this.totalPrice,
      };

      this.isLoading = true;
      this.error = null;

      try {
        const response = await PaymentService.insert(paymentData);
        if (response.status === 200) {
          alert("결제가 완료되었습니다.");
          window.location.href = "/payment/success";
        } else {
          throw new Error("결제 실패");
        }
      } catch (err) {
        this.error = "결제 처리 중 오류가 발생했습니다.";
        console.error("processPayment 에러:", err);
      } finally {
        this.isLoading = false;
      }
    },
  },
  async mounted() {
    // 컴포넌트가 로드될 때 상품 전체 조회 호출
    await this.fetchItems();
  },
};
</script>

<style scoped>
.payment-container {
  display: flex;
  justify-content: space-between;
  padding: 20px;
}

.left-section,
.right-section {
  width: 48%;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.form-group {
  margin-bottom: 15px;
}

.product-item {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
  border-bottom: 1px solid #ddd;
  padding-bottom: 15px;
}

.product-image {
  width: 80px;
  height: 80px;
  margin-right: 15px;
}

.product-details p {
  margin: 0;
  font-size: 14px;
}

.btn {
  width: 100%;
  margin-top: 20px;
}

.loading-spinner {
  text-align: center;
  color: #007bff;
}

.error-message {
  color: red;
  text-align: center;
  margin-bottom: 10px;
}
</style>
