D<template>
    <div class ="container all mt-5"> 
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
           
            <h3>1:1 문의내역</h3>

            <table class="table buy-list mt-3">
                <thead>
                    <tr>
                        <th scope="col" class="header">제목</th>
                         <td colspan="3">
                            <input type="text"
                            class="form-control"
                            readonly
                            v-model="inquiry.title">   
                         </td>
                    </tr>
                </thead>
                <tbody>

                    <tr>
                        <th scope="row" class="author">작성자</th>
                        <td>
                            <input type="text"
                            class="form-control"
                            readonly
                            v-model="inquiry.writer">
                        </td>
                        <th scope="row" class="response">문의일시</th>
                        <td>
                            <input type="text"
                            readonly
                            class="form-control"
                            v-model="inquiry.insertTime">
                        </td>
                    </tr>
                    
                    

                    <tr>
                        <td colspan="4" class="questionc">
                            <input type="text"
                            class="form-control"
                            readonly
                            v-model="inquiry.content">
                        </td>
                    </tr>
                </tbody>
            </table>
            
            <hr>


            <!-- 답변 -->
                     <table class="tbawnser">
                        <thead>
                            <tr>
                            <th class="ta">
                               답변일시      
                            </th>

                            <th class="tb"
                                readonly>
                              {{inquiry.answerInsertTime}}   
                            </th>

                            </tr>
                        </thead>
                    </table>

        <div class="anwser">
            <span>
                <input type="text"
                    class="form-control"
                    readonly
                    :value="inquiry.answerContent || '빠른 시간 내에 답변드리겠습니다.'">
            </span>
            </div>
            
        </div>


        <div class="btn">
           <router-link to = "/basic/mypage/inquiry"> <button class="back-btn">목록</button></router-link>
            <button class="delete-btn" @click="remove">삭제</button>
        </div>
        
    </div>
</template>
<script>
import InquiryService from '@/services/basic/InquiryService';
export default {
data() {
         return {
            inquiry: {
              iqid : "",
              title : "",
              contents : "",
              writer:"",
              anwser:"",
              answerid:"",
              answerContent:"",
            },
          }
       },

     methods: {
        async getDetail(iqid){
          try {
            let response = await InquiryService.get(iqid);
            console.log (response.data);
            this.inquiry = response.data;
          } catch (error) {
            console.log(error);
          }
        },
        async remove(){
            try {
                let response = await InquiryService.remove(this.inquiry.iqid);
                console.log(response);
                this.$router.push("/basic/mypage/inquiry");
            } catch (error) {
                console.log(error);
            }
        }

    },
        mounted() {
      this.getDetail(this.$route.params.iqid);
     
    }
    }
      
</script>
<style>
 @import '@/assets/css/Mypage/InquiryDetailView.css';
  input[type="text"] {
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
        box-shadow: none;    /* 포커스시 그림자 제거 */
    }

    input[type="text"].form-control {
        padding: 0;
        border-radius: 0;
    }  


</style>