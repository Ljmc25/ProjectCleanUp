// views/advanced/gallery/GalleryView.vue // vueInit // 디자인 및 변수/함수
형태만 코딩
<template>
  <div class="text-center">
      <h2>장바구니</h2>     
    </div>

    <hr class="hr2">

  <div class="body">
    
    <div class="cart-wrapper">
      
      <div class="cart-container">
        <!-- 전체선택 -->
        <div class="form-check">
    <input class="form-check-input" type="checkbox" id="selectAll" 
           :checked="allSelected" @change="toggleSelectAll" />
    <label class="form-check-label" for="selectAll">전체 선택/해제</label>
  </div>
        <div>
          <div v-for="(data, index) in carties" :key="index">
            <table class="table">
              <thead class="table-secondary">
                <tr>
                  <th scope="col">일반상품</th>
                </tr>
              </thead>
            </table>
            
            <!-- 체크박스 -->
            <div class="cart-item">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" :value="data.caid" v-model="selectedItems"
                  :id="'flexCheckDefault' + index" />
                <label class="form-check-label" :for="'flexCheckDefault' + index"></label>
              </div>
              <img :src="data.fileUrl" alt="상품 이미지" />
              <div class="item-details">
                <h5>{{ data.name }}</h5>
                <p>{{ formatPrice(data.price) }}원</p>
              </div>
              
            </div>

            <div class="size">
              <div class="size-item">
                <table class="table">
                  <thead class="table-secondary">
                    <tr>
                      <th scope="col">{{data.siid}}사이즈옵션</th>
                    </tr>
                  </thead>
                </table>
                
                
                <!-- 사이즈옵션번호 -->
                <div class="quantity">
                  <!-- 증가 버튼 -->
                  <button class="increase" @click="updateQuantity(index, 1)">+</button>

                  <!-- 수량 입력 (readonly 상태) -->
                  <input type="number" :value="data.quantity" min="1" readonly />

                  <!-- 감소 버튼 -->
                  <button class="decrease" @click="updateQuantity(index, -1)">−</button>
                </div>

                <div class="summary-item">
                  <span></span>
                  <span>주문금액&nbsp;&nbsp; {{ formatPrice(data.orderAmount) }}원</span> <!-- 주문 금액 표시 -->
                </div>

                <div class="edit">
                  
                  <button class="remove-btn" type="button" @click="remove(data.caid)">
                    삭제
                  </button>
                </div>
              </div>
            </div>
            <br />
          </div>




          <hr />

          <button class="btn btn-outline-dark me-3" type="button" @click="removeSelected">
            선택 삭제
          </button>
          <button class="btn btn-outline-dark" type="button" @click="removeAll">
            전체 삭제
          </button>
        </div>
      </div>

      <div class="summary-container">
        <h2>결제 요약</h2>
        <div class="summary-item">
          <span>상품 금액:</span>
          <span id="product-total">{{ formatPrice(productTotal) }}원</span>
        </div>
        <div class="summary-item">
          <span>배송비:</span>
          <span id="shipping-cost">{{ formatPrice(shippingCost) }}원</span>
        </div>
        <div class="summary-item total">
          <span>총 결제 금액:</span>
          <span id="total-amount">{{ formatPrice(totalAmount) }}원</span>
        </div>
        <br />
        <button id="order" class="btn btn-outline-dark" @click="orderAll">전체상품주문</button>
        <br />
        <br />
        <button id="order" class="btn btn-outline-dark" @click="orderSelected">선택상품주문</button>
      </div>
    </div>
  </div>
</template>
<script>

import CartService from "@/services/advanced/CartService";
import PaymentService from "@/services/advanced/PaymentService";
import OrderService from "@/services/advanced/OrderService";
import ProductService from "@/services/basic/ProductService";

