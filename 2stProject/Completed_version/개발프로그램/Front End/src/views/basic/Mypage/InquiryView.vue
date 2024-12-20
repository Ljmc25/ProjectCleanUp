<template>
    <div>
        <div class ="container all"> 
        <h1 class="title">MY PAGE</h1> 

        <hr class="hr2">


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
           
            <h2>1:1 문의내역</h2>

            <table class="table buy-list mt-3">
                <thead>
                  <tr>
                    <th scope="col">No.</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성일</th>
                    <th scope="col">답변</th>
                  </tr>
                </thead>

                <tbody>
                  <tr v-for="(data, index) in inquiries" :key="index">
                    <td>{{ calculateIndex(index)}}</td>

                      <td><a :href="'/basic/mypage/inquiry/' + data.iqid">
                                {{ data.title }}
                            </a></td>

                    <td>{{data.insertTime}}</td>
                    <td>{{data.answerContent ? "Y" : "N"}}</td>
                  </tr>               
                </tbody>
              </table>


            <!-- 페이징 -->
            <div class=" page">
                 <b-pagination v-model="pageIndex" :total-rows="totalCount" :per-page="recodeCountPerPage"
                @click="getInquiry"></b-pagination>
            </div>
            
        </div>


        <div class="btn">
        <a href="/basic/mypage/inquiry/write">
            <button class="write-btn">
              <!-- <router-link to ="/basic/mypage/inquiry/write">글쓰기</router-link> -->
              글쓰기
              </button>
              </a>
        </div>
        
    </div>    
    </div>
</template>
<script>
import InquiryService from '@/services/basic/InquiryService';
export default {
    data() {
      return {
            pageIndex: 1, 
            totalCount: 0, 
            recodeCountPerPage: 4, 
            searchKeyword: "",
            email:"",
            inquiries: [], 
      }
    },


    methods: {
      calculateIndex(index) {
      return this.totalCount -(this.pageIndex - 1) * this.recodeCountPerPage - index;
    },
        async getInquiry(){
          // const le = localStorage.getItem("user");
          // const ll = JSON.parse(le);
          // alert(ll.email);

          try {
            let response = await InquiryService.getAll(
                    this.searchKeyword,
                    this.pageIndex - 1,
                    this.recodeCountPerPage,
                    this.email
                );
                 const { results, totalCount } = response.data;
                console.log(response.data); // 디버깅
                this.inquiries = results;
                this.totalCount = totalCount;
            
          } catch (error) {
            console.log(error)
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


      this.getInquiry();

    },
}
</script>
<style>
    @import '@/assets/css/Mypage/InquiryView.css';
</style>