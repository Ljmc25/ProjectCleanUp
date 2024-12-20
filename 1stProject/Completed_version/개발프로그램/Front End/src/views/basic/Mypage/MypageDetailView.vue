<template>

  <div class="container all">
    <h1 class="title mt-5">
      <router-link to="/mypage">MY PAGE</router-link>
    </h1>

    <hr class="hr2">

    <div class="banner mt-5">
      <img src="@/assets/images/banner2.jpg" />
    </div>


    <div class="left">

      <li class="list-group-item p1">마이페이지</li>
      <hr>
      <br>

      <li class="list-group-item p"> 나의 쇼핑 </li>
      <li class="list-group-item p2">
        <a href="/basic/mypage">-구매 내역 조회</a>
      </li>
      <br>
      <li class="list-group-item p">나의 활동</li>
      <li class="list-group-item p2">
        <a href="/basic/mypage/inquiry"> -1:1 문의하기 </a>
      </li>

      <br>
      <li class="list-group-item p">나의정보</li>
      <li class="list-group-item p2">
        <a href="/basic/mypage/memberinfo">-회원 정보</a>
      </li>

    </div>




    <div class="main">
      <!-- 테이블 -->
      <h3>구매 내역 상세조회</h3>

      <table class="table buy-list mt-3">
        <colgroup>
          <col style="width: 25%;">
          <col style="width: 10%;">
          <col style="width: 30%;">
          <col style="width: 7%;">
          <col style="width: 21%;">
          <col style="width: 7%;">
        </colgroup>
        <thead>
          <tr>
            <th scope="col">상품</th>
            <th scope="col">상품번호</th>
            <th scope="col">상품명</th>
            <th scope="col">사이즈</th>
            <th scope="col">가격</th>
            <th scope="col">개수</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="(data, index) in orderjoinproduct" :key="index">
            <!-- <td><img src="@/assets/images/koala3.jpg" width="50%"></td> -->
            <td><img :src="data.imageUrl" width="70%"></td>
            <td>{{ data.code }}</td>
            <td>[{{ data.gender }}]<br>
              {{ data.name }}
            </td>
            <td>{{ data.siid }}</td>
            <td>{{ formatPrice(data.price) }}원</td>
            <td>{{ data.quantity }}</td>
          </tr>
        </tbody>
      </table>

      <!-- 페이징 -->
      <div class=" page">
        <b-pagination v-model="pageIndex" :total-rows="totalCount" :per-page="recodeCountPerPage"
          @click="save"></b-pagination>
      </div>

      <!-- <div class="delivery">
                <h3>배송 내역 상세조회</h3>

                <table class="table delivery-list mt-3">
                    <thead>
                      <tr>
                        <th scope="col">배송코드</th>
                        <th scope="col">배송상태</th>
                        <th scope="col">택배사</th>
                        <th scope="col">수취인 이름</th>
                        <th scope="col">수취인 번호</th>
                      </tr>
                    </thead>
    
                    <tbody>
                      <tr>
                        <td><input type="text" readonly v-model="deliveryBox.code"></td>
                        <td><input type="text" readonly v-model="deliveryBox.state"></td>
                        <td><input type="text" readonly v-model="deliveryBox.courier"> </td>
                        <td><input type="text" readonly v-model="deliveryBox.consigneeName"></td>
                        <td><input type="text" readonly v-model="deliveryBox.consigneePhoneNumber"></td>
                      </tr>
                    </tbody>
                  </table>
              </div> -->



    </div>
  </div>

</template>
<script>
import OrderService from '@/services/advanced/OrderService';
import DeliveryService from '@/services/advanced/DeliveryService';
export default {
  data() {
    return {
      orderjoinproduct: [],
      paid: "",
      deliveryBox: {},
      pageIndex: 1,
      totalCount: 0,
      recodeCountPerPage: 4,
      searchKeyword: "",
    }
  },
  methods: {
    formatPrice(price) {
      return price.toLocaleString("ko-KR", {
        currency: "KRW",
      });
    },
    async save() {
      try {
        let response = await OrderService.getDetailByPaid(
          this.paid,
          this.searchKeyword,
          this.pageIndex - 1,
          this.recodeCountPerPage,);
        console.log(response.data);
        // 나중에 페이징
        const { results, totalCount } = response.data;
        this.orderjoinproduct = results;
        this.totalCount = totalCount;

      } catch (error) {
        console.log(error);
      }
    },

    watch: {
      pageIndex() {
        this.save();
      }
    },

    async getFk(paid) {
      try {
        let response = await DeliveryService.getFk(paid);
        console.log(response.data);

        this.deliveryBox = response.data;


      } catch (error) {
        console.log(error);
      }
    }

  },
  mounted() {
    // this.save(this.$route.params.paid);
    this.paid = this.$route.params.paid;
    this.save();
    this.getFk(this.$route.params.paid);
  },
}
</script>
<style>
@import '@/assets/css/Mypage/MypageDetailView.css';

input[type="text"] {
  text-align: center;
  border: none;
  background: none;
  outline: none;
  font-size: 16px;
  color: #000;
  width: 100%;
  box-sizing: border-box;

}

input[type="text"]:focus {
  border: none;
  outline: none;
  box-shadow: none;
  /* 포커스시 그림자 제거 */
}

input[type="text"].form-control {
  padding: 0;
  border-radius: 0;
}
</style>