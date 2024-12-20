// 공유저장소 : 뷰(vue) 전역(공유) 변수 정의하는 곳
import { createStore } from 'vuex'

// TODO: 로컬스토리지의 user 값을 가져와서 저장
// TODO: 사용법(조회) : localStorage.getItem("키") [결과:문자열]
// TODO: 문자열->객체 :  JSON.parse(문자열)
const user = JSON.parse(localStorage.getItem("user"));
export default createStore({
  state: {
    // 전역(공유) 변수 정의하는 곳
    loggedIn : user!=null? true : false // 로그인 여부
  },
})