export default {
  data() {
    return {
      allSelected: false,
      selected: false, // 전체 선택 상태를 추적하는 프로퍼티
      email: "",
      cart: {
        caid: "",
        prid: "",
        siid: "",
        color: "",
        quantity: "",
        email: ""
      },

      pageIndex: 1,
      totalCount: 0,
      recodeCountPerPage: 3,
      searchKeyword: "",
      carties: [], // 빈배열
      selectedItems: [], // 선택된 상품 ID들
      // 사이즈옵션번호
      quantity: this.initialQuantity,
      productTotal: 0, // 상품 총 금액
      shippingCost: 3000, // 배송비
      totalAmount: 0, // 총 결제 금액  
      paid: "",

    };
  },

  methods: {


    //  전체선택 하는 함수
    toggleSelectAll(event) {
      this.allSelected = event.target.checked; // 전체 선택 체크박스 상태 업데이트


      if (this.allSelected) {
        // 전체 선택
        this.selectedItems = this.carties.map(item => item.caid); // 모든 항목 ID 추가
      }

      else {


        // 전체 해제
        this.selectedItems = []; // 선택 항목 초기화
      }
    },
    formatPrice(price) {
      return price.toLocaleString("ko-KR", {
        currency: "KRW",
      });
    },
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

    //  선택삭제
    async removeSelected() {
      try {
        const promises = this.selectedItems.map((caid) =>
          CartService.remove(caid)
        );
        await Promise.all(promises); // 모든 삭제 요청을 병렬로 처리
        this.selectedItems = []; // 선택 항목 초기화
        this.getCart(); // 데이터 다시 로드
      } catch (error) {
        console.error("선택 삭제 오류:", error);
      }
    },

    // 전체 삭제
    async removeAll() {
      try {
        const promises = this.carties.map((item) =>
          CartService.remove(item.caid)
        );
        await Promise.all(promises);
        this.carties = [];
        this.selectedItems = [];
      } catch (error) {
        console.error("전체 삭제 오류:", error);
      }
    },

    // Other methods...

    calculateSummary() {
      // Calculate the total amount only for selected items

      this.productTotal = this.carties
        .filter(item => this.selectedItems.includes(item.caid)) // Filter selected items
        .reduce((total, item) => total + item.price * item.quantity, 0);

      // Calculate the total amount with shipping cost
      this.totalAmount = this.productTotal + this.shippingCost;

    },

    async getCart() {
      try {
        let response = await CartService.getAll(
          this.searchKeyword,
          this.pageIndex - 1,
          this.recodeCountPerPage,
          this.email
        );

        const { results, totalCount } = response.data;
        console.log(response.data); // 디버깅
        this.carties = results;
        this.totalCount = totalCount;



        // 주문금액 계산
        this.carties = results.map(item => ({
          ...item,
          orderAmount: item.price * item.quantity // 초기 주문금액 계산
        }));

        // 데이터 로드 후 요약 정보 업데이트
        this.calculateSummary();
      } catch (error) {
        console.log(error);
      }
    },

    // 수정
    async update(caid, data) {
      try {

        let response = await CartService.update(caid, data);
        console.log(response.data);  // 디버깅
        // this.$router.push("/cart");
        // this.$router.go(0);   // 전체조회 강제이동: /dept
        this.getCart();
      } catch (error) {
        //this.cart.image = undefined;
        console.log(error);
      }
    },
    // 삭제
    async remove(caid) {
      try {

        let response = await CartService.remove(caid);
        console.log(response.data);
        // this.$router.push("/cart"); //
        this.$router.go(0);            // 새로고침
      } catch (error) {
        console.log(error);
      }
    },


    // 수량 업데이트
    updateQuantity(index, change) {
      const cartItem = this.carties[index];
      const newQuantity = cartItem.quantity + change;

      if (newQuantity >= 1) {
        this.carties[index].quantity = newQuantity;
        //this.calculateSummary();
        let clone = this.copyObj(this.carties[index]);
        delete clone.orderAmount;
        delete clone.price;
        delete clone.gender;
        delete clone.name;
        delete clone.fileUrl;
        delete clone.prid;
        delete clone.karegorie;
        delete clone.contents;
        delete clone.sales;
        //console.log(JSON.stringify(clone));
        this.update(clone.caid, clone);
      }
    },

    async pay(pymentData) {
      try {
        let response = await PaymentService.insert(pymentData);
        const paid = response.data;
        return paid;
      } catch {
        alert("정상적으로 결제가 되지 않았습니다.");
        return false;
      }
    },

    async or(pymentData) {
      await this.pay(pymentData).then((result) => {
        this.paid = result;
        console.log("결과 값:", result);
      });


      let OrderData = this.copyObj(this.carties
      .filter(item => this.selectedItems.includes(item.caid)));
      
      let ProductData = this.copyObj(this.carties
      .filter(item => this.selectedItems.includes(item.caid)));
      
      for (let i = 0; i < Object.keys(OrderData).length; i++) {
        delete OrderData[i].orderAmount;
        delete OrderData[i].price;
        delete OrderData[i].gender;
        delete OrderData[i].name;
        delete OrderData[i].fileUrl;
        delete OrderData[i].caid;
        delete OrderData[i].karegorie;
        delete OrderData[i].contents;
        delete OrderData[i].sales;
        OrderData[i].color = 'blue';
        OrderData[i].paid = this.paid;
        OrderData[i].email = this.email;
        // alert(JSON.stringify(OrderData[i]));
        try {
          let response = await OrderService.insert(OrderData[i]);
          console.log(response.data);
        } catch {
          alert("정상적으로 결제가 되지 않았습니다.");
          return false;
        }
      }
      for (let i = 0; i < Object.keys(ProductData).length; i++) {
        
        delete ProductData[i].sales;
        delete ProductData[i].orderAmount;
        delete ProductData[i].fileUrl;
        delete ProductData[i].caid;
        delete ProductData[i].siid;
        ProductData[i].sales = ProductData[i].quantity;
        delete ProductData[i].quantity;

        

        try {
          let response = await ProductService.updateSales(OrderData[i].prid,OrderData[i],ProductData[i].sales);
          console.log(response.data);
        } catch {
          alert("정상적으로 결제가 되지 않았습니다.");
          return false;
        }
      }

      alert("결제 완료했습니다.");
      window.location.href="/";


    },

    orderAll() {
      this.allSelected = true;
      this.selected = true;
      this.selectedItems = this.carties.map(item => item.caid);
      this.calculateSummary();

      const pymentData = {
        totalPrice: this.totalAmount,
        email: this.email
      }

      this.or(pymentData);


      // delete i.orderAmount;
      //     delete i.price;
      //     delete i.gender;
      //     delete i.name;
      //     delete i.fileUrl;
      // item.color = 'blue';


    },

    orderSelected(){
      
      const pymentData = {
        totalPrice: this.totalAmount,
        email: this.email
      }

      this.or(pymentData);
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

    this.getCart();

  },

  // 사이즈옵션번호
  watch: {
    carties: {
      deep: true,
      handler() {
        this.calculateSummary();
      },
    },
    selectedItems: {
      handler() {
        this.calculateSummary(); // Update summary when selected items change
      }
    }
  },


};
</script>
<style>
@import '@/assets/css/Cart/cart.css';
</style>
