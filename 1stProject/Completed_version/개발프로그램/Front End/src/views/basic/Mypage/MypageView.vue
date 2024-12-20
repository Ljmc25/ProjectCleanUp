<template>
    <div class ="container all mt-5"> 
      <h1 class="title">MY PAGE</h1> 

      <hr class="hr2">
        <div class="banner mt-5">
    <div class="banner-content">
        <img src="@/assets/images/banner2.jpg" />
    </div>
</div>


         <div class="left">

            <li class="list-group-item p1">마이페이지</li>
            <hr>
            <br>

            <li class="list-group-item p"> 나의 쇼핑  </li>
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
            <h3>구매 내역 조회</h3>

            <table class="table buy-list mt-3">
                <thead>
                  <tr>
                    <th scope="col">주문번호</th>
                    <th scope="col">결제금액</th>
                    <th scope="col">주문일시</th>
                    <th scope="col">상세보기</th>
                    <th scope="col">환불</th>
                    <th scope="col">환불여부</th>
                  </tr>
                </thead>

                <tbody>
                  <tr v-for="(data, index) in payments" :key="index">
                    <td>{{data.code}}</td>
                    <td>{{data.totalPrice}}원</td>
                    <td>{{data.insertTime}}</td>
                    <!-- <td><router-link :to='"/basic/mypage/detail/" + data.paid'>[상세보기]</router-link></td> -->
                    <td><a :href="'/basic/mypage/detail/' + data.paid">[상세보기]</a></td>
                    <td>   <button
                             
                            :disabled="!!data.deleteTime" 
                            @click="update(data.paid)" 
                            :style="{ cursor: data.deleteTime ? 'not-allowed' : 'pointer' }"
                          >
                            환불하기
                          </button></td>

                    <td>
                       <span>{{ data.deleteTime ? "[환불완료]" : "-" }}</span>
                    </td>
                  </tr>
  
                </tbody>
              </table>


            <!-- 페이징 -->
            <div class=" page">
                 <b-pagination v-model="pageIndex" :total-rows="totalCount" :per-page="recodeCountPerPage"
                @click="getPayment"></b-pagination>
            </div>
            
        </div>


        

       






    </div>    
</template>
<script>
import PaymentService from '@/services/advanced/PaymentService';
export default {

 data() {
      return {
            pageIndex: 1, 
            totalCount: 0, 
            recodeCountPerPage: 4, 
            searchKeyword: "",
            email:"",
            payments: {
              paid:""
            }, 
      }
    },
 methods: {
  formatPrice(price) {
      return price.toLocaleString("ko-KR", {
        currency: "KRW",
      });
    },
  async getPayment(){
    try {       
      let response = await PaymentService.getAll(
        this.searchKeyword,
        this.pageIndex - 1,
        this.recodeCountPerPage,
        this.email,
      );
      const { results, totalCount } = response.data;
      console.log(response.data);
      this.payments = results;
      this.totalCount = totalCount;
    } catch (error) {
      console.log(error)
    }
  },
         async update(paid){
          try {
            const payload = { paid: paid};
             let response 
             = await PaymentService.update(paid, payload);
             console.log(response.data);
             window.location.href = "/basic/mypage"; // 등록 후 이동
            //  this.$router.push("/basic/mypage");
             alert("환불 완료.")
          } catch (error) {
            console.log(error);
          }
        }
 },
  
  mounted() {
     const userData = localStorage.getItem("user");
      if (userData) {
        const parsedData = JSON.parse(userData);
        if (parsedData.email) {
          this.email = parsedData.email; // email 값을 업데이트
        } else {
          console.warn("Email 값이 존재하지 않습니다.");
        }
      } else {
        console.warn("localStorage에서 'user' 데이터를 찾을 수 없습니다.");
      }


    this.getPayment();


     
  },
}
</script>
<style scoped>
    @import '@/assets/css/Mypage/MypageView.css';
</style>